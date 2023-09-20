package com.example.flashanime.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.source.FlashAnimeRepository
import com.example.flashanime.detail.DetailViewModel

@Suppress("UNCHECKED_CAST")
class AnimeInfoViewModelFactory(
    private val flashAnimeRepository: FlashAnimeRepository,
//    private val animeInfo: AnimeInfo
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(DetailViewModel::class.java) ->
                    DetailViewModel(flashAnimeRepository)

                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}