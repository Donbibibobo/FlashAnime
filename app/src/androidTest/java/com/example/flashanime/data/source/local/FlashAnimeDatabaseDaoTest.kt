package com.example.flashanime.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.flashanime.data.AnimeInfo
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class FlashAnimeDatabaseDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: FlashAnimeDatabase
    private lateinit var dao: FlashAnimeDatabaseDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            FlashAnimeDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.flashAnimeDatabaseDao
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertAnimeInfo2DataBase() = runBlockingTest {
        val animeInfo = AnimeInfo(true,"0001","name")

        dao.insert(animeInfo)

        val allAnimeInfo = dao.getAllAnimeInfo().getOrAwaitValue()

        assertThat(allAnimeInfo).contains(animeInfo)
    }

    @Test
    fun clearAllAnimeInfoInDataBase() = runBlockingTest {
        val animeInfo = AnimeInfo(true,"0001","name")

        dao.insert(animeInfo)
        dao.clear()

        val allAnimeInfo = dao.getAllAnimeInfo().getOrAwaitValue()

        assertThat(allAnimeInfo).doesNotContain(allAnimeInfo)
    }


}