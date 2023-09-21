package com.example.flashanime.vocabularydetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.flashanime.NavigationDirections
import com.example.flashanime.R
import com.example.flashanime.databinding.FragmentVocavularyDetailBinding
import com.example.flashanime.detail.DetailFragmentArgs
import com.example.flashanime.ext.getVmFactory
import com.google.android.material.textfield.TextInputLayout

class VocabularyDetailFragment: Fragment() {

    private val viewModel by viewModels<VocabularyDetailViewModel> { getVmFactory(VocabularyDetailFragmentArgs.fromBundle(requireArguments()).animeInfoKey) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentVocavularyDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val adapter = VocabularyDetailListAdapter{
            viewModel.getWordInfo(it.word)
        }

        // show word info from word API
        viewModel.wordInfoSelected.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(NavigationDirections.navigateToWordDialog(it))
        })


        binding.recyclerView.adapter = adapter

        val episodeCount = mutableListOf<String>()

        viewModel.animeInfoArg.observe(viewLifecycleOwner, Observer {
            binding.animeInfo = it
            adapter.submitList(it.wordsList[0].playWords)

            // set episode word
            for (i in 1..it.videosId.size){
                episodeCount.add("第${i}集")
            }
            val adapterTextInputLayout = ArrayAdapter(requireContext(), R.layout.text_dropdown_item, episodeCount)
            binding.autocomplete.setAdapter(adapterTextInputLayout)
            // default first
            binding.autocomplete.setText(episodeCount[0], false)

        })


        binding.autocomplete.setOnItemClickListener { _, _, position, _ ->
            adapter.submitList(viewModel.animeInfoArg.value!!.wordsList[position].playWords)
        }

        binding.testButton.setOnClickListener {
            it.findNavController().navigate(NavigationDirections.navigateToWordTestFragment())
        }



        return binding.root
    }
}