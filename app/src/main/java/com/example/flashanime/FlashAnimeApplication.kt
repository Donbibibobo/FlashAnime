package com.example.flashanime

import android.app.Application
import com.example.flashanime.data.source.FlashAnimeRepository
import com.example.flashanime.util.ServiceLocator
import kotlin.properties.Delegates

// An application that lazily provides a repository
class FlashAnimeApplication : Application() {

    // Depends on the flavor
    val flashAnimeRepository: FlashAnimeRepository
        get() = ServiceLocator.provideTasksRepository(this)

    companion object {
        var instance: FlashAnimeApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}