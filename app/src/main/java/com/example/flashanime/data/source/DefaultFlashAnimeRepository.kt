package com.example.flashanime.data.source

import androidx.lifecycle.LiveData
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.JLPTWord
import retrofit2.Response

// Concrete implementation to load FlashAnime sources
class DefaultFlashAnimeRepository(
    private val flashAnimeRemoteDataSource: FlashAnimeDataSource,
    private val flashAnimeLocalDataSource: FlashAnimeDataSource,
) : FlashAnimeRepository {

    override suspend fun getWordInfo(word: String): JLPTWord {
        return flashAnimeRemoteDataSource.getWordInfo(word)
    }


    override suspend fun insertAnimeInfoInDatabase(animeInfo: AnimeInfo) {
        flashAnimeLocalDataSource.insertAnimeInfoInDatabase(animeInfo)
    }
    override suspend fun clearAnimeInfoInDatabase() {
        flashAnimeLocalDataSource.clearAnimeInfoInDatabase()
    }

    override fun getAllAnimeInfo(): LiveData<List<AnimeInfo>> {
        return flashAnimeLocalDataSource.getAllAnimeInfo()
    }
}