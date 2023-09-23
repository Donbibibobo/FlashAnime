package com.example.flashanime.data.source.local

import androidx.room.TypeConverter
import com.example.flashanime.data.PlayWordEpisode
import com.example.flashanime.data.PlayWords
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class FlashAnimeConverters {


    /**
     * Convert [List] [PlayWordEpisode] to Json
     */
    @TypeConverter
    fun convertPlayWordEpisodeToJson(list: List<PlayWordEpisode>?): String? {
        list?.let {
            return Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter<List<PlayWordEpisode>>(List::class.java).toJson(list)
        }
        return null
    }
    /**
     * Convert Json to [List] [PlayWordEpisode]
     */
    @TypeConverter
    fun convertJsonToPlayWordEpisode(json: String?): List<PlayWordEpisode>? {
        json?.let {
            val type = Types.newParameterizedType(List::class.java, PlayWordEpisode::class.java)
            val adapter: JsonAdapter<List<PlayWordEpisode>> = Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter(type)
            return adapter.fromJson(it)
        }
        return null
    }



    /**
     * Convert [List] [String] to Json
     */
    @TypeConverter
    fun convertListToJson(list: List<String>?): String? {
        list?.let {
            return Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter<List<String>>(List::class.java).toJson(list)
        }
        return null
    }
    /**
     * Convert Json to [List] [String]
     */
    @TypeConverter
    fun convertJsonToList(json: String?): List<String>? {
        json?.let {
            val type = Types.newParameterizedType(List::class.java, String::class.java)
            val adapter: JsonAdapter<List<String>> = Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter(type)
            return adapter.fromJson(it)
        }
        return null
    }



    /**
     * Convert [List] [PlayWords] to Json
     */
    @TypeConverter
    fun convertPlayWordsToJson(list: List<PlayWords>?): String? {
        list?.let {
            return Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter<List<PlayWords>>(List::class.java).toJson(list)
        }
        return null
    }
    /**
     * Convert Json to [List] [PlayWords]
     */
    @TypeConverter
    fun convertJsonToPlayWords(json: String?): List<PlayWords>? {
        json?.let {
            val type = Types.newParameterizedType(List::class.java, PlayWords::class.java)
            val adapter: JsonAdapter<List<PlayWords>> = Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter(type)
            return adapter.fromJson(it)
        }
        return null
    }



}