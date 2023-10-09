package com.example.flashanime.wordscollection

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashanime.UserManager
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.JLPTWordInfo
import com.example.flashanime.data.PlayWords
import com.example.flashanime.data.WordsCollection
import com.example.flashanime.data.source.FlashAnimeRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class WordsCollectionViewModel(private val flashAnimeRepository: FlashAnimeRepository) : ViewModel()  {


    private val _wordsCollectionList = MutableLiveData<List<WordsCollection>>()
    val wordsCollectionList: LiveData<List<WordsCollection>>
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
        Log.i("loginTest","${UserManager.user?.uid}")

        wordsCollection.get()
            .addOnSuccessListener { value ->
                val playWordsList = mutableListOf<WordsCollection>()

                for (document in value){
                    val playWords = document.toObject(WordsCollection::class.java)
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
                Log.i("DetailViewModel", "getWordInfo failed with exception: $e")
//                throw IllegalArgumentException("DetailViewModel getWordInfo fail!")
            }
        }
    }
}