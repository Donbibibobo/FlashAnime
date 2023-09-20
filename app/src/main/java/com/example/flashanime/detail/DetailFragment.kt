package com.example.flashanime.detail

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
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
import com.example.flashanime.databinding.FragmentDetailBinding
import com.example.flashanime.ext.getVmFactory
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel


        // keep the screen always on
        val window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)


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
                viewModel.scrollToWord(matchingWordPosition, binding.wordList)
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
                youTubePlayer.loadVideo(viewModel.animeInfoArg.value!!.videosId.last(), 0f)
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


        // wordList recyclerview - all section
        val adapterWordList = DetailWordListAdapter(
            {
            // click sound icon to jump to specific time
                // if doesn't have words List
                if (it.level != ""){
                    val desiredTimeInSeconds = viewModel.timeToMillis(it.time) / 1000f
                    youTubePlayerDetailFragment?.seekTo(desiredTimeInSeconds)
                }
            },{
            // click to get word info through JLPt API
                // if doesn't have words List
                if (it.level != ""){
                    viewModel.getWordInfo(it.word)
                }
            })
        binding.wordList.adapter = adapterWordList


        // add dividerItemDecoration for wordList recyclerview
        val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider, null)?.let {
            dividerItemDecoration.setDrawable(it)
        }
        binding.wordList.addItemDecoration(dividerItemDecoration)


        // video default first episode
        viewModel.episodeMutableListDefault.observe(viewLifecycleOwner, Observer {
            adapterEpisode.submitList(it)
            adapterWordList.submitList(viewModel.animeInfoArg.value!!.wordsList[viewModel.episodeExo].playWords)
        })


        // change to selected episode list
        viewModel.episodeMutableListSelected.observe(viewLifecycleOwner, Observer {
            adapterEpisode.submitList(it)

            // change to selected video
            youTubePlayerDetailFragment?.loadVideo(viewModel.animeInfoArg.value!!.videosId[viewModel.episodeExo], 0f)

            // change to selected wordList
            adapterWordList.submitList(viewModel.animeInfoArg.value!!.wordsList[viewModel.episodeExo].playWords)
        })


        // show word info from word API
        viewModel.wordInfoSelected.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(NavigationDirections.navigateToWordDialog(it))
        })


        // file picker
//        val dialogProperties: DialogProperties
//        val filePickerDialog: FilePickerDialog

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
        val window = requireActivity().window
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
}


