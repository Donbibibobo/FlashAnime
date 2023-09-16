package com.example.flashanime.home.viewpage2.season

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.flashanime.NavigationDirections
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.databinding.FragmentSeasonBinding
import com.example.flashanime.ext.getVmFactory

class SeasonFragment: Fragment() {

    private val viewModel by viewModels<SeasonViewModel> { getVmFactory() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentSeasonBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        // test video
        binding.textSeason.setOnClickListener{
            it.findNavController().navigate(NavigationDirections.navigateToDetailFragment())
        }

        // mock data
        val wordsList = listOf<String>("同感","家")
        val category = listOf<String>("奇幻","搞笑")
        val animeInfo1 = AnimeInfo(false,
            "黑暗集會",
            "09/11 (一) 01:05",
            "第一集",
            "4.5",
            wordsList,
            category,
            "https://vpx34.myself-bbs.com/hls/yw/kA/As/AgADywkAAsU4WVU/index.m3u8",
            "https://myself-bbs.com/data/attachment/forum/202307/10/103635cficxop2wccjcf2v.jpg"
            )

        val adapter = SeasonListAdapter()
        binding.recyclerView.adapter = adapter

        val animeInfoList = mutableListOf<AnimeInfo>()
        animeInfoList.add(animeInfo1)
        animeInfoList.add(animeInfo1)
        animeInfoList.add(animeInfo1)
        animeInfoList.add(animeInfo1)

        adapter.submitList(animeInfoList)






        return binding.root
    }

}