package com.example.flashanime.hot

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.source.FlashAnimeRepository

class HotViewModel(private val flashAnimeRepository: FlashAnimeRepository): ViewModel() {

    private val _combinedList: LiveData<List<AnimeInfo>> = flashAnimeRepository.getAllAnimeInfo()
    val combinedList: LiveData<List<AnimeInfo>>
        get() = _combinedList

}