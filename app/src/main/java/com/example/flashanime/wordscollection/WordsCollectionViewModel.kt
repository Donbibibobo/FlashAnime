package com.example.flashanime.wordscollection

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.JLPTWordInfo
import com.example.flashanime.data.PlayWords
import com.example.flashanime.data.source.FlashAnimeRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class WordsCollectionViewModel(private val flashAnimeRepository: FlashAnimeRepository) : ViewModel()  {


    private val _wordsCollectionList = MutableLiveData<List<PlayWords>>()
    val wordsCollectionList: LiveData<List<PlayWords>>
        get() = _wordsCollectionList


    // JLPt API
    private val _wordInfoSelected = MutableLiveData<JLPTWordInfo>()
    val wordInfoSelected: LiveData<JLPTWordInfo>
        get() = _wordInfoSelected

    init {
        getWordsCollectionList()
    }

    private fun getWordsCollectionList() {
        val db = Firebase.firestore
        val wordsCollection = db.collection("userInfo")
            .document("Bstm28YuZ3ih78afvdq9").collection("wordsCollection")

        wordsCollection.get()
            .addOnSuccessListener { value ->
                val playWordsList = mutableListOf<PlayWords>()

                for (document in value){
                    val playWords = document.toObject(PlayWords::class.java)
                    playWordsList.add(playWords.copy(isCollected = true))
                    Log.i("getWordsCollectionList", "playWords: $playWords")
                }

                _wordsCollectionList.value = playWordsList
            }
    }

    fun getWordInfoWordsCollection(word: String) {
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
}