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

//    @Update
//    fun update(product: AnimeInfo)

//    @Query("DELETE from products_in_cart_table WHERE product_id = :id AND product_selected_color_code = :colorCode AND product_selected_size = :size")
//    fun delete(id: Long, colorCode: String, size: String)
//
    @Query("DELETE FROM anime_info_table")
    fun clear()

    @Query("SELECT * FROM anime_info_table")
    fun getAllAnimeInfo():
            LiveData<List<AnimeInfo>>

//    @Query("SELECT * from products_in_cart_table WHERE product_id = :id AND product_selected_color_code = :colorCode AND product_selected_size = :size")
//    fun get(id: Long, colorCode: String, size: String): Product?
}