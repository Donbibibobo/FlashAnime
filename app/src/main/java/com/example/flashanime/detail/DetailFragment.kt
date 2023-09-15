package com.example.flashanime.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.flashanime.all.AllViewModel
import com.example.flashanime.databinding.FragmentAllBinding
import com.example.flashanime.databinding.FragmentDetailBinding
import com.example.flashanime.ext.getVmFactory

class DetailFragment: Fragment() {

    private val viewModel by viewModels<DetailViewModel> { getVmFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel





        return binding.root
    }

}