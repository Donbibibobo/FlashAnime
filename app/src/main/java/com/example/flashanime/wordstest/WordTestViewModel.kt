package com.example.flashanime.wordstest

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashanime.R
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.JLPTWordInfo
import com.example.flashanime.data.PlayWordEpisode
import com.example.flashanime.data.PlayWords
import com.example.flashanime.data.source.FlashAnimeRepository
import com.example.flashanime.util.Util
import kotlinx.coroutines.launch

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

    // JLPt API
    private val _wordInfoSelected = MutableLiveData<JLPTWordInfo>()
    val wordInfoSelected: LiveData<JLPTWordInfo>
        get() = _wordInfoSelected


    fun getWordInfoWordsTest(word: String) {
        viewModelScope.launch {
            try {
                val wordInfo = flashAnimeRepository.getWordInfo(word)
                Log.i("getWordInfo", wordInfo.words[0].romaji)
                _wordInfoSelected.value = wordInfo.words[0]
            } catch (e: Exception) {
                Log.i("DetailViewModel", "getWordInfo failed with exception: $e")
//                throw IllegalArgumentException("DetailViewModel getWordInfo fail!")
            }
        }
    }

}