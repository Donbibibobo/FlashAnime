package com.example.flashanime.all.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flashanime.data.JLPTWordInfo
import com.example.flashanime.data.source.FlashAnimeRepository

class CategoryViewModel(private val flashAnimeRepository: FlashAnimeRepository) : ViewModel() {



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