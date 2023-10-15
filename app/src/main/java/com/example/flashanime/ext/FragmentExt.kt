package com.example.flashanime.ext

import androidx.fragment.app.Fragment
import com.example.flashanime.FlashAnimeApplication
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.JLPTWordInfo
import com.example.flashanime.data.PlayWordEpisode
import com.example.flashanime.data.PlayWordEpisodePlusAnimeInfo
import com.example.flashanime.data.WordsCollection
import com.example.flashanime.factory.AnimeInfoViewModelFactory
import com.example.flashanime.factory.CollectedViewModelFactory
import com.example.flashanime.factory.PlayWordsListViewModelFactory
import com.example.flashanime.factory.ViewModelFactory
import com.example.flashanime.factory.WordInfoViewModelFactory
import com.example.flashanime.factory.WordTestViewModelFactory

fun Fragment.getVmFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as FlashAnimeApplication).flashAnimeRepository
    return ViewModelFactory(repository)
}

fun Fragment.getVmFactory(animeInfo: AnimeInfo): AnimeInfoViewModelFactory {
    val repository = (requireContext().applicationContext as FlashAnimeApplication).flashAnimeRepository
    return AnimeInfoViewModelFactory(repository, animeInfo)
}



fun Fragment.getVmFactory(fromProfile: Boolean): CollectedViewModelFactory {
    val repository = (requireContext().applicationContext as FlashAnimeApplication).flashAnimeRepository
    return CollectedViewModelFactory(repository, fromProfile)
}


fun Fragment.getVmFactory(wordsCollection: WordsCollection): WordInfoViewModelFactory {
    val repository = (requireContext().applicationContext as FlashAnimeApplication).flashAnimeRepository
    return WordInfoViewModelFactory(repository, wordsCollection)
}

fun Fragment.getVmFactory(playWordEpisodePlusAnimeInfo: PlayWordEpisodePlusAnimeInfo): WordTestViewModelFactory {
    val repository = (requireContext().applicationContext as FlashAnimeApplication).flashAnimeRepository
    return WordTestViewModelFactory(repository, playWordEpisodePlusAnimeInfo)
}