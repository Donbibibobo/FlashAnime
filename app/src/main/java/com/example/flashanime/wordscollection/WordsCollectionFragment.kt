package com.example.flashanime.wordscollection

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
            Log.i("WordsCollectionFragment", "$it")
        }
        binding.recyclerView.adapter = adapter


        viewModel.wordsCollectionList.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }


        return binding.root
    }
}