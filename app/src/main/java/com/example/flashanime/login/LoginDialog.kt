package com.example.flashanime.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.view.children
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.flashanime.R
import com.example.flashanime.all.category.CategoryViewModel
import com.example.flashanime.databinding.DialogCategoryBinding
import com.example.flashanime.databinding.DialogLoginBinding
import com.example.flashanime.ext.getVmFactory
import com.google.android.material.chip.Chip

class LoginDialog: AppCompatDialogFragment() {

    private val viewModel by viewModels<LoginViewModel> { getVmFactory() }
    private lateinit var binding: DialogLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.Dialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DialogLoginBinding.inflate(inflater, container, false)
        binding.layoutLogin.startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_slide_up))

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