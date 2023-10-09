package com.example.flashanime.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.PlayWordEpisode
import com.example.flashanime.data.PlayWordEpisodePlusAnimeInfo
import com.example.flashanime.data.PlayWords
import com.example.flashanime.data.source.FlashAnimeRepository
import com.example.flashanime.detail.DetailViewModel
import com.example.flashanime.vocabularydetail.VocabularyDetailViewModel
import com.example.flashanime.wordstest.WordTestAdapter
import com.example.flashanime.wordstest.WordTestViewModel


@Suppress("UNCHECKED_CAST")
class PlayWordsListViewModelFactory(
    private val flashAnimeRepository: FlashAnimeRepository,
    private val playWordEpisodePlusAnimeInfo: PlayWordEpisodePlusAnimeInfo
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(WordTestViewModel::class.java) ->
                    WordTestViewModel(flashAnimeRepository, playWordEpisodePlusAnimeInfo)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}