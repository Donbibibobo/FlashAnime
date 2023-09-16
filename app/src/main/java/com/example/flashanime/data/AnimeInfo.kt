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
    val wordsList: List<String>,
    val category: List<String>,
    val videoSourceM3U8: String,
    val pictureURL: String,
): Parcelable

