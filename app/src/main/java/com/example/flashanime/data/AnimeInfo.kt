package com.example.flashanime.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AnimeInfo(
    val isCollected: Boolean = false,
    val animeId: String = "",
    val title: String = "",
    val releaseTime: String = "",
    val episode: String = "",
    val rate: String = "",
    val wordsList: List<PlayWordEpisode> = emptyList(),
    val category: List<String> = emptyList(),
    val videosId: List<String> = emptyList(),
    val pictureURL: String = ""
) : Parcelable

@Parcelize
data class PlayWordEpisode(
    val episodeNum: String = "",
    val playWords: List<PlayWords> = emptyList()
) : Parcelable

@Parcelize
data class PlayWords(
    val time: String = "",
    val word: String = "",
    val level: String = ""
) : Parcelable



