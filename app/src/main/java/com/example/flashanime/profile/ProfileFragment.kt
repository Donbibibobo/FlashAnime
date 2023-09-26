package com.example.flashanime.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.flashanime.MainActivity
import com.example.flashanime.NavigationDirections
import com.example.flashanime.R
import com.example.flashanime.databinding.FragmentAllBinding
import com.example.flashanime.databinding.FragmentProfileBinding
import com.example.flashanime.ext.getVmFactory
import com.example.flashanime.home.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.random.Random

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




        // watch history

        // collected anime
        binding.collectedAnime.setOnClickListener {

        }

        // test history

        // about
        binding.about.setOnClickListener {
            viewModel.showAbout(requireContext())
        }

        // logout





        return binding.root
    }


}