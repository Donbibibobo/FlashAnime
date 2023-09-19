package com.example.flashanime.data.source.remote

import com.example.flashanime.data.JLPTWord
import com.example.flashanime.data.source.FlashAnimeDataSource
import com.example.flashanime.network.FlashAnimeMoshiApi
import com.example.flashanime.network.FlashAnimeScalarsApi
import retrofit2.Response

object FlashAnimeRemoteDataSource: FlashAnimeDataSource{

    override suspend fun getAnimeInfo(tid: Long): Response<String> {
        return FlashAnimeScalarsApi.retrofitService.getAnimeInfo(tid)
    }

    override suspend fun getWordInfo(word: String): JLPTWord {
        return FlashAnimeMoshiApi.retrofitService.getWordInfo(word)
    }
}