package com.example.flashanime.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.source.FlashAnimeRepository

class DetailViewModel(
    private val flashAnimeRepository: FlashAnimeRepository,
    private val arguments: AnimeInfo
): ViewModel() {

    // Detail has product data from arguments
    private val _animeInfoArg = MutableLiveData<AnimeInfo>().apply {
        value = arguments
    }
    val animeInfoArg: LiveData<AnimeInfo>
        get() = _animeInfoArg

}