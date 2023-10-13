package com.example.flashanime.hot

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.alpha
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.example.flashanime.R
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


        val adapter = CarouselAdapter{
            // click
        }

        binding.carouselRecyclerView.adapter = adapter

        viewModel.combinedList.observe(viewLifecycleOwner){
            adapter.submitList(it)
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
                viewModel.bindImageMainWithPalette(binding.constraint,imageUrl,requireContext(),binding.paletteColor)
            }
        }











        return binding.root
    }





}