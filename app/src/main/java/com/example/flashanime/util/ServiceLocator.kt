package com.example.flashanime.util

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.example.flashanime.data.source.DefaultFlashAnimeRepository
import com.example.flashanime.data.source.FlashAnimeDataSource
import com.example.flashanime.data.source.FlashAnimeRepository
import com.example.flashanime.data.source.local.FlashAnimeDatabase
import com.example.flashanime.data.source.local.FlashAnimeLocalDataSource
import com.example.flashanime.data.source.remote.FlashAnimeRemoteDataSource

object ServiceLocator {

    @Volatile
    var flashAnimeRepository: FlashAnimeRepository? = null
        @VisibleForTesting set

    fun provideTasksRepository(context: Context): FlashAnimeRepository {
        synchronized(this) {
            return flashAnimeRepository
                ?: createFlashAnimeRepository(context)
        }
    }

    private fun createFlashAnimeRepository(context: Context): FlashAnimeRepository {
        return DefaultFlashAnimeRepository(
            FlashAnimeRemoteDataSource,
            createLocalDataSource(context)
        )
    }

    private fun createLocalDataSource(context: Context): FlashAnimeDataSource {
        return FlashAnimeLocalDataSource(FlashAnimeDatabase.getInstance(context).flashAnimeDatabaseDao)
    }
}