package com.example.flashanime.all

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.source.FlashAnimeRepository

class AllViewModel(private val flashAnimeRepository: FlashAnimeRepository): ViewModel() {

    private val _animeInfo = MutableLiveData<List<AnimeInfo>>()
    val animeInfo: LiveData<List<AnimeInfo>>
        get() = _animeInfo

}