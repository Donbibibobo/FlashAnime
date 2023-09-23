package com.example.flashanime.data.source.local

import androidx.lifecycle.LiveData
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.JLPTWord
import com.example.flashanime.data.source.FlashAnimeDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FlashAnimeLocalDataSource(private val dao: FlashAnimeDatabaseDao): FlashAnimeDataSource {

    override suspend fun getWordInfo(word: String): JLPTWord {
        TODO("Not yet implemented")
    }


    override suspend fun insertAnimeInfoInDatabase(animeInfo: AnimeInfo) {
        withContext(Dispatchers.IO) {
            dao.insert(animeInfo)
        }
    }

    override suspend fun clearAnimeInfoInDatabase() {
        withContext(Dispatchers.IO) {
            dao.clear()
        }
    }

    override fun getAllAnimeInfo(): LiveData<List<AnimeInfo>> {
        return dao.getAllAnimeInfo()
    }
}
