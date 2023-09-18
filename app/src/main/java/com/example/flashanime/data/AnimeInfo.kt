package com.example.flashanime.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AnimeInfo (
    val isCollected: Boolean? = null,
    val title: String,
    val releaseTime: String,
    val episode: String,
    val rate: String,
    val wordsList: List<PlayWordEpisode>,
    val category: List<String>,
    val videoSourceM3U8: List<String>,
    val pictureURL: String,
): Parcelable

@Parcelize
data class PlayWordEpisode (
    val episodeNum: String,
    val playWords: List<PlayWords>,
): Parcelable

@Parcelize
data class PlayWords (
    val time: String,
    val word: String,
    val level: String,
): Parcelable



