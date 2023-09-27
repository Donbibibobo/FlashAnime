package com.example.flashanime.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.flashanime.NavigationDirections
import com.example.flashanime.collected.CollectedListAdapter
import com.example.flashanime.collected.CollectedViewModel
import com.example.flashanime.databinding.FragmentCollectedBinding
import com.example.flashanime.databinding.FragmentHistoryBinding
import com.example.flashanime.ext.getVmFactory

class WatchHistoryFragment: Fragment() {

    private val viewModel by viewModels<WatchHistoryViewModel> { getVmFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHistoryBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val adapter = WatchHistoryListAdapter{
            view?.findNavController()?.navigate(NavigationDirections.navigateToDetailFragment(it))
        }

        binding.recyclerView.adapter = adapter


        viewModel.animeInfoList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })



        return binding.root
    }
}