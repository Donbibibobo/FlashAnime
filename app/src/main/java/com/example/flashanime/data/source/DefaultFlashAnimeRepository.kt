package com.example.flashanime.data.source

import com.example.flashanime.data.JLPTWord
import retrofit2.Response

// Concrete implementation to load FlashAnime sources
class DefaultFlashAnimeRepository(
    private val flashAnimeRemoteDataSource: FlashAnimeDataSource,
//    private val flashAnimeLocalDataSource: FlashAnimeDataSource,
) : FlashAnimeRepository {

    override suspend fun getAnimeInfo(tid: Long): Response<String> {
        return flashAnimeRemoteDataSource.getAnimeInfo(tid)
    }

    override suspend fun getWordInfo(word: String): JLPTWord {
        return flashAnimeRemoteDataSource.getWordInfo(word)
    }
}