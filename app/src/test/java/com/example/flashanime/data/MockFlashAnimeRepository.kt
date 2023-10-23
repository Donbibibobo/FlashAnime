package com.example.flashanime.data

import android.provider.UserDictionary.Words
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.flashanime.data.source.FlashAnimeRepository
import org.mockito.Mockito.mock
import java.io.IOException

class MockFlashAnimeRepository: FlashAnimeRepository {

    private val jLPTWordInfo = JLPTWordInfo(false,"言う","say","iu","iu",1)

    private val words = listOf<JLPTWordInfo>(jLPTWordInfo)

    private val jLPTWord = JLPTWord(0,0,0, words)

    private val animeInfo = AnimeInfo()

    private val listOfAnimeInfo = listOf(animeInfo)

    private val liveDataListOfAnimeInfo = MutableLiveData(listOfAnimeInfo)

    var shouldThrowNoConnectionException = false

    override suspend fun getWordInfo(word: String): JLPTWord {
        if (shouldThrowNoConnectionException) {
            throw IOException("No connection")
        }
        return jLPTWord
    }

    override suspend fun insertAnimeInfoInDatabase(animeInfo: AnimeInfo) {}

    override suspend fun clearAnimeInfoInDatabase() {}

    override fun getAllAnimeInfo(): LiveData<List<AnimeInfo>> {
        return liveDataListOfAnimeInfo
    }

    override suspend fun getAnimeInfoById(id: String): AnimeInfo {
        return animeInfo
    }
}