package com.example.flashanime.home.viewpage2.season

import android.content.Context
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashanime.TemporaryFile
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.PlayWordEpisode
import com.example.flashanime.data.PlayWords
import com.example.flashanime.data.source.FlashAnimeRepository
import com.example.flashanime.data.source.local.FlashAnimeDatabase
import com.example.flashanime.data.source.local.FlashAnimeDatabaseDao
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.regex.Pattern


private const val TAG: String = "HomeViewModel"

class SeasonViewModel(private val flashAnimeRepository: FlashAnimeRepository): ViewModel() {

//
//    private val _animeInfo = MutableLiveData<List<AnimeInfo>>()
//    val animeInfo: LiveData<List<AnimeInfo>>
//        get() = _animeInfo
//


    init {

//        TemporaryFile.addOtherAnimeInfo()

//        TemporaryFile.setAnimeInfo5_1()
//        TemporaryFile.addFirebaseAnimeInfo()
        
//        TemporaryFile.addUserCollectedWordsList()
//        TemporaryFile.addWeekList()
//        TemporaryFile.addFirebaseAnimeInfo()
//        TemporaryFile.addWeekList()
//        TemporaryFile.addUser()
//        TemporaryFile.setUserCollectedAnimeList()
    }



    private val _combinedList: LiveData<List<AnimeInfo>> = flashAnimeRepository.getAllAnimeInfo()
    val combinedList: LiveData<List<AnimeInfo>>
        get() = _combinedList



//    fun removeId(context: Context) {
//        viewModelScope.launch{
//            withContext(Dispatchers.IO){
//                FlashAnimeDatabase.getInstance(context).flashAnimeDatabaseDao.deleteSpecificRowById("dDZcI1G1RPcwZIZSpTEe")
//
//            }
//        }
//    }



}