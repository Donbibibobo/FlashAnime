package com.example.flashanime.hot

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.flashanime.databinding.FragmentHotBinding
import com.example.flashanime.ext.getVmFactory

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




//        layoutManager.setCarouselStrategy()







        return binding.root
    }

}