package com.example.flashanime.home.viewpage2.season

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.flashanime.MainActivity
import com.example.flashanime.NavigationDirections
import com.example.flashanime.databinding.FragmentSeasonBinding
import com.example.flashanime.ext.getVmFactory
import com.example.flashanime.home.HomeFragment
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.FullScreenCarouselStrategy
import com.google.android.material.tabs.TabLayout

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
        val circleAdapter = CircleAdapter()

        binding.recyclerView.adapter = seasonAdapter
        binding.recyclerCircles.adapter = circleAdapter



    // carousel view
        val carouseAdapter = CarouselAdapter{
            val animeInfo = viewModel.currentNum.value?.let { index ->
                viewModel.hotList?.get(index)
            }
            view?.findNavController()?.navigate(NavigationDirections.navigateToDetailFragment(animeInfo!!))
        }

        binding.carouselRecyclerView.adapter = carouseAdapter

        viewModel.combinedList.observe(viewLifecycleOwner){ animeInfo ->
            val sortedList = animeInfo.sortedBy { it.animeId }
            Log.i("animeListToCombine99", "combinedList: $animeInfo")
            seasonAdapter.submitList(sortedList)

            // select 4 top hot to carousel adapter
            val indices = setOf(2, 5, 6, 8)
            val hotList = sortedList.filterIndexed { index, _ ->
                index in indices
            }
            viewModel.hotList = hotList

            carouseAdapter.submitList(hotList)

            // original
            hotList.let {
                if (it.isEmpty().not()){
                    viewModel.hotListReady = true
                    viewModel.callSetBackgroundColor()

                    // circle adapter
                    val spotList = mutableListOf <CircleIsSelected>()
                    for(i in hotList.indices){
                        val spot = CircleIsSelected(i,false)
                        spotList.add(spot)
                    }
                    spotList[viewModel.currentNum.value!!].isSelected = true
                    Log.i("getListt","$spotList")
                    circleAdapter.submitList(spotList)
                }
            }
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

            // hot_button
            binding.hotPlay.setOnClickListener {
                val animeInfo = viewModel.hotList!![viewModel.currentNum.value!!]
                view?.findNavController()?.navigate(NavigationDirections.navigateToDetailFragment(animeInfo))
            }
        }

        viewModel.callColor.observe(viewLifecycleOwner){
            it?.let {
                val imageUrl = viewModel.setBackgroundColor()
                viewModel.bindImageMainWithPalette(binding.constraint,imageUrl, binding.paletteColor)
            }

            // circle adapter
            val spotList = mutableListOf <CircleIsSelected>()
            for(i in viewModel.hotList?.indices!!){
                val spot = CircleIsSelected(i,false)
                spotList.add(spot)
            }
            spotList[viewModel.currentNum.value!!].isSelected = true
            Log.i("getListt","$spotList")
            circleAdapter.submitList(spotList)
        }







        return binding.root
    }



}