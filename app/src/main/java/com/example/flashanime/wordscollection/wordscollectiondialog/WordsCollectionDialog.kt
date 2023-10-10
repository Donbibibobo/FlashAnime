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

class WordsCollectionDialog: AppCompatDialogFragment()  {

    private val viewModel by viewModels<WordsCollectionDialogViewModel> { getVmFactory(WordsCollectionDialogArgs.fromBundle(requireArguments()).wordInfoKey) }
    private lateinit var binding: DialogWordsCollectionBinding

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


        return binding.root
    }
}