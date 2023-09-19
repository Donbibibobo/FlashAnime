package com.example.flashanime.data.source

import com.example.flashanime.data.JLPTWord
import retrofit2.Response
import retrofit2.http.Query

// Interface to the Stylish layers
interface FlashAnimeRepository {

    suspend fun getAnimeInfo(tid: Long): Response<String>

    suspend fun getWordInfo(word: String): JLPTWord


}