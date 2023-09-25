package com.example.flashanime.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.flashanime.data.AnimeInfo

@Dao
interface FlashAnimeDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: AnimeInfo)

    @Query("DELETE FROM anime_info_table")
    fun clear()

    @Query("SELECT * FROM anime_info_table")
    fun getAllAnimeInfo():
            LiveData<List<AnimeInfo>>

    @Query("SELECT * FROM anime_info_table WHERE animeId = :id")
    fun getAnimeInfoById(id: String): AnimeInfo

//    @Query("DELETE FROM anime_info_table WHERE animeId = :id")
//    fun deleteSpecificRowById(id: String)


}