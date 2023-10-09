package com.example.flashanime.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flashanime.data.PlayWordEpisodePlusAnimeInfo
import com.example.flashanime.data.WordsCollection
import com.example.flashanime.data.source.FlashAnimeRepository
import com.example.flashanime.word.WordViewModel
import com.example.flashanime.wordstest.WordTestViewModel

@Suppress("UNCHECKED_CAST")
class WordTestViewModelFactory(
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