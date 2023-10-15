package com.example.flashanime.home.viewpage2.season

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.flashanime.NavigationDirections
import com.example.flashanime.databinding.FragmentSeasonBinding
import com.example.flashanime.ext.getVmFactory
import com.example.flashanime.hot.CarouselAdapter
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.FullScreenCarouselStrategy

class SeasonFragment: Fragment() {

    private val viewModel by viewModels<SeasonViewModel> { getVmFactory() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentSeasonBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val seasonAdapter = SeasonListAdapter{
            view?.findNavController()?.navigate(NavigationDirections.navigateToDetailFragment(it))
        }

        binding.recyclerView.adapter = seasonAdapter



        viewModel.combinedList.observe(viewLifecycleOwner) {animeInfo ->
            val sortedList = animeInfo.sortedBy { it.animeId }
            Log.i("animeListToCombine99", "combinedList: $animeInfo")
            seasonAdapter.submitList(sortedList)
        }



    // carousel view
        val carouseAdapter = CarouselAdapter{
            // click
        }

        binding.carouselRecyclerView.adapter = carouseAdapter

        viewModel.combinedList.observe(viewLifecycleOwner){
            carouseAdapter.submitList(it)
            viewModel.combinedListReady = true
            viewModel.callSetBackgroundColor()
        }

        val layoutManager = CarouselLayoutManager(FullScreenCarouselStrategy(), RecyclerView.HORIZONTAL)
        binding.carouselRecyclerView.layoutManager = layoutManager

        val snapHelper = CarouselSnapHelper()
        snapHelper.attachToRecyclerView(binding.carouselRecyclerView)


        binding.carouselRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val first= viewModel.findFirstVisibleItemPosition(layoutManager)?.toDouble()
                val last = viewModel.findLastVisibleItemPosition(layoutManager)?.toDouble()

                val average = ( first!! + last!!)/2

                val position = viewModel.currentPosition(average)

                Log.i("gogogo11","current: $position")

                if (position.toInt() != viewModel.currentNum.value!!){
                    viewModel.currentNum.value = position.toInt()
                }
            }
        })

        viewModel.currentNum.observe(viewLifecycleOwner){
            Log.i("finalNum","$it")
            viewModel.currentNumReady = true
            viewModel.callSetBackgroundColor()
        }

        viewModel.callColor.observe(viewLifecycleOwner){
            it?.let {
                val imageUrl = viewModel.setBackgroundColor()
                viewModel.bindImageMainWithPalette(binding.constraint,imageUrl)
            }
        }

        return binding.root
    }



}