package com.example.flashanime.wordstest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.PlayWordEpisode
import com.example.flashanime.data.PlayWords
import com.example.flashanime.data.source.FlashAnimeRepository

class WordTestViewModel(
    private val flashAnimeRepository: FlashAnimeRepository,
    private val animeInfoArg: PlayWordEpisode
): ViewModel() {

    // Detail has product data from arguments
    private val _platWordEpisode = MutableLiveData<PlayWordEpisode>().apply {
        value = animeInfoArg
    }
    val platWordEpisode: LiveData<PlayWordEpisode>
        get() = _platWordEpisode



    val finalScore = MutableLiveData<Int>()
}