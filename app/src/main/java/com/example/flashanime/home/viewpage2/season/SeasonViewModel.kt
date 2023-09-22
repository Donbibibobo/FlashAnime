package com.example.flashanime.home.viewpage2.season

import android.util.Log
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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import java.util.regex.Pattern


private const val TAG: String = "HomeViewModel"

class SeasonViewModel(private val flashAnimeRepository: FlashAnimeRepository): ViewModel() {

    val db = Firebase.firestore

    private val _animeInfo = MutableLiveData<List<AnimeInfo>>()
    val animeInfo: LiveData<List<AnimeInfo>>
        get() = _animeInfo

    init {
//        animeSnapshot()

        getAnimeList()
        getFavoriteList()

//        TemporaryFile.addFirebaseAnimeInfo()
//        TemporaryFile.addWeekList()
//        TemporaryFile.addUser()
//        TemporaryFile.setUserCollectedAnimeList()
    }

    // if user not login then use animeSnapshot()
    private fun animeSnapshot() {
        val articlesCollection = db.collection("animeInfo")

        articlesCollection
            .get()
            .addOnSuccessListener { querySnapshot ->
                Log.i(TAG, "SeasonViewModel animeSnapshot success")

                val animeInfoList = mutableListOf<AnimeInfo>()

                for (document in querySnapshot.documents) {

                    val animeInfo = document.toObject(AnimeInfo::class.java)
                    if (animeInfo != null) {
                        animeInfoList.add(animeInfo)
                    }

                }

                _animeInfo.value = animeInfoList
                TemporaryFile.TempoAnimeInfo = animeInfoList // Temporary

            }
            .addOnFailureListener { exception ->
                Log.i(TAG, "SeasonViewModel animeSnapshot fail: $exception")
            }

    }


    // combine
    private var isAnimeListGet: Boolean = false
    private var isFavoriteListGet: Boolean = false
    private fun callOnCombinedList(){
        if (isAnimeListGet && isFavoriteListGet) {
            onCombinedList()
        }
    }

    private var animeListToCombine = mutableListOf<AnimeInfo>()
    private var favoriteListToCombine = mutableListOf<String>()

    private val _combinedList = MutableLiveData<List<AnimeInfo>>()
    val combinedList: LiveData<List<AnimeInfo>>
        get() = _combinedList



    private fun onCombinedList() {
        val combined = animeListToCombine.map { animeInfo ->
            animeInfo.copy(isCollected = favoriteListToCombine.contains(animeInfo.animeId))
        }
        _combinedList.value = combined
        TemporaryFile.TempoAnimeInfo = combined // Temporary
    }


    private fun getAnimeList() {
        val animeInfoCollection = db.collection("animeInfo")
        val animeList = mutableListOf<AnimeInfo>()
        animeInfoCollection.get()
            .addOnSuccessListener {value ->
                for (document in value) {
                    val anime = document.toObject(AnimeInfo::class.java)
                    animeList.add(anime)
                }
                Log.i("animeListToCombine", "animeList: $animeList")
                animeListToCombine = animeList

                isAnimeListGet = true
                callOnCombinedList()
            }
    }

    private fun getFavoriteList() {
        val userInfoCollection =
            db.collection("userInfo").document("Bstm28YuZ3ih78afvdq9").collection("animeCollection")
        val favoriteList = mutableListOf<String>()
        userInfoCollection.get()
            .addOnSuccessListener { value ->
                for (document in value){
                    val favorite = document.id
                    favoriteList.add(favorite)
                }
                Log.i("animeListToCombine", "favoriteList: $favoriteList")
                favoriteListToCombine = favoriteList

                isFavoriteListGet = true
                callOnCombinedList()
            }
    }
}