package com.example.flashanime.data.source

import retrofit2.Response
import retrofit2.http.Query


interface FlashAnimeDataSource {

    suspend fun getAnimeInfo(tid: Long): Response<String>

}