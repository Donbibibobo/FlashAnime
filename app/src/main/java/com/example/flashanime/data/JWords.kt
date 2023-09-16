package com.example.flashanime.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize


@Parcelize
data class JLPTWord (
    val total: Int,
    val offset: Int,
    val limit: Int,
    val words: List<JLPTWordInfo>
): Parcelable

@Parcelize
data class JLPTWordInfo (
    val isCollected: Boolean? = null,
    val word: String,
    val meaning: String,
    val furigana: String,
    val romaji: String,
    val level: Int
): Parcelable

