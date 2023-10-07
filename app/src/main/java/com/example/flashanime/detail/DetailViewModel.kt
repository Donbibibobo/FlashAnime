package com.example.flashanime.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.flashanime.TemporaryFile
import com.example.flashanime.UserManager
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.Episode
import com.example.flashanime.data.JLPTWordInfo
import com.example.flashanime.data.source.FlashAnimeRepository
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class DetailViewModel(
    private val flashAnimeRepository: FlashAnimeRepository,
    private val arguments: AnimeInfo
): ViewModel() {

    // Detail has product data from arguments
    private val _animeInfoArg = MutableLiveData<AnimeInfo>().apply {
        value = arguments
    }
    val animeInfoArg: LiveData<AnimeInfo>
        get() = _animeInfoArg



    // episode recyclerview list default
    private val _episodeMutableListDefault = MutableLiveData<List<Episode>>()
    val episodeMutableListDefault: LiveData<List<Episode>>
        get() = _episodeMutableListDefault


    // episode recyclerview list selected
    private val _episodeMutableListSelected = MutableLiveData<List<Episode>>()
    val episodeMutableListSelected: LiveData<List<Episode>>
        get() = _episodeMutableListSelected


    // episode index for exoPlayer
    private var _episodeExo = 0
    val episodeExo: Int
        get() = _episodeExo

    // episode recyclerview list constrain selected word API
    private val _wordInfoSelected = MutableLiveData<JLPTWordInfo>()
    val wordInfoSelected: LiveData<JLPTWordInfo>
        get() = _wordInfoSelected




    init {
        createEpisodeList()
    }

    private fun createEpisodeList() {
        val mutableEpisodeList = mutableListOf<Episode>()
        mutableEpisodeList.add(Episode.EpisodeSelected("1"))
        List(arguments.videosId.size) {
            mutableEpisodeList.add(Episode.EpisodeUnSelected((it + 2).toString()))
        }
        mutableEpisodeList.removeLast()
        _episodeMutableListDefault.value = mutableEpisodeList
    }

    fun selectedEpisodeList(selectedIndex: Int) {
        _episodeExo = selectedIndex
        val mutableEpisodeList = mutableListOf<Episode>()
        List(arguments.videosId.size) {
            mutableEpisodeList.add(Episode.EpisodeUnSelected((it + 1).toString()))
        }
        mutableEpisodeList[selectedIndex] = Episode.EpisodeSelected((selectedIndex+1).toString())
        _episodeMutableListSelected.value = mutableEpisodeList
        Log.i("test11","viewModel: $_episodeExo")
    }

    fun scrollToWord(position: Int, recyclerView: RecyclerView) {
        val center = recyclerView.height / 2
        val targetView = recyclerView.layoutManager?.findViewByPosition(position)
        targetView?.let {
            val top = it.top
            val toScroll = top - center + it.height / 2
            recyclerView.smoothScrollBy(0, toScroll)
        }
    }

    fun findMatchingWordPosition(currentTime: Long): Int {
        val currentEpisodeIndex = _episodeExo
        val currentEpisode = animeInfoArg.value?.wordsList?.getOrNull(currentEpisodeIndex)

        return currentEpisode?.playWords?.indexOfFirst {
            timeToMillis(it!!.time) >= currentTime
        } ?: -1
    }

    // change time to mills
    fun timeToMillis(timeString: String): Long {
        val splitByColon = timeString.split(":")
        val hours = splitByColon[0].toLong()
        val minutes = splitByColon[1].toLong()
        val splitByDot = splitByColon[2].split(".")
        val seconds = splitByDot[0].toLong()
        val millis = if (splitByDot.size > 1) splitByDot[1].toLong() else 0L

        return (hours * 3600000) + (minutes * 60000) + (seconds * 1000) + (millis * 10)
    }

    fun getWordInfo(word: String) {
        viewModelScope.launch {
            try {
                val wordInfo = flashAnimeRepository.getWordInfo(word)
                Log.i("getWordInfo", wordInfo.words[0].romaji)
                _wordInfoSelected.value = wordInfo.words[0]
            } catch (e: Exception) {
//                throw IllegalArgumentException("DetailViewModel getWordInfo fail!")
                Log.i("DetailViewModel", "getWordInfo failed with exception: $e")
            }
        }
    }


    // record UserWatchHistory
    fun setUserWatchHistoryList(animeId: String) {
        val db = Firebase.firestore

        val userAnimeListDocument =
            db.collection("userInfo").document("Bstm28YuZ3ih78afvdq9").collection("watchHistory").document(animeId)
        Log.i("loginTest","${UserManager.user?.uid}")


        val userData = hashMapOf(
            "animeId" to "animeId",
            "timestamp" to FieldValue.serverTimestamp()
        )

        // [update same user document]
        userAnimeListDocument.set(userData)
            .addOnSuccessListener { documentReference ->
                Log.d("AddFirebase", "DocumentSnapshot written with ID: $documentReference")
            }
            .addOnFailureListener { e ->
                Log.w("AddFirebase", "Error adding document", e)
            }
    }

}