package com.example.flashanime.wordstest

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat.getDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flashanime.MainViewModel
import com.example.flashanime.NavigationDirections
import com.example.flashanime.R
import com.example.flashanime.data.PlayWords
import com.example.flashanime.data.WordsCollection
import com.example.flashanime.databinding.FragmentWordtestBinding
import com.example.flashanime.ext.getVmFactory
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction

class WordTestFragment: Fragment() {

    private val viewModel by viewModels<WordTestViewModel> { getVmFactory(WordTestFragmentArgs.fromBundle(requireArguments()).wordTestInfoKey) }

    private lateinit var manager: CardStackLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentWordtestBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel


        // review recyclerview
        val wordReviewListAdapter = WordReviewListAdapter{
            viewModel.getWordInfoWordsTest(it.word)
        }
        binding.reviewRecyclerview.adapter = wordReviewListAdapter
        binding.reviewRecyclerview.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

        viewModel.reviewList.observe(viewLifecycleOwner){

            wordReviewListAdapter.submitList(it)
        }


        viewModel.platWordEpisode.observe(viewLifecycleOwner, Observer {
            val takeTen = it.playWordEpisode.playWords.shuffled().take(10)
            init(takeTen)
            binding.cardStackView.layoutManager = manager
            binding.cardStackView.itemAnimator = DefaultItemAnimator()

            binding.cardStackView.adapter = WordTestAdapter(requireContext(), takeTen, viewModel)

            viewModel.platWordEpisodeSize.value = takeTen.size.toString()
            Log.i("progressCircular11", "takeTen.size: ${takeTen.size}")
            Log.i("progressCircular11", "takeTen.size: $takeTen")

        })


        viewModel.isTesting.observe(viewLifecycleOwner){
            // progress circular
            binding.progressCircular.apply {
//                progressMax = 100f
//                setProgressWithAnimation(50f, 800)

                progressMax = viewModel.platWordEpisodeSize.value!!.toFloat()
                setProgressWithAnimation(viewModel.minusScore.value!!.toFloat(), 800)

                Log.i("progressCircular11", "minusScore: ${viewModel.minusScore.value!!.toFloat()}")
            }
        }
        binding.progressCircular.onProgressChangeListener = { progress ->
            Log.i("progressCircular", "$progress")
            viewModel.scorePercent.value = ((progress/viewModel.platWordEpisodeSize.value!!.toFloat())*100).toInt()
        }

        binding.buttonFinish.setOnClickListener {
            it.findNavController().navigateUp()
        }


        viewModel.wordInfoSelected.observe(viewLifecycleOwner){
            val targetWord = it.word
            val foundTime = viewModel.platWordEpisode.value!!.playWordEpisode.playWords.firstOrNull {
                it.word == targetWord }?.time

            val episodeNum = viewModel.platWordEpisode.value!!.playWordEpisode.episodeNum
            val wordsCollection = WordsCollection(
                viewModel.platWordEpisode.value!!.animeInfo.animeId,
                foundTime!!,
                viewModel.platWordEpisode.value!!.animeInfo.title,
                viewModel.platWordEpisode.value!!.animeInfo.pictureURL,
                episodeNum,
                it.word,
                false,
                false,
                it.meaning,
                it.furigana,
                it.romaji,
                viewModel.platWordEpisode.value!!.animeInfo.videosId[episodeNum.toInt()-1])

            findNavController().navigate(NavigationDirections.navigateToWordDialog(wordsCollection))
        }


        binding.instructions.setOnClickListener {
            it.visibility = View.GONE
        }

        // instruction Info
        var isChecked = false
        val mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        binding.noInstruction.setOnClickListener {
            isChecked = !isChecked
            if (isChecked) {
                binding.noInstruction.setIconResource(R.drawable.checkbox_true)
                mainViewModel.hideInstructionInfo = true
            } else {
                binding.noInstruction.setIconResource(R.drawable.checkbox_false)
                mainViewModel.hideInstructionInfo = false
            }
        }

        if (mainViewModel.hideInstructionInfo){
            binding.instructions.visibility = View.GONE
        }



        return binding.root
    }

    private fun init(playWords: List<PlayWords>) {
        val reviewWordsList = mutableListOf<WordsCollection>()

        manager = CardStackLayoutManager(requireContext(), object : CardStackListener{
            override fun onCardDragging(direction: Direction?, ratio: Float) {
            }

            override fun onCardSwiped(direction: Direction?) {

                when(direction) {
                    Direction.Left -> {
                        viewModel.addScore.value = viewModel.addScore.value?.plus(1)
                        viewModel.numerator.value = viewModel.numerator.value?.plus(1)

                        val targetWord = playWords[manager.topPosition - 1]
                        val episodeNum = viewModel.platWordEpisode.value!!.playWordEpisode.episodeNum
                        val wordsCollection = WordsCollection(
                            viewModel.platWordEpisode.value!!.animeInfo.animeId,
                            targetWord.time,
                            viewModel.platWordEpisode.value!!.animeInfo.title,
                            viewModel.platWordEpisode.value!!.animeInfo.pictureURL,
                            episodeNum,
                            targetWord.word,
                            targetWord.isCollected,
                            false,
                            "",
                            "",
                            "",
                            viewModel.platWordEpisode.value!!.animeInfo.videosId[episodeNum.toInt()-1])

                        reviewWordsList.add(wordsCollection)

                    }
                    Direction.Right -> {
                        viewModel.minusScore.value = viewModel.minusScore.value?.plus(1)
                        viewModel.numerator.value = viewModel.numerator.value?.plus(1)

                        val targetWord = playWords[manager.topPosition - 1]
                        val episodeNum = viewModel.platWordEpisode.value!!.playWordEpisode.episodeNum
                        val wordsCollection = WordsCollection(
                            viewModel.platWordEpisode.value!!.animeInfo.animeId,
                            targetWord.time,
                            viewModel.platWordEpisode.value!!.animeInfo.title,
                            viewModel.platWordEpisode.value!!.animeInfo.pictureURL,
                            episodeNum,
                            targetWord.word,
                            targetWord.isCollected,
                            true,
                            "",
                            "",
                            "",
                            viewModel.platWordEpisode.value!!.animeInfo.videosId[episodeNum.toInt()-1])

                        reviewWordsList.add(wordsCollection)

                    }
//                    Direction.Top -> Toast.makeText(requireContext(), "Swiped to Top", Toast.LENGTH_SHORT).show()
//                    Direction.Bottom -> Toast.makeText(requireContext(), "Swiped to Bottom", Toast.LENGTH_SHORT).show()
                    else -> {}
                }

                if (manager.topPosition == playWords.size){
//                    Toast.makeText(requireContext(), "this is last card", Toast.LENGTH_SHORT).show()
                    viewModel.isTesting.value = false
                    viewModel.reviewList.value = reviewWordsList
                }
            }

            override fun onCardRewound() {
            }

            override fun onCardCanceled() {
            }

            override fun onCardAppeared(view: View?, position: Int) {
            }

            override fun onCardDisappeared(view: View?, position: Int) {
            }
        })

        manager.setVisibleCount(3)
        manager.setTranslationInterval(0.6f)
        manager.setScaleInterval(0.8f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)
    }

}

