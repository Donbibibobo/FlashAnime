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
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent


class DetailFragment: Fragment() {
//DetailFragmentArgs.fromBundle(requireArguments()).animeInfoKey
    private val viewModel by viewModels<DetailViewModel> { getVmFactory()  }

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

        val assetUri = "asset:///1.mp4"
        val mediaItem = MediaItem.fromUri(Uri.parse(assetUri))
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true


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
                "https://bahamut.akamaized.net/113306eb62e8e64bdc2ba1787c2a834df66e72d4/1080p/hdntl=exp=1695272243~acl=%2f*~data=hdntl,twterry10%3a34861%3a1%3a1%3a68686829~hmac=1efa69b472c0dd7d7ba4c39d3d34899354c147766940d283a2bbd2ab6ac2232a/key_b5000000.m3u8key"
            )))
            exoPlayer.prepare()
            exoPlayer.playWhenReady = true
        })



        // wordList recyclerview

        val adapterWordList = DetailWordListAdapter(
            {
                exoPlayer.seekTo(viewModel.timeToMillis(it.time))
            },{
                Log.i("wordsList", "${it.word}")
                viewModel.getWordInfo(it.word)
            })
        binding.wordList.adapter = adapterWordList

        val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider, null)?.let {
            dividerItemDecoration.setDrawable(it)
        }
        binding.wordList.addItemDecoration(dividerItemDecoration)



        //fix
//        exoPlayer.setMediaItem(MediaItem.fromUri(Uri.parse(
//            ""
//        )))

        exoPlayer.prepare()
        exoPlayer.playWhenReady = true

        adapterWordList.submitList(
            viewModel.animeInfoArg.value!!.wordsList[viewModel.episodeExo].playWords
        )


        // change to selected episode
//        viewModel.episodeMutableListSelected.observe(viewLifecycleOwner, Observer {
//            adapterEpisode.submitList(it)
            // set source
//            exoPlayer.stop()
//            exoPlayer.clearMediaItems()

//            Log.i("test11","fragment: ${viewModel.episodeExo}")
//            exoPlayer.setMediaItem(MediaItem.fromUri(Uri.parse(
//                        viewModel.animeInfoArg.value!!.videoSourceM3U8[viewModel.episodeExo]
//            )))

//            exoPlayer.prepare()
//            exoPlayer.playWhenReady = true

            // submit wordList according to the episode
//            adapterWordList.submitList(
//                viewModel.animeInfoArg.value!!.wordsList[viewModel.episodeExo].playWords
//            )
//        })


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


