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
import androidx.recyclerview.widget.RecyclerView
import com.example.flashanime.MainActivity
import com.example.flashanime.NavigationDirections
import com.example.flashanime.R
import com.example.flashanime.data.PlayWordEpisode
import com.example.flashanime.data.PlayWordEpisodePlusAnimeInfo
import com.example.flashanime.data.PlayWords
import com.example.flashanime.data.WordsCollection
import com.example.flashanime.databinding.FragmentVocavularyDetailBinding
import com.example.flashanime.detail.DetailFragmentArgs
import com.example.flashanime.ext.getVmFactory
import com.google.android.material.textfield.TextInputLayout
import kotlin.random.Random

class VocabularyDetailFragment: Fragment() {

    private val viewModel by viewModels<VocabularyDetailViewModel> { getVmFactory(VocabularyDetailFragmentArgs.fromBundle(requireArguments()).animeInfoKey) }

    private lateinit var binding: FragmentVocavularyDetailBinding
    private lateinit var adapter: VocabularyDetailListAdapter
    private lateinit var dataObserver: RecyclerView.AdapterDataObserver
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentVocavularyDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel


        // ListAdapter
        adapter = VocabularyDetailListAdapter{
            viewModel.getWordInfoVocabulary(it.word)

            // this is for words collection
            viewModel.playWords.value = it
        }
        binding.recyclerView.adapter = adapter

        // let recyclerview scroll to top
        dataObserver = object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                binding.recyclerView.scrollToPosition(0)
            }
        }
        adapter.registerAdapterDataObserver(dataObserver)


        // show word info from word API
        viewModel.wordInfoSelected.observe(viewLifecycleOwner, Observer {
            it?.let {
                val episodeNum = viewModel.animeInfoArg.value!!.wordsList[viewModel.autocompletePosition].episodeNum
                val wordsCollection = WordsCollection(
                    viewModel.animeInfoArg.value!!.animeId,
                    viewModel.playWords.value!!.time,
                    viewModel.animeInfoArg.value!!.title,
                    viewModel.animeInfoArg.value!!.pictureURL,
                    episodeNum,
                    it.word,
                    false,
                    false,
                    it.meaning,
                    it.furigana,
                    it.romaji,
                    viewModel.animeInfoArg.value!!.videosId[episodeNum.toInt()-1])
                findNavController().navigate(NavigationDirections.navigateToWordDialog(wordsCollection))
            }
        })

        // test list
        var testList: PlayWordEpisode? = viewModel.animeInfoArg.value!!.wordsList[0]


        // init default episode 1
        viewModel.animeInfoArg.observe(viewLifecycleOwner, Observer {
            Log.i("OMGG","cccl")
            if (binding.radioGroup.checkedRadioButtonId == R.id.mode_all){
                binding.animeInfo = it

                Log.i("OMGG","cccl1")

                Log.i("findList","1: ${it.wordsList[0].playWords}")

                adapter.submitList(it.wordsList[viewModel.autocompletePosition].playWords)
            } else {
                Log.i("OMGG","cccl2")

                // new list
                val collectedList =
                    viewModel.animeInfoArg.value!!.wordsList[viewModel.autocompletePosition].playWords
                        .filter { it.isCollected }

                Log.i("findList","2: $collectedList")
                if (collectedList.isEmpty()){
                    Log.i("catchAll","C")

                    binding.radioGroup.check(R.id.mode_all)
                    Log.i("catchAll","1")
                }else{
                    adapter.submitList(collectedList)
                    Log.i("catchAll","D")

                }

                // new test list
                val currentEpisode = viewModel.animeInfoArg.value!!.wordsList[viewModel.autocompletePosition]
                val updatedPlayWords = currentEpisode.playWords.filter { it.isCollected }
                val updatedEpisode = currentEpisode.copy(playWords = updatedPlayWords)
                testList = updatedEpisode
            }

            Log.i("autocompletePosition","autocompletePosition1: ${viewModel.autocompletePosition}")

            val episodeCount = mutableListOf<String>()
            // set episode word
            Log.i("episoedd", "${it.videosId.size}")
            for (i in 1..it.videosId.size){
                val episodeString = getString(R.string.episodeCount, i)
                episodeCount.add(episodeString)
            }
            val adapterTextInputLayout = ArrayAdapter(requireContext(), R.layout.text_dropdown_item, episodeCount)
            binding.autocomplete.setAdapter(adapterTextInputLayout)
            binding.autocomplete.setText(episodeCount[viewModel.autocompletePosition], false)

            Log.i("autocompletePosition","autocompletePosition2: ${viewModel.autocompletePosition}")

        })


        // change list according to autocomplete
        binding.autocomplete.setOnItemClickListener { _, _, position, _ ->
            // current episode position
            viewModel.autocompletePosition = position
            Log.i("autocompletePosition","autocompletePositionNow: ${viewModel.autocompletePosition}")


            // back to default mode_all
            binding.radioGroup.check(R.id.mode_all)
            Log.i("catchAll","2")

            // close [start test] if it doesn't have content
            binding.testButton.isEnabled =
                viewModel.animeInfoArg.value!!.wordsList[position].playWords[0].level != ""

            // set new list
            Log.i("findList","3: ${viewModel.animeInfoArg.value!!.wordsList[position].playWords}")
            adapter.submitList(viewModel.animeInfoArg.value!!.wordsList[position].playWords)

            // set test list
            testList = viewModel.animeInfoArg.value!!.wordsList[position]
            Log.i("testList","$testList")
        }

        binding.testButton.setOnClickListener {
            viewModel.hasCollectedWords = false
            Log.i("testListFinal","list: $testList")
            Log.i("testListFinal","animeInfo: ${viewModel.animeInfoArg.value!!.animeId}")

            val playWordEpisodePlusAnimeInfo = PlayWordEpisodePlusAnimeInfo(testList!!,viewModel.animeInfoArg.value!!)

            it.findNavController().navigate(NavigationDirections.navigateToWordTestFragment(playWordEpisodePlusAnimeInfo))
//            it.findNavController().navigate(NavigationDirections.navigateToWordTestFragment(testList!!))
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
                        Log.i("catchAll","3")
                        binding.dialogBackground.visibility = View.VISIBLE
                        viewModel.showNoCollectedWordsAlert(requireContext())
                    }else{
                        Log.i("findList","4: $collectedList")
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

        viewModel.leaveDialog.observe(viewLifecycleOwner){
            it?.let {
                binding.dialogBackground.visibility = View.GONE
                viewModel.leaveDialogComplete()
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
        Log.i("catchAll","4")
    }

    override fun onDestroy() {
        adapter.unregisterAdapterDataObserver(dataObserver)
        super.onDestroy()
    }
}