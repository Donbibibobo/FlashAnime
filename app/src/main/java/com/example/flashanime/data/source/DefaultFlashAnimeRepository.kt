package com.example.flashanime.data.source

import retrofit2.Response

// Concrete implementation to load FlashAnime sources
class DefaultFlashAnimeRepository(
    private val flashAnimeRemoteDataSource: FlashAnimeDataSource,
//    private val flashAnimeLocalDataSource: FlashAnimeDataSource,
) : FlashAnimeRepository {

    override suspend fun getAnimeInfo(tid: Long): Response<String> {
        return flashAnimeRemoteDataSource.getAnimeInfo(tid)
    }
}