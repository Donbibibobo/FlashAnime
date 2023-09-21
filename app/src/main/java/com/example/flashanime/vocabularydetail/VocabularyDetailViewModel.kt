package com.example.flashanime.vocabularydetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.JLPTWordInfo
import com.example.flashanime.data.source.FlashAnimeRepository
import kotlinx.coroutines.launch

class VocabularyDetailViewModel(
    private val flashAnimeRepository: FlashAnimeRepository,
    private val arguments: AnimeInfo
): ViewModel() {

    // Detail has product data from arguments
    private val _animeInfoArg = MutableLiveData<AnimeInfo>().apply {
        value = arguments
    }
    val animeInfoArg: LiveData<AnimeInfo>
        get() = _animeInfoArg

    // episode recyclerview list constrain selected word API
    private val _wordInfoSelected = MutableLiveData<JLPTWordInfo>()
    val wordInfoSelected: LiveData<JLPTWordInfo>
        get() = _wordInfoSelected


    fun getWordInfo(word: String) {
        viewModelScope.launch {
            try {
                val wordInfo = flashAnimeRepository.getWordInfo(word)
                Log.i("getWordInfo", wordInfo.words[0].romaji)
                _wordInfoSelected.value = wordInfo.words[0]
            } catch (e: Exception) {
                throw IllegalArgumentException("DetailViewModel getWordInfo fail!")
            }
        }
    }

}