package com.example.flashanime.ext

import androidx.fragment.app.Fragment
import com.example.flashanime.FlashAnimeApplication
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.factory.AnimeInfoViewModelFactory
import com.example.flashanime.factory.ViewModelFactory

fun Fragment.getVmFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as FlashAnimeApplication).flashAnimeRepository
    return ViewModelFactory(repository)
}

fun Fragment.getVmFactory(animeInfo: AnimeInfo): AnimeInfoViewModelFactory {
    val repository = (requireContext().applicationContext as FlashAnimeApplication).flashAnimeRepository
    return AnimeInfoViewModelFactory(repository, animeInfo)
}