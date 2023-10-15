package com.example.flashanime.hot

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.flashanime.databinding.FragmentHotBinding
import com.example.flashanime.ext.getVmFactory
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.FullScreenCarouselStrategy

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


//        val carouseAdapter = CarouselAdapter{
//            // click
//        }
//
//        binding.carouselRecyclerView.adapter = carouseAdapter
//
//        viewModel.combinedList.observe(viewLifecycleOwner){
//            carouseAdapter.submitList(it)
//            viewModel.combinedListReady = true
//            viewModel.callSetBackgroundColor()
//        }
//
//        val layoutManager = CarouselLayoutManager(FullScreenCarouselStrategy(), RecyclerView.HORIZONTAL)
//        binding.carouselRecyclerView.layoutManager = layoutManager
//
//        val snapHelper = CarouselSnapHelper()
//        snapHelper.attachToRecyclerView(binding.carouselRecyclerView)
//
//
//        binding.carouselRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//
//                val first= viewModel.findFirstVisibleItemPosition(layoutManager)?.toDouble()
//                val last = viewModel.findLastVisibleItemPosition(layoutManager)?.toDouble()
//
//                val average = ( first!! + last!!)/2
//
//                val position = viewModel.currentPosition(average)
//
//                Log.i("gogogo11","current: $position")
//
//                if (position.toInt() != viewModel.currentNum.value!!){
//                    viewModel.currentNum.value = position.toInt()
//                }
//            }
//        })
//
//        viewModel.currentNum.observe(viewLifecycleOwner){
//            Log.i("finalNum","$it")
//            viewModel.currentNumReady = true
//            viewModel.callSetBackgroundColor()
//        }
//
//        viewModel.callColor.observe(viewLifecycleOwner){
//            it?.let {
//                val imageUrl = viewModel.setBackgroundColor()
//                viewModel.bindImageMainWithPalette(binding.constraint,imageUrl,requireContext(),binding.paletteColor)
//            }
//        }











        return binding.root
    }





}