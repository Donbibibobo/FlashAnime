package com.example.flashanime.collected

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.source.FlashAnimeRepository


class CollectedViewModel(
    private val flashAnimeRepository: FlashAnimeRepository,
    private val fromProfile: Boolean
): ViewModel() {

    private val _combinedList: LiveData<List<AnimeInfo>> = flashAnimeRepository.getAllAnimeInfo()
    val combinedList: LiveData<List<AnimeInfo>>
        get() = _combinedList

    init {
        Log.i("aaasss", "$fromProfile")
    }
}