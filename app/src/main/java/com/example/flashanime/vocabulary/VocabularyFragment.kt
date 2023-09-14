package com.example.flashanime.vocabulary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flashanime.databinding.FragmentAllBinding
import com.example.flashanime.databinding.FragmentVocabularyBinding

class VocabularyFragment: Fragment() {

    //    private val viewModel by viewModels<HomeViewModel> { getVmFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentVocabularyBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
//        binding.viewModel = viewModel





        return binding.root
    }
}