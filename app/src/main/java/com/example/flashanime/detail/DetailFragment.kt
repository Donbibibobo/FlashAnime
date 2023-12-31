package com.example.flashanime.detail

import android.content.res.ColorStateList
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.flashanime.NavigationDirections
import com.example.flashanime.R
import com.example.flashanime.data.WordsCollection
import com.example.flashanime.databinding.FragmentDetailBinding
import com.example.flashanime.ext.getVmFactory
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


class DetailFragment: Fragment() {

    private val viewModel by viewModels<DetailViewModel> { getVmFactory(DetailFragmentArgs.fromBundle(requireArguments()).animeInfoKey)  }

    private var youTubePlayerDetailFragment: YouTubePlayer? = null

    private val updateHandler = Handler(Looper.getMainLooper())

    private var currentYouTubeSecond: Float = 0f

    private lateinit var dataObserver: RecyclerView.AdapterDataObserver

    private lateinit var adapterWordList: DetailWordListAdapter

    private lateinit var window: Window


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel


        // keep the screen always on
        window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        binding.back.setOnClickListener {
            it.findNavController().navigateUp()
        }

        // auto track color
        var isAutoTrack = true
        val colorBg = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.new_bg))
        val colorWhite = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.new_text))
        binding.autoTrack.setOnClickListener {
            isAutoTrack = !isAutoTrack
            if (isAutoTrack) {
                binding.autoTrack.setIconResource(R.drawable.locked)
                binding.autoTrack.backgroundTintList = colorBg
                binding.autoTrack.setTextColor(colorWhite)
            } else {
                binding.autoTrack.setIconResource(R.drawable.unlocked)
                binding.autoTrack.backgroundTintList = colorWhite
                binding.autoTrack.setTextColor(colorBg)
            }
        }

        // change the wordList
        val updateRunnable = object : Runnable {
            override fun run() {
                Log.i("updateRunnable", Thread.currentThread().name)

                val currentTime = (currentYouTubeSecond * 1000).toLong()
                val matchingWordPosition = viewModel.findMatchingWordPosition(currentTime)
                binding.wordList.adapter?.let {
                    if (it is DetailWordListAdapter) {
                        it.highlightWordPosition(matchingWordPosition-1)
                    }
                }
                if (isAutoTrack){
                    viewModel.scrollToWord(matchingWordPosition, binding.wordList)
                }
                updateHandler.postDelayed(this, 500)
            }
        }

        // video player
        binding.youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
                currentYouTubeSecond = second
            }
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                youTubePlayerDetailFragment = youTubePlayer
                youTubePlayer.cueVideo(viewModel.animeInfoArg.value!!.videosId.first(), 0f)
            }
            override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {
                when (state) {
                    PlayerConstants.PlayerState.PLAYING -> {
                        Log.i("updateRunnable", "Playing")
                        updateHandler.post(updateRunnable)
                    }
                    PlayerConstants.PlayerState.PAUSED, PlayerConstants.PlayerState.ENDED -> {
                        Log.i("updateRunnable", "Paused or Ended")
                        updateHandler.removeCallbacks(updateRunnable)
                    }
                    else -> {}
                }
            }
        })

        // wordList recyclerview - all section
        adapterWordList = DetailWordListAdapter(
            clickSound = {
                val desiredTimeInSeconds = viewModel.timeToMillis(it.time)/ 1000f
                youTubePlayerDetailFragment?.seekTo(desiredTimeInSeconds)
                Log.i("soundbuttob","$desiredTimeInSeconds")
            },
            clickWord = {
                // avoid multiple clicks
                if (viewModel.wordsClick.not()){
                    viewModel.getWordInfo(it.word)
                }

                // this is for words collection
                viewModel.playWords.value = it
            },
            requireContext())
        binding.wordList.adapter = adapterWordList




        // episode recyclerView
        val adapterEpisode = DetailEpisodeAdapter{
            viewModel.selectedEpisodeList(it)
        }
        binding.episode.adapter = adapterEpisode
        val layoutManager = FlexboxLayoutManager(requireContext()).apply {
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.FLEX_START
            alignItems = AlignItems.STRETCH
            flexWrap = FlexWrap.WRAP
        }
        binding.episode.layoutManager = layoutManager




        // let recyclerview scroll to top
        dataObserver = object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                binding.wordList.scrollToPosition(0)
            }
        }
        adapterWordList.registerAdapterDataObserver(dataObserver)


        // add dividerItemDecoration for wordList recyclerview
        val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider, null)?.let {
            dividerItemDecoration.setDrawable(it)
        }
        binding.wordList.addItemDecoration(dividerItemDecoration)


        // video default first episode
        viewModel.episodeMutableListDefault.observe(viewLifecycleOwner, Observer {
            adapterEpisode.submitList(it)

            adapterWordList.submitList(viewModel.wordList2Submit)

        })


        // change to selected episode list
        viewModel.episodeMutableListSelected.observe(viewLifecycleOwner) { episodeList ->
            adapterEpisode.submitList(episodeList)

            // change to selected video
            youTubePlayerDetailFragment?.cueVideo(
                viewModel.animeInfoArg.value!!.videosId[viewModel.episodeExo],
                0f
            )

            // change to selected wordList
            adapterWordList.submitList(viewModel.animeInfoArg.value!!.wordsList[viewModel.episodeExo].playWords)
        }


        // show word info from word API
        viewModel.wordInfoSelected.observe(viewLifecycleOwner) {
            val episodeNum =
                viewModel.animeInfoArg.value!!.wordsList[viewModel.episodeExo].episodeNum
            // this is for words collection
            val wordsCollection = WordsCollection(
                viewModel.animeInfoArg.value!!.animeId,
                viewModel.playWords.value!!.time,
                viewModel.animeInfoArg.value!!.title,
                viewModel.animeInfoArg.value!!.pictureURL,
                episodeNum,
                it.word,
                false,
                false,
                it.meaning,
                it.furigana,
                it.romaji,
                viewModel.animeInfoArg.value!!.videosId[episodeNum.toInt() - 1]
            )
            findNavController().navigate(NavigationDirections.navigateToWordDialog(wordsCollection))

            viewModel.wordsClick = false

        }


        // watch history
        viewModel.animeInfoArg.observe(viewLifecycleOwner, Observer {
            viewModel.setUserWatchHistoryList(it.animeId)
        })


        return binding.root
    }
    override fun onResume() {
        super.onResume()
        youTubePlayerDetailFragment?.play()
    }
    override fun onPause() {
        super.onPause()
        youTubePlayerDetailFragment?.pause()
    }
    override fun onDestroy() {
        super.onDestroy()
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        adapterWordList.unregisterAdapterDataObserver(dataObserver)
    }
}


