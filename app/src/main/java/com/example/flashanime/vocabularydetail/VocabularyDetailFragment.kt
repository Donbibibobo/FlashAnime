package com.example.flashanime.vocabularydetail

import android.app.AlertDialog
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
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.flashanime.MainActivity
import com.example.flashanime.NavigationDirections
import com.example.flashanime.R
import com.example.flashanime.data.PlayWordEpisode
import com.example.flashanime.data.PlayWords
import com.example.flashanime.databinding.FragmentVocavularyDetailBinding
import com.example.flashanime.detail.DetailFragmentArgs
import com.example.flashanime.ext.getVmFactory
import com.google.android.material.textfield.TextInputLayout
import kotlin.random.Random

class VocabularyDetailFragment: Fragment() {

    private val viewModel by viewModels<VocabularyDetailViewModel> { getVmFactory(VocabularyDetailFragmentArgs.fromBundle(requireArguments()).animeInfoKey) }

    private lateinit var binding: FragmentVocavularyDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentVocavularyDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel


        // ListAdapter
        val adapter = VocabularyDetailListAdapter{
            viewModel.getWordInfoVocabulary(it.word)
        }
        binding.recyclerView.adapter = adapter


        // show word info from word API
        viewModel.wordInfoSelected.observe(viewLifecycleOwner, Observer {
            it?.let {
                findNavController().navigate(NavigationDirections.navigateToWordDialog(it))
            }
        })

        // test list
        var testList: PlayWordEpisode? = viewModel.animeInfoArg.value!!.wordsList[0]


        // init default episode 1
        val episodeCount = mutableListOf<String>()
        viewModel.animeInfoArg.observe(viewLifecycleOwner, Observer {
            if (binding.radioGroup.checkedRadioButtonId == R.id.mode_all){
                binding.animeInfo = it
                adapter.submitList(it.wordsList[0].playWords)
            } else {
                // new list
                val collectedList =
                    viewModel.animeInfoArg.value!!.wordsList[viewModel.autocompletePosition].playWords
                        .filter { it.isCollected }
                adapter.submitList(collectedList)

                // new test lsit
                val currentEpisode = viewModel.animeInfoArg.value!!.wordsList[viewModel.autocompletePosition]
                val updatedPlayWords = currentEpisode.playWords.filter { it.isCollected }
                val updatedEpisode = currentEpisode.copy(playWords = updatedPlayWords)
                testList = updatedEpisode
            }


            // set episode word
            for (i in 1..it.videosId.size){
                episodeCount.add("第${i}集")
            }
            val adapterTextInputLayout = ArrayAdapter(requireContext(), R.layout.text_dropdown_item, episodeCount)
            binding.autocomplete.setAdapter(adapterTextInputLayout)
            binding.autocomplete.setText(episodeCount[0], false)
        })


        // change list according to autocomplete
        binding.autocomplete.setOnItemClickListener { _, _, position, _ ->
            // current episode position
            viewModel.autocompletePosition = position

            // back to default mode_all
            binding.radioGroup.check(R.id.mode_all)

            // close [start test] if it doesn't have content
            binding.testButton.isEnabled =
                viewModel.animeInfoArg.value!!.wordsList[position].playWords[0].level != ""

            // set new list
            adapter.submitList(viewModel.animeInfoArg.value!!.wordsList[position].playWords)

            // set test list
            testList = viewModel.animeInfoArg.value!!.wordsList[position]
            Log.i("testList","$testList")
        }

        binding.testButton.setOnClickListener {
            viewModel.hasCollectedWords = false
            it.findNavController().navigate(NavigationDirections.navigateToWordTestFragment(testList!!))
//            viewModel.removeListenerRegistration()
        }

        // default first page check if have words
        binding.testButton.isEnabled =
            viewModel.animeInfoArg.value!!.wordsList[0].playWords[0].level != ""

        // get collected words and refresh listAdapter
        viewModel.collectedWordsList.observe(viewLifecycleOwner){
            Log.i("collectedList", "collectedWordsList.observe")
            Log.i("collectedList", "O:_collectedWordsList ${viewModel.collectedWordsList.value}")
            viewModel.createCollectedWordList()

        }

        // radioGroup
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.mode_all -> {
                    // set new list
                    adapter.submitList(viewModel.animeInfoArg.value!!.wordsList[viewModel.autocompletePosition].playWords)

                    // set test list
                    testList = viewModel.animeInfoArg.value!!.wordsList[viewModel.autocompletePosition]

                }
                R.id.mode_collected -> {
                    // set new list
                    val collectedList =
                        viewModel.animeInfoArg.value!!.wordsList[viewModel.autocompletePosition].playWords
                            .filter { it.isCollected }
                    if (collectedList.isEmpty()){
                        binding.radioGroup.check(R.id.mode_all)
                        viewModel.showNoCollectedWordsAlert(requireContext())
                    }else{
                        adapter.submitList(collectedList)

                        // set test list
                        val currentEpisode = viewModel.animeInfoArg.value!!.wordsList[viewModel.autocompletePosition]
                        val updatedPlayWords = currentEpisode.playWords.filter { it.isCollected }
                        val updatedEpisode = currentEpisode.copy(playWords = updatedPlayWords)
                        testList = updatedEpisode
                    }
                }
            }
        }

        return binding.root
    }

    override fun onStop() {
        super.onStop()
        viewModel.resetWordInfoSelected()
    }

    override fun onStart() {
        super.onStart()
        binding.radioGroup.check(R.id.mode_all)
    }
}