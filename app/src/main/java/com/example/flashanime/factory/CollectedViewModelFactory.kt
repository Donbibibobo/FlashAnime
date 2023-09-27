package com.example.flashanime.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flashanime.collected.CollectedViewModel
import com.example.flashanime.data.source.FlashAnimeRepository

@Suppress("UNCHECKED_CAST")
class CollectedViewModelFactory(
    private val flashAnimeRepository: FlashAnimeRepository,
    private val fromProfile: Boolean
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(CollectedViewModel::class.java) ->
                    CollectedViewModel(flashAnimeRepository, fromProfile)

                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}