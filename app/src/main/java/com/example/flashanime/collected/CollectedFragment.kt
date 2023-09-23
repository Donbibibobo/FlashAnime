package com.example.flashanime.collected

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.flashanime.NavigationDirections
import com.example.flashanime.TemporaryFile
import com.example.flashanime.all.AllListAdapter
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

        val adapter = CollectedListAdapter{
            view?.findNavController()?.navigate(NavigationDirections.navigateToDetailFragment(it))
        }

        binding.recyclerView.adapter = adapter

        // get TempoInfo


        viewModel.combinedList.observe(viewLifecycleOwner, Observer {
            val collectedList = it.filter { animeInfo ->
                animeInfo.isCollected
            }
            adapter.submitList(collectedList)
        })



        return binding.root
    }
}