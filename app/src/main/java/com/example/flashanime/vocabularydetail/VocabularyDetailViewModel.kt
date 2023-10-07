package com.example.flashanime.vocabularydetail

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashanime.R
import com.example.flashanime.UserManager
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.JLPTWordInfo
import com.example.flashanime.data.source.FlashAnimeRepository
import com.example.flashanime.util.Util.getString
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


    var hasCollectedWords: Boolean = false

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

    private val collectedList = mutableListOf<String>()

    init {
        snapShotCollectedList()

        // snap wont' remove when nav to wordTest(same as this VM), so give
        // collectedList to _collectedWordsList again
        _collectedWordsList.value = collectedList

    }

    fun resetWordInfoSelected() {
        _wordInfoSelected.value = null
    }



    fun getWordInfoVocabulary(word: String) {
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

    private fun snapShotCollectedList() {


        val userCollectedWordList =
            db.collection("userInfo").document("Bstm28YuZ3ih78afvdq9")
                .collection("wordsCollection")
        Log.i("loginTest","${UserManager.user?.uid}")



        listenerRegistration = userCollectedWordList.addSnapshotListener { value, error ->
            if (error != null) {
                Log.w(TAG, "Listen failed.", error)
                return@addSnapshotListener

            } else if (value != null) {

                hasCollectedWords = false

                collectedList.clear()

                for (document in value){
                    val collected = document.id
                    collectedList.add(collected)
                }

                Log.i("collectedList", "1collectedList: $collectedList")
                Log.i("collectedList", "2hasCollectedWords: $hasCollectedWords")

                _collectedWordsList.value = collectedList

                Log.i("collectedList", "3_collectedWordsList.value: ${_collectedWordsList.value}")


            }
        }

    }

//    fun removeListenerRegistration(){
//        listenerRegistration?.remove()
//        Log.i(TAG, "Listener removed!")
//    }

    override fun onCleared() {
        super.onCleared()
        listenerRegistration?.remove()
        Log.i(TAG, "Listener removed!")
    }


    fun createCollectedWordList() {
        Log.i("collectedList", "createCollectedWordList hasCollectedWords: $hasCollectedWords")
        if (!hasCollectedWords){
            // get a copy form _animeInfoArg
            val currentAnimeInfo = _animeInfoArg.value?.copy()

            Log.i("collectedList", "x:_collectedWordsList ${_collectedWordsList.value}")

            // update every wordsList's PlayWordEpisode's playWords
            val updatedWordsList = currentAnimeInfo?.wordsList?.map { playWordEpisode ->
                val updatedPlayWords = playWordEpisode.playWords.map { playWord ->
                    playWord.copy(isCollected = _collectedWordsList.value?.contains(playWord.word) == true)
                }
                playWordEpisode.copy(playWords = updatedPlayWords)
            }

            // give wordsList to AnimeInfo
            currentAnimeInfo?.wordsList = updatedWordsList ?: emptyList()

            Log.i("collectedList", "currentAnimeInfo :$currentAnimeInfo")

            hasCollectedWords = true


            // update _animeInfoArg to upDate UI
            _animeInfoArg.value = currentAnimeInfo

            Log.i("OMGG ","B: $hasCollectedWords")
        }

    }

    fun showNoCollectedWordsAlert(context: Context){
        AlertDialog.Builder(context)
            .setTitle(getString(R.string.collectedWordsAlert_title))
            .setMessage(getString(R.string.collectedWordsAlert_message))
            .setPositiveButton(getString(R.string.collectedWordsAlert_positiveButton), null).show()
    }

}

