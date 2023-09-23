package com.example.flashanime.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.flashanime.data.AnimeInfo

@Database(entities = [AnimeInfo::class], version = 1, exportSchema = false)
abstract class FlashAnimeDatabase: RoomDatabase() {

    abstract val flashAnimeDatabaseDao: FlashAnimeDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: FlashAnimeDatabase? = null

        fun getInstance(context: Context): FlashAnimeDatabase {

            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FlashAnimeDatabase::class.java,
                        "flash_anime_database"
                    )

                        .fallbackToDestructiveMigration()
                        .build()
                    // Assign INSTANCE to the newly created database.
                    INSTANCE = instance
                }
                // Return instance; smart cast to be non-null.
                return instance
            }
        }

    }


}