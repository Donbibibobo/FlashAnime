package com.example.flashanime.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.flashanime.R
import com.example.flashanime.databinding.FragmentHomeBinding
import com.example.flashanime.ext.getVmFactory
import com.example.flashanime.home.viewpage2.ViewPageAdapter
import com.google.android.material.tabs.TabLayout
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
        binding.viewPager2.isUserInputEnabled = false


        // set custom text style
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            val textView = LayoutInflater.from(requireContext()).inflate(R.layout.custom_tab_item, null) as TextView
            when (position) {
                FRAGMENT_SEASON -> textView.text = getString(R.string.first_tab_season)
                FRAGMENT_WEEK -> textView.text = getString(R.string.first_tab_week)
            }
            tab.customView = textView
        }.attach()

        // change text color when select
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.customView?.findViewById<TextView>(R.id.tabTextView)?.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.black))
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.customView?.findViewById<TextView>(R.id.tabTextView)?.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.new_text))
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })

        // set tab text color when init
        for (i in 0 until binding.tabLayout.tabCount) {
            val tab = binding.tabLayout.getTabAt(i)
            if (tab?.isSelected == true) {
                tab.customView?.findViewById<TextView>(R.id.tabTextView)?.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.black))
            } else {
                tab?.customView?.findViewById<TextView>(R.id.tabTextView)?.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.new_text))
            }
        }








        return binding.root
    }

}