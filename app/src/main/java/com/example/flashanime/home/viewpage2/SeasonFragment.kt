package com.example.flashanime.home.viewpage2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.flashanime.NavigationDirections
import com.example.flashanime.databinding.FragmentSeasonBinding
import com.example.flashanime.ext.getVmFactory

class SeasonFragment: Fragment() {

    private val viewModel by viewModels<SeasonViewModel> { getVmFactory() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentSeasonBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        // test video
        binding.textSeason.setOnClickListener{
            it.findNavController().navigate(NavigationDirections.navigateToDetailFragment())
        }






        return binding.root
    }

}