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


    fun timeToSeconds(time: String): Float {
        val timeParts = time.split(":", ".")

        val hours = timeParts[0].toInt()
        val minutes = timeParts[1].toInt()
        val seconds = timeParts[2].toInt()
        val milliseconds = timeParts[3].toInt()

        return (hours * 3600 + minutes * 60 + seconds + milliseconds / 100.0).toFloat()
    }


}