package com.example.flashanime.data.source.remote

import androidx.lifecycle.LiveData
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.JLPTWord
import com.example.flashanime.data.source.FlashAnimeDataSource
import com.example.flashanime.network.FlashAnimeMoshiApi
import retrofit2.Response

object FlashAnimeRemoteDataSource: FlashAnimeDataSource{

    override suspend fun getWordInfo(word: String): JLPTWord {
        return FlashAnimeMoshiApi.retrofitService.getWordInfo(word)
    }


    override suspend fun insertAnimeInfoInDatabase(animeInfo: AnimeInfo) {
        TODO("Not yet implemented")
    }

    override suspend fun clearAnimeInfoInDatabase() {
        TODO("Not yet implemented")
    }

    override fun getAllAnimeInfo(): LiveData<List<AnimeInfo>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAnimeInfoById(id: String): AnimeInfo {
        TODO("Not yet implemented")
    }
}