package com.example.flashanime

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.source.FlashAnimeRepository
import com.example.flashanime.data.source.local.FlashAnimeDatabase
import com.example.flashanime.util.CurrentFragmentType
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.launch

private const val TAG: String = "MainViewModel"

class MainViewModel(private val flashAnimeRepository: FlashAnimeRepository): ViewModel() {

    // Record current fragment to support data binding
    val currentFragmentType = MutableLiveData<CurrentFragmentType>()


    val db = Firebase.firestore
    init {
        getAnimeList()
        snapShotFavoriteList()


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

    private fun snapShotFavoriteList() {
        val userInfoCollection =
            db.collection("userInfo").document("Bstm28YuZ3ih78afvdq9").collection("animeCollection")

        val favoriteList = mutableListOf<String>()

        userInfoCollection.addSnapshotListener { value, error ->
            if (error != null) {
                Log.w(TAG, "Listen failed.", error)
                return@addSnapshotListener

            } else if (value != null) {

                favoriteList.clear()

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


    // combine
    private var isAnimeListGet: Boolean = false
    private var isFavoriteListGet: Boolean = false
    private fun callOnCombinedList(){
        if (isAnimeListGet && isFavoriteListGet) {
            onCombinedList()
            isFavoriteListGet = false
        }
    }

    private var animeListToCombine = mutableListOf<AnimeInfo>()
    private var favoriteListToCombine = mutableListOf<String>()

    private fun onCombinedList() {
        val combined = animeListToCombine.map { animeInfo ->
            animeInfo.copy(isCollected = favoriteListToCombine.contains(animeInfo.animeId))
        }
        TemporaryFile.TempoAnimeInfo = combined // Temporary

        viewModelScope.launch {

            combined.forEach {
                flashAnimeRepository.insertAnimeInfoInDatabase(it)
            }
            Log.i("animeListToCombine","after insert")

        }
    }

}