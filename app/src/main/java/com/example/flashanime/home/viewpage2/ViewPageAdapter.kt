package com.example.flashanime.home.viewpage2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter

private const val ITEM_COUNT = 2
private const val FRAGMENT_SEASON = 0
private const val FRAGMENT_WEEK = 1

class ViewPageAdapter(
    fragmentManager: FragmentManager,
    lifecycle: androidx.lifecycle.Lifecycle
): FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return ITEM_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            FRAGMENT_SEASON -> SeasonFragment()
            FRAGMENT_WEEK -> WeekFragment()
            else -> throw IllegalArgumentException("No such fragment exist.")
        }
    }
}