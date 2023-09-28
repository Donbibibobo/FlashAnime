package com.example.flashanime.word

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flashanime.data.JLPTWordInfo
import com.example.flashanime.data.source.FlashAnimeRepository
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private const val TAG = "WordViewModel"

class WordViewModel(
    private val flashAnimeRepository: FlashAnimeRepository,
    private val wordInfo: JLPTWordInfo
) : ViewModel() {

    val db = Firebase.firestore
    private var listenerRegistration: ListenerRegistration? = null


    // arguments from detail word
    val wordInfoArg = MutableLiveData<JLPTWordInfo>().apply {
        value = wordInfo
    }


    val wordInfoArgForUi = MutableLiveData<JLPTWordInfo>()


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

        val collectedWords = mapOf(
            "word" to wordInfoArg.value!!.word,
            "level" to wordInfoArg.value!!.level,
            "isCollected" to true
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