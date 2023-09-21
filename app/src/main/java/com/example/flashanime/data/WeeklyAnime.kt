package com.example.flashanime.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize


@Parcelize
data class WeeklyInfo(
    val monday: WeeklyAnimeList = WeeklyAnimeList(),
    val tuesday: WeeklyAnimeList = WeeklyAnimeList(),
    val wednesday: WeeklyAnimeList = WeeklyAnimeList(),
    val thursday: WeeklyAnimeList = WeeklyAnimeList(),
    val friday: WeeklyAnimeList = WeeklyAnimeList(),
    val saturday: WeeklyAnimeList = WeeklyAnimeList(),
    val sunday: WeeklyAnimeList = WeeklyAnimeList(),
): Parcelable

@Parcelize
data class WeeklyAnimeList (
    val weeklyAnimeList: List<WeeklyAnime> = emptyList()
): Parcelable
@Parcelize
data class WeeklyAnime (
    val releaseTime: String = "",
    val title: String = "",
    val episode: String = ""
): Parcelable
