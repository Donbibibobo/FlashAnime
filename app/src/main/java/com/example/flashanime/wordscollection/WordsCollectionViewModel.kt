package com.example.flashanime.wordscollection

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.PlayWords
import com.example.flashanime.data.source.FlashAnimeRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class WordsCollectionViewModel(private val flashAnimeRepository: FlashAnimeRepository) : ViewModel()  {


    private val _wordsCollectionList = MutableLiveData<List<PlayWords>>()
    val wordsCollectionList: LiveData<List<PlayWords>>
        get() = _wordsCollectionList


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
}