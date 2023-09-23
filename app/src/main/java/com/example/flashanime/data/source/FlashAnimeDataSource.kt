package com.example.flashanime.data.source

import com.example.flashanime.data.JLPTWord
import retrofit2.Response
import retrofit2.http.Query


interface FlashAnimeDataSource {

    suspend fun getWordInfo(word: String): JLPTWord


}