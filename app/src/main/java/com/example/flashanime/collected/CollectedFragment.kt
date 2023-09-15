package com.example.flashanime.collected

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.flashanime.databinding.FragmentAllBinding
import com.example.flashanime.databinding.FragmentCollectedBinding
import com.example.flashanime.ext.getVmFactory

class CollectedFragment: Fragment() {

    private val viewModel by viewModels<CollectedViewModel> { getVmFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentCollectedBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel





        return binding.root
    }
}