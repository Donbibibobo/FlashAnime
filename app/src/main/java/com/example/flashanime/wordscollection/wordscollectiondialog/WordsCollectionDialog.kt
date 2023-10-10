package com.example.flashanime.wordscollection.wordscollectiondialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.flashanime.R
import com.example.flashanime.databinding.DialogWordBinding
import com.example.flashanime.databinding.DialogWordsCollectionBinding
import com.example.flashanime.ext.getVmFactory
import com.example.flashanime.word.WordDialogArgs
import com.example.flashanime.word.WordViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions

class WordsCollectionDialog: AppCompatDialogFragment()  {

    private val viewModel by viewModels<WordsCollectionDialogViewModel> { getVmFactory(WordsCollectionDialogArgs.fromBundle(requireArguments()).wordInfoKey) }
    private lateinit var binding: DialogWordsCollectionBinding

    private var currentYouTubeSecond: Float = 0f
    private var youTubePlayerFragment: YouTubePlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.CenterDialogTheme)

        Log.i("WordsCollectionDialog","onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("WordsCollectionDialog","onCreateView")

        binding = DialogWordsCollectionBinding.inflate(inflater, container, false)
//        binding.layoutWord.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in))

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.leave.observe(
            viewLifecycleOwner,
            Observer {
                it?.let {
                    dismiss()
                    viewModel.onLeaveCompleted()
                }
            }
        )

        binding.dismiss.setOnClickListener {
            viewModel.leave()
        }

        // set start time
        val startSecond = viewModel.timeToSeconds(viewModel.wordsCollection.value!!.wordsTime) - 0.5f
        val safeStartSecond = startSecond.coerceAtLeast(0f)
        var setSecond: Boolean = false

        class YtListener : AbstractYouTubePlayerListener() {
            override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {

                if (setSecond.not()){
                    currentYouTubeSecond = second
                    setSecond = true
                }
                Log.i("secondddd", "second: $second")
                Log.i("secondddd", "currentYouTubeSecond: $currentYouTubeSecond")
                if (second >= currentYouTubeSecond+5f) {
                    youTubePlayer.pause()
                }
            }

            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)

                youTubePlayerFragment = youTubePlayer

                youTubePlayer.loadVideo(viewModel.wordsCollection.value!!.episodeId, safeStartSecond)
            }
            override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {
                when (state) {
                    PlayerConstants.PlayerState.PLAYING -> {
                        Log.i("updateRunnable", "Playing")
                    }
                    PlayerConstants.PlayerState.PAUSED, PlayerConstants.PlayerState.ENDED -> {
                        Log.i("updateRunnable", "Paused or Ended")
                    }
                    else -> {}
                }
            }
        }



        val listener = YtListener()

        val options: IFramePlayerOptions = IFramePlayerOptions.Builder()
            .controls(0)
            .build()
        binding.video.enableAutomaticInitialization = false
        binding.video.initialize(listener, options)


//        binding.video.addYouTubePlayerListener(listener)


        binding.transparentOverlay.setOnClickListener {
            Log.i("transparentOverlay","transparentOverlay click")
            setSecond = false
            youTubePlayerFragment?.loadVideo(viewModel.wordsCollection.value!!.episodeId, safeStartSecond)

        }


        return binding.root
    }
}