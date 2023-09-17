package com.example.flashanime.detail

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flashanime.all.AllViewModel
import com.example.flashanime.databinding.FragmentAllBinding
import com.example.flashanime.databinding.FragmentDetailBinding
import com.example.flashanime.ext.getVmFactory
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.StyledPlayerView

class DetailFragment: Fragment() {

    private val viewModel by viewModels<DetailViewModel> { getVmFactory(DetailFragmentArgs.fromBundle(requireArguments()).animeInfoKey)  }

    private lateinit var exoPlayer: ExoPlayer

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
        val adapter = DetailEpisodeAdapter()
        binding.episode.adapter = adapter
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.episode.layoutManager = layoutManager


        viewModel.animeInfoArg.observe(viewLifecycleOwner, Observer { animeInfo ->
            // set source
            exoPlayer.setMediaItem(MediaItem.fromUri(Uri.parse(
//                "https://vpx16.myself-bbs.com/vpx/46596/001/720p.m3u8"
                animeInfo.videoSourceM3U8[1]
            )))

            // set episode recyclerView
            val listCount = List(animeInfo.videoSourceM3U8.size) { (it + 1).toString() }
            adapter.submitList(listCount)

        })



        exoPlayer.prepare()

        // other feature about the video player
        exoPlayer.addListener(object: Player.Listener{
            override fun onRenderedFirstFrame() {
                super.onRenderedFirstFrame()

                // after the video was playing, you can do something here
                // for example, show a toast, or change UI, or make UI into full screen
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
    }

    override fun onPause() {
        super.onPause()

        exoPlayer.pause()
        exoPlayer.playWhenReady = false
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