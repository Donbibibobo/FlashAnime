package com.example.flashanime.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.flashanime.NavigationDirections
import com.example.flashanime.R
import com.example.flashanime.databinding.FragmentHomeBinding
import com.example.flashanime.ext.getVmFactory
import com.example.flashanime.home.viewpage2.ViewPageAdapter
import com.example.flashanime.util.Util.getString
import com.google.android.material.tabs.TabLayoutMediator

private const val FRAGMENT_SEASON = 0
private const val FRAGMENT_WEEK = 1
class HomeFragment: Fragment() {

    private val viewModel by viewModels<HomeViewModel> { getVmFactory() }

    private lateinit var adapter: FragmentStateAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        // view page adapter
        adapter = ViewPageAdapter(childFragmentManager, lifecycle)

        // add adapter to viewpage
        binding.viewPager2.adapter = adapter

        // connect TabLayout and ViewPager2
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) {tab, position ->
            when (position) {
                FRAGMENT_SEASON -> tab.text = getString(R.string.first_tab_season)
                FRAGMENT_WEEK -> tab.text = getString(R.string.first_tab_week)
            }
        }.attach()










        return binding.root
    }

}