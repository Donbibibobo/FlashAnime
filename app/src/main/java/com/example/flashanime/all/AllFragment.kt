package com.example.flashanime.all

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.flashanime.NavigationDirections
import com.example.flashanime.TemporaryFile
import com.example.flashanime.databinding.FragmentAllBinding
import com.example.flashanime.databinding.FragmentHomeBinding
import com.example.flashanime.ext.getVmFactory
import com.example.flashanime.home.HomeViewModel
import com.example.flashanime.home.viewpage2.season.SeasonListAdapter

class AllFragment: Fragment() {

    private val viewModel by viewModels<AllViewModel> { getVmFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentAllBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val adapter = AllListAdapter{
            view?.findNavController()?.navigate(NavigationDirections.navigateToDetailFragment(it))
        }

        binding.recyclerView.adapter = adapter

        adapter.submitList(TemporaryFile.TempoAnimeInfo)








        return binding.root
    }

}