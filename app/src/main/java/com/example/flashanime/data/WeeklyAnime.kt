package com.example.flashanime.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeeklyAnime (
    val releaseTime: String,
    val title: String,
    val episode: String
): Parcelable
