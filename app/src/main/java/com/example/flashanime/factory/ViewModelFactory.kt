package com.example.flashanime.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flashanime.MainViewModel
import com.example.flashanime.all.AllViewModel
import com.example.flashanime.collected.CollectedViewModel
import com.example.flashanime.data.source.FlashAnimeRepository
import com.example.flashanime.detail.DetailViewModel
import com.example.flashanime.home.HomeViewModel
import com.example.flashanime.home.viewpage2.season.SeasonViewModel
import com.example.flashanime.home.viewpage2.week.WeekViewModel
import com.example.flashanime.profile.ProfileViewModel
import com.example.flashanime.vocabulary.VocabularyViewModel

// Factory for all ViewModels
@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val flashAnimeRepository: FlashAnimeRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(MainViewModel::class.java) ->
                    MainViewModel(flashAnimeRepository)

                isAssignableFrom(HomeViewModel::class.java) ->
                    HomeViewModel(flashAnimeRepository)

                isAssignableFrom(SeasonViewModel::class.java) ->
                    SeasonViewModel(flashAnimeRepository)

                isAssignableFrom(WeekViewModel::class.java) ->
                    WeekViewModel(flashAnimeRepository)

                isAssignableFrom(AllViewModel::class.java) ->
                    AllViewModel(flashAnimeRepository)

                isAssignableFrom(CollectedViewModel::class.java) ->
                    CollectedViewModel(flashAnimeRepository)

                isAssignableFrom(VocabularyViewModel::class.java) ->
                    VocabularyViewModel(flashAnimeRepository)

                isAssignableFrom(ProfileViewModel::class.java) ->
                    ProfileViewModel(flashAnimeRepository)

                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}
