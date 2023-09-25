package com.example.flashanime.data.source

import androidx.lifecycle.LiveData
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.JLPTWord
import retrofit2.Response
import retrofit2.http.Query


interface FlashAnimeDataSource {

    suspend fun getWordInfo(word: String): JLPTWord







    suspend fun insertAnimeInfoInDatabase(animeInfo: AnimeInfo)

    suspend fun clearAnimeInfoInDatabase()

    fun getAllAnimeInfo(): LiveData<List<AnimeInfo>>

    suspend fun getAnimeInfoById(id: String): AnimeInfo


}