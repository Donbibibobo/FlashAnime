package com.example.flashanime.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.example.flashanime.MainActivity
import com.example.flashanime.NavigationDirections
import com.example.flashanime.R
import com.example.flashanime.collected.CollectedFragmentDirections
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
        binding.watchHistory.setOnClickListener {
            it.findNavController().navigate(ProfileFragmentDirections.navigateToWatchHistoryFragment(),
                NavOptions.Builder()
                    .setEnterAnim(R.anim.enter_left_to_right)
                    .setExitAnim(R.anim.enter_right_to_left)
                    .build())
        }

        // collected anime
        binding.collectedAnime.setOnClickListener {
            it.findNavController().navigate(ProfileFragmentDirections.navigateToCollectedFragmentFromProfile(true),NavOptions.Builder()
                .setEnterAnim(R.anim.enter_left_to_right)
                .setExitAnim(R.anim.enter_right_to_left)
                .build())
        }

        // words collected
        binding.wordsCollection.setOnClickListener {
            it.findNavController().navigate(ProfileFragmentDirections.navigateToWordsCollectionFragment(),
                NavOptions.Builder()
                .setEnterAnim(R.anim.enter_left_to_right)
                .setExitAnim(R.anim.enter_right_to_left)
                .build())
        }

        // about
        binding.about.setOnClickListener {
            viewModel.showAbout(requireContext())
        }

        // logout
        binding.logout.setOnClickListener {
            it.findNavController().navigate(ProfileFragmentDirections.navigateToLoginDialog())
        }





        return binding.root
    }


}