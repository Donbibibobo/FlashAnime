package com.example.flashanime.home.viewpage2.week

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.flashanime.R
import com.example.flashanime.data.WeeklyAnime
import com.example.flashanime.databinding.FragmentWeekBinding
import com.example.flashanime.ext.getVmFactory

class WeekFragment: Fragment() {

    private val viewModel by viewModels<WeekViewModel> { getVmFactory() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentWeekBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        // mock data
        val weeklyAnimeSon1 = WeeklyAnime("01:05","黑暗集會","第10集")
        val weeklyAnimeSon2 = WeeklyAnime("21:30","LV1 魔王與獨居廢勇者","第11集")
        val weeklyAnimeMon1 = WeeklyAnime("23:56","咒術回戰 第二季", "第32集")
        val weeklyAnimeMon2 = WeeklyAnime("23:05","無職轉生～到了異世界就拿出真本事 第二季","第10集")
        val weeklyAnimeMon3 = WeeklyAnime("23:30","間諜教室","第22集")

        val adapter1 = WeekListAdapter()
        val adapter2 = WeekListAdapter()
        val adapter3 = WeekListAdapter()
        val adapter4 = WeekListAdapter()
        val adapter5 = WeekListAdapter()
        val adapter6 = WeekListAdapter()
        val adapter7 = WeekListAdapter()

        binding.R1.adapter = adapter1
        binding.R2.adapter = adapter2
        binding.R3.adapter = adapter3
        binding.R4.adapter = adapter4
        binding.R5.adapter = adapter5
        binding.R6.adapter = adapter6
        binding.R7.adapter = adapter7

        val weekListA = mutableListOf<WeeklyAnime>()
        val weekListB = mutableListOf<WeeklyAnime>()

        weekListA.add(weeklyAnimeSon1)
        weekListA.add(weeklyAnimeSon2)

        weekListB.add(weeklyAnimeMon1)
        weekListB.add(weeklyAnimeMon2)
        weekListB.add(weeklyAnimeMon3)

        adapter1.submitList(weekListA)
        adapter2.submitList(weekListB)
        adapter3.submitList(weekListA)
        adapter4.submitList(weekListA)
        adapter5.submitList(weekListB)
        adapter6.submitList(weekListA)
        adapter7.submitList(weekListA)

        val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider, null)?.let {
            dividerItemDecoration.setDrawable(it)
        }

        binding.R1.addItemDecoration(dividerItemDecoration)
        binding.R2.addItemDecoration(dividerItemDecoration)
        binding.R3.addItemDecoration(dividerItemDecoration)
        binding.R4.addItemDecoration(dividerItemDecoration)
        binding.R5.addItemDecoration(dividerItemDecoration)
        binding.R6.addItemDecoration(dividerItemDecoration)
        binding.R7.addItemDecoration(dividerItemDecoration)




        return binding.root
    }

}