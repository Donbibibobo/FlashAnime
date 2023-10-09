package com.example.flashanime.wordscollection

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.flashanime.NavigationDirections
import com.example.flashanime.databinding.FragmentWordsCollectedBinding
import com.example.flashanime.ext.getVmFactory

class WordsCollectionFragment: Fragment() {

    private val viewModel by viewModels<WordsCollectionViewModel> { getVmFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentWordsCollectedBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel


        val adapter = WordsCollectionListAdapter{
            viewModel.getWordInfoWordsCollection(it.word)
            Log.i("newNew","newNew")
        }
        binding.recyclerView.adapter = adapter


        viewModel.wordsCollectionList.observe(viewLifecycleOwner){

            adapter.submitList(it)
        }

        viewModel.wordInfoSelected.observe(viewLifecycleOwner){

//            findNavController().navigate(NavigationDirections.navigateToWordDialog(it))
        }




        return binding.root
    }
}