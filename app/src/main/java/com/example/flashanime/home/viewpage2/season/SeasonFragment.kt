package com.example.flashanime.home.viewpage2.season

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.flashanime.NavigationDirections
import com.example.flashanime.data.AnimeInfo
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



        val adapter = SeasonListAdapter{
            view?.findNavController()?.navigate(NavigationDirections.navigateToDetailFragment())
        }

        binding.recyclerView.adapter = adapter

        viewModel.animeInfo.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })






        return binding.root
    }

}