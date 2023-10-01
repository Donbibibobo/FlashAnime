package com.example.flashanime.wordstest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flashanime.R
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.PlayWordEpisode
import com.example.flashanime.data.PlayWords
import com.example.flashanime.data.source.FlashAnimeRepository
import com.example.flashanime.util.Util

class WordTestViewModel(
    private val flashAnimeRepository: FlashAnimeRepository,
    private val animeInfoArg: PlayWordEpisode
): ViewModel() {

    // review list
    val reviewList = MutableLiveData<List<PlayWords>>()

    // Detail has product data from arguments
    private val _platWordEpisode = MutableLiveData<PlayWordEpisode>().apply {
        value = animeInfoArg
    }
    val platWordEpisode: LiveData<PlayWordEpisode>
        get() = _platWordEpisode

    // isTesting
    val isTesting = MutableLiveData<Boolean>(true)


    // show on score_denominator
    val platWordEpisodeSize = MutableLiveData<String>()


    // add score
    val addScore = MutableLiveData<Int>(0)

    // minus score
    val minusScore = MutableLiveData<Int>(0)

    // score's numerator
    val numerator = MutableLiveData<Int>(0)


    // score percent
    val scorePercent = MutableLiveData<Int>()




}