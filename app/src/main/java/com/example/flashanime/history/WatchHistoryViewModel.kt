package com.example.flashanime.history

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flashanime.UserManager
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.source.FlashAnimeRepository
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class WatchHistoryViewModel(private val flashAnimeRepository: FlashAnimeRepository) : ViewModel()  {


    private val _animeInfoList = MutableLiveData<List<AnimeInfo>>()
    val animeInfoList: LiveData<List<AnimeInfo>>
        get() = _animeInfoList


    init {
        getWatchHistoryList()
        Log.i("watchhh", "WATCH_HISTORY")
    }
    private fun getWatchHistoryList() {
        val db = Firebase.firestore
        val animeInfoCollection = db.collection("userInfo")
            .document("Bstm28YuZ3ih78afvdq9").collection("watchHistory")
            .orderBy("timestamp", Query.Direction.DESCENDING)
        Log.i("loginTest","${UserManager.user?.uid}")


        // get watchHistory
        animeInfoCollection.get()
            .addOnSuccessListener { value ->
                val tasks = mutableListOf<Task<QuerySnapshot>>()
                val animeList = mutableListOf<AnimeInfo>()

                for (document in value) {
                    val task = db.collection("animeInfo")
                        .whereEqualTo("animeId", document.id)
                        .get()

                    tasks.add(task)
                }

                Tasks.whenAllSuccess<QuerySnapshot>(tasks).addOnSuccessListener { results ->
                    for (documents in results) {
                        for (document in documents) {
                            val animeInfo = document.toObject(AnimeInfo::class.java)
                            animeInfo?.let { animeList.add(it) }
                        }
                    }
                    _animeInfoList.value = animeList
                }
            }
    }
}