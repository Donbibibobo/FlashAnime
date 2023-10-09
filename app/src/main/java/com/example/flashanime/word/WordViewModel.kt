package com.example.flashanime.word

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flashanime.UserManager
import com.example.flashanime.data.JLPTWordInfo
import com.example.flashanime.data.WordsCollection
import com.example.flashanime.data.source.FlashAnimeRepository
import com.example.flashanime.util.CurrentFragmentType
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private const val TAG = "WordViewModel"

class WordViewModel(
    private val flashAnimeRepository: FlashAnimeRepository,
    private val wordInfo: WordsCollection
) : ViewModel() {

    val db = Firebase.firestore
    private var listenerRegistration: ListenerRegistration? = null


    // arguments from detail word
    val wordInfoArg = MutableLiveData<WordsCollection>().apply {
        value = wordInfo
    }


    val wordInfoArgForUi = MutableLiveData<WordsCollection>()


    // handle leave dialog
    private val _leave = MutableLiveData<Boolean>()
    val leave: LiveData<Boolean>
        get() = _leave


    // snap shot collected words
    private val _collectedWordsList = MutableLiveData<List<String>>()
    val collectedWordsList: LiveData<List<String>>
        get() = _collectedWordsList



    init {
//        addUserCollectedWordsList()
        snapShotCollectedList()
    }

    // leave dialog
    fun leave() {
        _leave.value = true
    }

    fun onLeaveCompleted() {
        _leave.value = null
    }

    // add word to words list
    fun addUserCollectedWordsList() {
        // fire
        val userCollectedWordsList =
            db.collection("userInfo").document("Bstm28YuZ3ih78afvdq9")
                .collection("wordsCollection").document(wordInfoArg.value!!.word)
        Log.i("loginTest","${UserManager.user?.uid}")


        val collectedWords = mapOf(
            "videoId" to wordInfoArg.value!!.videoId,
            "wordsTime" to wordInfoArg.value!!.wordsTime,
            "videoTitle" to wordInfoArg.value!!.videoTitle,
            "image" to wordInfoArg.value!!.image,
            "episodeNum" to wordInfoArg.value!!.episodeNum,
            "word" to wordInfoArg.value!!.word,
            "isCollected" to true,
            "episodeId" to wordInfoArg.value!!.episodeId
        )

        userCollectedWordsList.set(collectedWords)
            .addOnSuccessListener { documentReference ->
                Log.d("AddFirebase", "DocumentSnapshot written with ID: $documentReference")
            }
            .addOnFailureListener { e ->
                Log.w("AddFirebase", "Error adding document", e)
            }
    }

    // remove word to words list
    fun removeUserCollectedWord() {
        // fire
        val userCollectedWordsList =
            db.collection("userInfo").document("Bstm28YuZ3ih78afvdq9")
                .collection("wordsCollection").document(wordInfoArg.value!!.word)
        Log.i("loginTest","${UserManager.user?.uid}")


        userCollectedWordsList.delete()
            .addOnSuccessListener {
                Log.d("DeleteFirebase", "DocumentSnapshot successfully deleted!")
            }
            .addOnFailureListener { e ->
                Log.w("DeleteFirebase", "Error deleting document", e)
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



}