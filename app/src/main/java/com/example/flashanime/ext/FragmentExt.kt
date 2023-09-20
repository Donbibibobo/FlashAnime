package com.example.flashanime.ext

import androidx.fragment.app.Fragment
import com.example.flashanime.FlashAnimeApplication
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.JLPTWordInfo
import com.example.flashanime.factory.AnimeInfoViewModelFactory
import com.example.flashanime.factory.ViewModelFactory
import com.example.flashanime.factory.WordInfoViewModelFactory

fun Fragment.getVmFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as FlashAnimeApplication).flashAnimeRepository
    return ViewModelFactory(repository)
}

fun Fragment.getVmFactory(animeInfo: AnimeInfo): AnimeInfoViewModelFactory {
    val repository = (requireContext().applicationContext as FlashAnimeApplication).flashAnimeRepository
    return AnimeInfoViewModelFactory(repository)
}

fun Fragment.getVmFactory(wordInfo: JLPTWordInfo): WordInfoViewModelFactory {
    val repository = (requireContext().applicationContext as FlashAnimeApplication).flashAnimeRepository
    return WordInfoViewModelFactory(repository, wordInfo)
}