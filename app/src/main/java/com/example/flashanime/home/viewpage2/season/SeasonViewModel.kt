package com.example.flashanime.home.viewpage2.season

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashanime.TemporaryFile
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.PlayWordEpisode
import com.example.flashanime.data.PlayWords
import com.example.flashanime.data.source.FlashAnimeRepository
import com.google.firebase.firestore.ktx.firestore
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
        animeSnapshot()
//        TemporaryFile.addFirebaseAnimeInfo()
    }

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
                TemporaryFile.TempoAnimeInfo = animeInfoList

            }
            .addOnFailureListener { exception ->
                Log.i(TAG, "SeasonViewModel animeSnapshot fail: $exception")
            }

    }

}