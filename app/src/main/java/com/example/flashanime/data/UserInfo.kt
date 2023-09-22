package com.example.flashanime.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfo (
    val userId: String = "",
    val collectedAnime: CollectedAnimeList = CollectedAnimeList(),
): Parcelable

@Parcelize
data class CollectedAnimeList (
    val collectedAnimeList: String = ""
): Parcelable