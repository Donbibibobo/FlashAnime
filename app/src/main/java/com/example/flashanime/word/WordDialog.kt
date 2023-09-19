package com.example.flashanime.word

import android.os.Bundle
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
import com.example.flashanime.detail.DetailFragmentArgs
import com.example.flashanime.ext.getVmFactory

class WordDialog: AppCompatDialogFragment() {

    private val viewModel by viewModels<WordViewModel> { getVmFactory(WordDialogArgs.fromBundle(requireArguments()).wordInfoKey) }
    private lateinit var binding: DialogWordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.WordDialog)
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

        // mock collected word
        binding.collectdUnSave.setOnClickListener {
            it.visibility = View.GONE
            binding.collectdSave.visibility = View.VISIBLE
        }
        binding.collectdSave.setOnClickListener {
            it.visibility = View.GONE
            binding.collectdUnSave.visibility = View.VISIBLE
        }



        return binding.root
    }
}