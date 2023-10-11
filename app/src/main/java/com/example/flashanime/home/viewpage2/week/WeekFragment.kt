package com.example.flashanime.home.viewpage2.week

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.flashanime.NavigationDirections
import com.example.flashanime.R
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

        val adapter1 = WeekListAdapter{
            viewModel.navigateByAnimeId(it)
        }
        val adapter2 = WeekListAdapter{
            viewModel.navigateByAnimeId(it)
        }
        val adapter3 = WeekListAdapter{
            viewModel.navigateByAnimeId(it)
        }
        val adapter4 = WeekListAdapter{
            viewModel.navigateByAnimeId(it)
        }
        val adapter5 = WeekListAdapter{
            viewModel.navigateByAnimeId(it)
        }
        val adapter6 = WeekListAdapter{
            viewModel.navigateByAnimeId(it)
        }
        val adapter7 = WeekListAdapter{
            viewModel.navigateByAnimeId(it)
        }

        binding.R1.adapter = adapter1
        binding.R2.adapter = adapter2
        binding.R3.adapter = adapter3
        binding.R4.adapter = adapter4
        binding.R5.adapter = adapter5
        binding.R6.adapter = adapter6
        binding.R7.adapter = adapter7

        // recyclerView divider
        val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        val colorBg = ContextCompat.getColor(requireContext(), R.color.new_bg)
        ResourcesCompat.getDrawable(resources, R.drawable.divider, null)?.let {
            it.setTint(colorBg)
            dividerItemDecoration.setDrawable(it)
        }
        binding.R1.addItemDecoration(dividerItemDecoration)
        binding.R2.addItemDecoration(dividerItemDecoration)
        binding.R3.addItemDecoration(dividerItemDecoration)
        binding.R4.addItemDecoration(dividerItemDecoration)
        binding.R5.addItemDecoration(dividerItemDecoration)
        binding.R6.addItemDecoration(dividerItemDecoration)
        binding.R7.addItemDecoration(dividerItemDecoration)

        viewModel.weekInfo.observe(viewLifecycleOwner, Observer { weeklyInfos ->
            weeklyInfos?.let {
                if(it.isNotEmpty()){
                    // because only have one weeklyInfo
                    val weeklyInfo = it[0]

                    adapter1.submitList(weeklyInfo.monday.weeklyAnimeList)
                    adapter2.submitList(weeklyInfo.tuesday.weeklyAnimeList)
                    adapter3.submitList(weeklyInfo.wednesday.weeklyAnimeList)
                    adapter4.submitList(weeklyInfo.thursday.weeklyAnimeList)
                    adapter5.submitList(weeklyInfo.friday.weeklyAnimeList)
                    adapter6.submitList(weeklyInfo.saturday.weeklyAnimeList)
                    adapter7.submitList(weeklyInfo.sunday.weeklyAnimeList)
                }
            }
        })

        viewModel.selectedAnimeInfo.observe(viewLifecycleOwner, Observer {
            if (it != null){
                NavHostFragment.findNavController(this).navigate(NavigationDirections.navigateToDetailFragment(it))
                viewModel.navigateComplete()
            }

        })



        binding.nav2hot.setOnClickListener {
            it.findNavController().navigate(NavigationDirections.navigateToHotFragment())
        }

        return binding.root
    }

}