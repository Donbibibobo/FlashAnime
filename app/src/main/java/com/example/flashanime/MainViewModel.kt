package com.example.flashanime

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flashanime.data.source.FlashAnimeRepository
import com.example.flashanime.util.CurrentFragmentType



class MainViewModel(private val flashAnimeRepository: FlashAnimeRepository): ViewModel() {

    // Record current fragment to support data binding
    val currentFragmentType = MutableLiveData<CurrentFragmentType>()

}