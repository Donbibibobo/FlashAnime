package com.example.flashanime.word

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.JLPTWordInfo
import com.example.flashanime.data.source.FlashAnimeRepository

class WordViewModel(
    private val flashAnimeRepository: FlashAnimeRepository,
    private val wordInfo: JLPTWordInfo
) : ViewModel() {

    // arguments from detail word
    private val _wordInfoArg = MutableLiveData<JLPTWordInfo>().apply {
        value = wordInfo
    }
    val wordInfoArg: LiveData<JLPTWordInfo>
        get() = _wordInfoArg

    // handle leave dialog
    private val _leave = MutableLiveData<Boolean>()
    val leave: LiveData<Boolean>
        get() = _leave

    // leave dialog
    fun leave() {
        _leave.value = true
    }

    fun onLeaveCompleted() {
        _leave.value = null
    }

}