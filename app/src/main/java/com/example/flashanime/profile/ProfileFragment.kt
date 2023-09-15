package com.example.flashanime.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.flashanime.databinding.FragmentAllBinding
import com.example.flashanime.databinding.FragmentProfileBinding
import com.example.flashanime.ext.getVmFactory
import com.example.flashanime.home.HomeViewModel

class ProfileFragment: Fragment() {

    private val viewModel by viewModels<ProfileViewModel> { getVmFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel





        return binding.root
    }
}