package com.example.flashanime.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.flashanime.NavigationDirections
import com.example.flashanime.databinding.FragmentHomeBinding
import com.example.flashanime.ext.getVmFactory

class HomeFragment: Fragment() {

    private val viewModel by viewModels<HomeViewModel> { getVmFactory() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel


        binding.HomeText.setOnClickListener{
            it.findNavController().navigate(NavigationDirections.navigateToDetailFragment())
        }



        return binding.root
    }

}