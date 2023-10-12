package com.example.flashanime.hot

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.flashanime.databinding.FragmentHotBinding
import com.example.flashanime.ext.getVmFactory
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.FullScreenCarouselStrategy
import com.google.android.material.carousel.HeroCarouselStrategy

class HotFragment: Fragment() {

    private val viewModel by viewModels<HotViewModel> { getVmFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHotBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel


        val adapter = CarouselAdapter{
            Log.i("CarouselAdapter", "CarouselAdapter tab")
        }

        binding.carouselRecyclerView.adapter = adapter

        viewModel.combinedList.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        val layoutManager = CarouselLayoutManager(FullScreenCarouselStrategy(), RecyclerView.HORIZONTAL)
        binding.carouselRecyclerView.layoutManager = layoutManager

//        layoutManager.setCarouselAlignment(CarouselLayoutManager.CENTER)
        val snapHelper = CarouselSnapHelper()
        snapHelper.attachToRecyclerView(binding.carouselRecyclerView)








        return binding.root
    }

}