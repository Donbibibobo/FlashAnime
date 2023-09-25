package com.example.flashanime.home.viewpage2.week

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.WeeklyInfo
import com.example.flashanime.data.source.FlashAnimeRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG: String = "WeekViewModel"

class WeekViewModel(private val flashAnimeRepository: FlashAnimeRepository): ViewModel() {


    val db = Firebase.firestore

    private val _weekInfo = MutableLiveData<List<WeeklyInfo>>()
    val weekInfo: LiveData<List<WeeklyInfo>>
        get() = _weekInfo

    private val _selectedAnimeInfo = MutableLiveData<AnimeInfo?>()
    val selectedAnimeInfo: LiveData<AnimeInfo?>
        get() = _selectedAnimeInfo

    init {
        weekSnapshot()
    }

    private fun weekSnapshot() {
        val articlesCollection = db.collection("weekInfo")

        articlesCollection
            .get()
            .addOnSuccessListener { querySnapshot ->
                Log.i(TAG, "WeekViewModel weekSnapshot success")

                val weekInfoList = mutableListOf<WeeklyInfo>()

                for (document in querySnapshot.documents) {

                    val weekInfo = document.toObject(WeeklyInfo::class.java)

                    Log.i(TAG, "kk: $weekInfo")

                    weekInfo?.let {
                        weekInfoList.add(it)
                    }

                }

                _weekInfo.value = weekInfoList
            }
            .addOnFailureListener { exception ->
                Log.i(TAG, "SeasonViewModel animeSnapshot fail: $exception")
            }

    }

    fun navigateByAnimeId(animeId: String) {
        viewModelScope.launch {
            val selectedAnimeInfoById = withContext(Dispatchers.IO) {
                flashAnimeRepository.getAnimeInfoById(animeId)
            }
            _selectedAnimeInfo.value = selectedAnimeInfoById
        }
    }

    fun navigateComplete() {
        _selectedAnimeInfo.value = null
    }
}