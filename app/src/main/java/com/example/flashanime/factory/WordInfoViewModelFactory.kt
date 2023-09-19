package com.example.flashanime.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.JLPTWordInfo
import com.example.flashanime.data.source.FlashAnimeRepository
import com.example.flashanime.detail.DetailViewModel
import com.example.flashanime.word.WordDialog
import com.example.flashanime.word.WordViewModel

@Suppress("UNCHECKED_CAST")
class WordInfoViewModelFactory(
    private val flashAnimeRepository: FlashAnimeRepository,
    private val wordInfo: JLPTWordInfo
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(WordViewModel::class.java) ->
                    WordViewModel(flashAnimeRepository, wordInfo)

                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}