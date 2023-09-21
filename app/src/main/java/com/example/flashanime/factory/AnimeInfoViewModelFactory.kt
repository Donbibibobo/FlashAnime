package com.example.flashanime.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.source.FlashAnimeRepository
import com.example.flashanime.detail.DetailViewModel
import com.example.flashanime.vocabularydetail.VocabularyDetailViewModel

@Suppress("UNCHECKED_CAST")
class AnimeInfoViewModelFactory(
    private val flashAnimeRepository: FlashAnimeRepository,
    private val animeInfo: AnimeInfo
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(DetailViewModel::class.java) ->
                    DetailViewModel(flashAnimeRepository, animeInfo)

                isAssignableFrom(VocabularyDetailViewModel::class.java) ->
                    VocabularyDetailViewModel(flashAnimeRepository, animeInfo)

                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}