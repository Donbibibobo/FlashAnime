package com.example.flashanime.wordscollection.wordscollectiondialog

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.WordsCollection
import com.example.flashanime.data.source.FlashAnimeRepository

class WordsCollectionDialogViewModel (
    private val flashAnimeRepository: FlashAnimeRepository,
    private val wordsCollectionArgs: WordsCollection
) : ViewModel() {

    private val _wordsCollection = MutableLiveData<WordsCollection>().apply {
            value = wordsCollectionArgs
    }
    val wordsCollection: LiveData<WordsCollection>
        get() = _wordsCollection



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