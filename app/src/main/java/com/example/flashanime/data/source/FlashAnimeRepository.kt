package com.example.flashanime.data.source

import com.example.flashanime.data.JLPTWord
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import retrofit2.Response
import retrofit2.http.Query

// Interface to the Stylish layers
interface FlashAnimeRepository {
    suspend fun getWordInfo(word: String): JLPTWord


}