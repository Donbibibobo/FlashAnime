package com.example.flashanime.vocabulary

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.flashanime.NavigationDirections
import com.example.flashanime.R
import com.example.flashanime.databinding.FragmentAllBinding
import com.example.flashanime.databinding.FragmentVocabularyBinding
import com.example.flashanime.ext.getVmFactory

class VocabularyFragment: Fragment() {

    private val viewModel by viewModels<VocabularyViewModel> { getVmFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentVocabularyBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel


        val adapter = VocabularyListAdapter{
            findNavController().navigate(NavigationDirections.navigateToVocabularyDetailFragment(it),
                NavOptions.Builder()
                    .setEnterAnim(R.anim.enter_left_to_right)
                    .setExitAnim(R.anim.enter_right_to_left)
                    .build())
            Log.i("VocabularyFragment", "$it")
        }

        binding.recyclerView.adapter = adapter

        viewModel.combinedList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })




        return binding.root
    }
}