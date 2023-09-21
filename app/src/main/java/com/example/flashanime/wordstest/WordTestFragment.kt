package com.example.flashanime.wordstest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.flashanime.databinding.FragmentHomeBinding
import com.example.flashanime.databinding.FragmentWordtestBinding
import com.example.flashanime.ext.getVmFactory
import com.example.flashanime.home.HomeViewModel
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction

class WordTestFragment: Fragment() {

    private val viewModel by viewModels<WordTestViewModel> { getVmFactory() }

    private lateinit var manager: CardStackLayoutManager
    val list = listOf<String>("AAA","BBB","AAA")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentWordtestBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel


//        val list = listOf<String>("AAA","BBB","AAA","BBB","AAA","BBB","AAA","BBB")


        init()

        binding.cardStackView.layoutManager = manager
        binding.cardStackView.itemAnimator = DefaultItemAnimator()
        binding.cardStackView.adapter = WordTestAdapter(requireContext(), list)



        return binding.root
    }

    private fun init() {
        manager = CardStackLayoutManager(requireContext(), object : CardStackListener{
            override fun onCardDragging(direction: Direction?, ratio: Float) {
            }

            override fun onCardSwiped(direction: Direction?) {

                when(direction) {
                    Direction.Left -> Toast.makeText(requireContext(), "Swiped to Left", Toast.LENGTH_SHORT).show()
                    Direction.Right -> Toast.makeText(requireContext(), "Swiped to Right", Toast.LENGTH_SHORT).show()
                    Direction.Top -> Toast.makeText(requireContext(), "Swiped to Top", Toast.LENGTH_SHORT).show()
                    Direction.Bottom -> Toast.makeText(requireContext(), "Swiped to Bottom", Toast.LENGTH_SHORT).show()
                    else -> {}
                }

                if (manager.topPosition == list.size){
                    Toast.makeText(requireContext(), "this is last card", Toast.LENGTH_SHORT).show()
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

