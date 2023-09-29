package com.example.flashanime.vocabularydetail

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.JLPTWordInfo
import com.example.flashanime.data.source.FlashAnimeRepository
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

private const val TAG = "VocabularyDetailViewModel"
class VocabularyDetailViewModel(
    private val flashAnimeRepository: FlashAnimeRepository,
    private val arguments: AnimeInfo
): ViewModel() {


    val db = Firebase.firestore

    // current episode position
    var autocompletePosition = 0

    private var listenerRegistration: ListenerRegistration? = null


    private var hasCollectedWords: Boolean = false

    // Detail has product data from arguments
    private val _animeInfoArg = MutableLiveData<AnimeInfo>().apply {
        if (hasCollectedWords.not()){
            Log.i("OMGG ","value = arguments")
            Log.i("OMGG ","A: $hasCollectedWords")
            value = arguments
        }
    }
    val animeInfoArg: LiveData<AnimeInfo>
        get() = _animeInfoArg




    // episode recyclerview list constrain selected word API
    private val _wordInfoSelected = MutableLiveData<JLPTWordInfo>()
    val wordInfoSelected: LiveData<JLPTWordInfo>
        get() = _wordInfoSelected


    // snap shot collected words
    private val _collectedWordsList = MutableLiveData<List<String>>()
    val collectedWordsList: LiveData<List<String>>
        get() = _collectedWordsList


    init {
        snapShotCollectedList()
    }

    fun resetWordInfoSelected() {
        _wordInfoSelected.value = null
    }

    fun getWordInfo(word: String) {
        viewModelScope.launch {
            try {
                val wordInfo = flashAnimeRepository.getWordInfo(word)
                Log.i("getWordInfo", wordInfo.words[0].romaji)
                _wordInfoSelected.value = wordInfo.words[0]
            } catch (e: Exception) {
                throw IllegalArgumentException("DetailViewModel getWordInfo fail!")
            }
        }
    }

    private fun snapShotCollectedList() {


        val userCollectedWordList =
            db.collection("userInfo").document("Bstm28YuZ3ih78afvdq9")
                .collection("wordsCollection")


        listenerRegistration = userCollectedWordList.addSnapshotListener { value, error ->
            if (error != null) {
                Log.w(TAG, "Listen failed.", error)
                return@addSnapshotListener

            } else if (value != null) {

                hasCollectedWords = false

                val collectedList = mutableListOf<String>()

                for (document in value){
                    val collected = document.id
                    collectedList.add(collected)
                }

                Log.i("collectedList", "collectedList: $collectedList")


                _collectedWordsList.value = collectedList

                collectedList.clear()
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        listenerRegistration?.remove()
    }

    fun createCollectedWordList() {
        if (!hasCollectedWords){
            // get a copy form _animeInfoArg
            val currentAnimeInfo = _animeInfoArg.value?.copy()

            // update every wordsList's PlayWordEpisode's playWords
            val updatedWordsList = currentAnimeInfo?.wordsList?.map { playWordEpisode ->
                val updatedPlayWords = playWordEpisode.playWords.map { playWord ->
                    playWord.copy(isCollected = _collectedWordsList.value?.contains(playWord.word) == true)
                }
                playWordEpisode.copy(playWords = updatedPlayWords)
            }

            // give wordsList to AnimeInfo
            currentAnimeInfo?.wordsList = updatedWordsList ?: emptyList()

            // update _animeInfoArg to upDate UI
            _animeInfoArg.value = currentAnimeInfo

            hasCollectedWords = true
            Log.i("OMGG ","B: $hasCollectedWords")
        }

    }

    fun showNoCollectedWordsAlert(context: Context){
        AlertDialog.Builder(context)
            .setTitle("No collected Words!")
            .setMessage("There is no collected words in this episode!" +
                    "Go add some words!")
            .setPositiveButton("OK", null).show()
    }

}