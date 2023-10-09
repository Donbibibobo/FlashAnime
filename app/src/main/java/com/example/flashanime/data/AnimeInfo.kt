package com.example.flashanime.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.flashanime.data.source.local.FlashAnimeConverters
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.parcelize.Parcelize

@Entity(tableName = "anime_info_table")
@TypeConverters(FlashAnimeConverters::class)
@Parcelize
data class AnimeInfo(
    @ColumnInfo(name = "is_collected")
    val isCollected: Boolean = false,
    @PrimaryKey
    val animeId: String = "",
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "release_time")
    val releaseTime: String = "",
    @ColumnInfo(name = "episode")
    val episode: String = "",
    @ColumnInfo(name = "rate")
    val rate: String = "",
    @ColumnInfo(name = "word_list")
    var wordsList: List<PlayWordEpisode> = emptyList(),
    @ColumnInfo(name = "category")
    val category: List<String> = emptyList(),
    @ColumnInfo(name = "videos_id")
    val videosId: List<String> = emptyList(),
    @ColumnInfo(name = "picture_url")
    val pictureURL: String = ""
) : Parcelable

@Parcelize
data class PlayWordEpisode(
    val episodeNum: String = "",
    var playWords: List<PlayWords> = emptyList()
) : Parcelable

@Parcelize
data class PlayWords(
    val time: String = "",
    val word: String = "",
    val level: String = "",
    val isCollected: Boolean = false
) : Parcelable


// this is for words collection
@Parcelize
data class WordsCollection(
    val videoId: String = "",
    val wordsTime: String = "",
    val videoTitle: String = "",
    val image: String = "",
    val episodeNum: String = "",
    val word: String = "",
    val isCollected: Boolean = false,
    val forReview: Boolean = false,

    // for word dialog
    val meaning: String = "",
    val furigana: String = "",
    val romaji: String = "",

    // episode id
    val episodeId: String = ""
) : Parcelable




@Parcelize
data class PlayWordEpisodePlusAnimeInfo(
    val playWordEpisode: PlayWordEpisode,
    var animeInfo: AnimeInfo
) : Parcelable
