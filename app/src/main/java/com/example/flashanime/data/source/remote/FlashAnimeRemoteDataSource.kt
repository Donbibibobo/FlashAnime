package com.example.flashanime.data.source.remote

import com.example.flashanime.data.source.FlashAnimeDataSource
import com.example.flashanime.network.FlashAnimeApi
import retrofit2.Response

object FlashAnimeRemoteDataSource: FlashAnimeDataSource{

    override suspend fun getAnimeInfo(tid: Long): Response<String> {
        return FlashAnimeApi.retrofitService.getAnimeInfo(tid)
    }
}