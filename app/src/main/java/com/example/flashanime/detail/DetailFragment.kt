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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
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


class DetailFragment: Fragment() {

    private val viewModel by viewModels<DetailViewModel> { getVmFactory(DetailFragmentArgs.fromBundle(requireArguments()).animeInfoKey)  }

    private lateinit var exoPlayer: ExoPlayer

    private val updateHandler = Handler(Looper.getMainLooper())


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

        exoPlayer = ExoPlayer.Builder(requireContext()).build()
        binding.styledPlayerView.player = exoPlayer




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



        // video default last episode
        viewModel.episodeMutableListDefault.observe(viewLifecycleOwner, Observer {
            adapterEpisode.submitList(it)
            exoPlayer.setMediaItem(MediaItem.fromUri(Uri.parse(
                viewModel.animeInfoArg.value!!.videoSourceM3U8[it.size-1]
            )))
            exoPlayer.prepare()
            exoPlayer.playWhenReady = true
        })



        // wordList recyclerview
        val adapterWordList = DetailWordListAdapter{
            exoPlayer.seekTo(viewModel.timeToMillis(it.time))
        }
        binding.wordList.adapter = adapterWordList
        val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider, null)?.let {
            dividerItemDecoration.setDrawable(it)
        }
        binding.wordList.addItemDecoration(dividerItemDecoration)



        // change to selected episode
        viewModel.episodeMutableListSelected.observe(viewLifecycleOwner, Observer {
            adapterEpisode.submitList(it)
            // set source
            exoPlayer.stop()
            exoPlayer.clearMediaItems()

            Log.i("test11","fragment: ${viewModel.episodeExo}")
            Log.i("test11","fragment: ${viewModel.episodeExo}")
            exoPlayer.setMediaItem(MediaItem.fromUri(Uri.parse(
                        viewModel.animeInfoArg.value!!.videoSourceM3U8[viewModel.episodeExo]
            )))

            exoPlayer.prepare()
            exoPlayer.playWhenReady = true

            // submit wordList according to the episode
            adapterWordList.submitList(
                viewModel.animeInfoArg.value!!.wordsList[viewModel.episodeExo].playWords
            )
        })


        val updateRunnable = object : Runnable {
            override fun run() {
                Log.i("updateRunnable", Thread.currentThread().name)

                val currentTime = exoPlayer.currentPosition
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


        // other feature about the video player
        exoPlayer.addListener(object: Player.Listener{
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                if (isPlaying) {
                    Log.i("updateRunnable", "Playing")
                    updateHandler.post(updateRunnable)
                } else {
                    Log.i("updateRunnable", "Paused")
                    updateHandler.removeCallbacks(updateRunnable)
                }
            }

            override fun onPlaybackStateChanged(playbackState: Int) {
                when (playbackState) {
                    Player.STATE_IDLE, Player.STATE_ENDED, Player.STATE_BUFFERING -> {
                        updateHandler.removeCallbacks(updateRunnable)
                    }
                }
            }
        })







        // file picker
//        val dialogProperties: DialogProperties
//        val filePickerDialog: FilePickerDialog

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        exoPlayer.playWhenReady = true
        exoPlayer.play()

//        updateHandler.post(updateRunnable)


    }

    override fun onPause() {
        super.onPause()

        exoPlayer.pause()
        exoPlayer.playWhenReady = false

//        updateHandler.removeCallbacks(updateRunnable)


    }

    override fun onStop() {
        super.onStop()

        exoPlayer.pause()
        exoPlayer.playWhenReady = false


    }

    override fun onDestroy() {
        super.onDestroy()

        val window = requireActivity().window
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        exoPlayer.stop()
        exoPlayer.clearMediaItems()
    }


}


