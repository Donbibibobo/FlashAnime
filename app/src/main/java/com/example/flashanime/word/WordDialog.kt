package com.example.flashanime.word

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
import androidx.lifecycle.ViewModelProvider
import com.example.flashanime.MainViewModel
import com.example.flashanime.R
import com.example.flashanime.databinding.DialogWordBinding
import com.example.flashanime.ext.getVmFactory
import com.example.flashanime.util.CurrentFragmentType

class WordDialog: AppCompatDialogFragment() {

    private val viewModel by viewModels<WordViewModel> { getVmFactory(WordDialogArgs.fromBundle(requireArguments()).wordInfoKey) }
    private lateinit var binding: DialogWordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.Dialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DialogWordBinding.inflate(inflater, container, false)
        binding.layoutWord.startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_slide_up))

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


        binding.collectdUnSave.setOnClickListener {
            viewModel.addUserCollectedWordsList()
        }
        binding.collectdSave.setOnClickListener {
            viewModel.removeUserCollectedWord()
        }

        viewModel.collectedWordsList.observe(viewLifecycleOwner) { collectedWords ->
            val wordInCollectedList = collectedWords.contains(viewModel.wordInfoArg.value!!.word)

            if (wordInCollectedList) {
                val updatedWordInfo = viewModel.wordInfoArg.value!!.copy(isCollected = true)
                viewModel.wordInfoArgForUi.value = updatedWordInfo
            } else {
                val updatedWordInfo = viewModel.wordInfoArg.value!!.copy(isCollected = false)
                viewModel.wordInfoArgForUi.value = updatedWordInfo
            }
        }


        val mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        val currentFragmentType = mainViewModel.getCurrentFragmentTypeToHideCollected()

        if (currentFragmentType == CurrentFragmentType.WORD_TEST || currentFragmentType == CurrentFragmentType.WORDS_COLLECTION){
            val parentViewGroup = binding.collectdSave.parent as? ViewGroup
            parentViewGroup?.removeView(binding.collectdSave)
            parentViewGroup?.removeView(binding.collectdUnSave)
        }




        return binding.root
    }
}