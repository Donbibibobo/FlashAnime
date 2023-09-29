package com.example.flashanime.wordstest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.flashanime.data.PlayWords
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



        viewModel.platWordEpisode.observe(viewLifecycleOwner, Observer {
            val takeTen = it.playWords.shuffled().take(10)
            init(takeTen)
            binding.cardStackView.layoutManager = manager
            binding.cardStackView.itemAnimator = DefaultItemAnimator()

            binding.cardStackView.adapter = WordTestAdapter(requireContext(), takeTen)

            viewModel.platWordEpisodeSize.value = takeTen.size.toString()
        })


        // final score
        viewModel.numerator.observe(viewLifecycleOwner, Observer {
            if (it > 5){
//                binding.good.visibility = View.VISIBLE
//                binding.scoreGood.visibility = View.VISIBLE
//                binding.scoreGood.text = it.toString()
            } else {
//                binding.bad.visibility = View.VISIBLE
//                binding.scoreBad.visibility = View.VISIBLE
//                binding.scoreBad.text = it.toString()
            }
        })






        return binding.root
    }

    private fun init(playWords: List<PlayWords>) {
        manager = CardStackLayoutManager(requireContext(), object : CardStackListener{
            override fun onCardDragging(direction: Direction?, ratio: Float) {
            }

            override fun onCardSwiped(direction: Direction?) {

                when(direction) {
                    Direction.Left -> {
                        viewModel.addScore.value = viewModel.addScore.value?.plus(1)
                        viewModel.numerator.value = viewModel.numerator.value?.plus(1)
                    }
                    Direction.Right -> {
                        viewModel.minusScore.value = viewModel.minusScore.value?.plus(1)
                        viewModel.numerator.value = viewModel.numerator.value?.plus(1)
                    }
//                    Direction.Top -> Toast.makeText(requireContext(), "Swiped to Top", Toast.LENGTH_SHORT).show()
//                    Direction.Bottom -> Toast.makeText(requireContext(), "Swiped to Bottom", Toast.LENGTH_SHORT).show()
                    else -> {}
                }

                if (manager.topPosition == playWords.size){
                    Toast.makeText(requireContext(), "this is last card", Toast.LENGTH_SHORT).show()
                    viewModel.isTesting.value = false
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

