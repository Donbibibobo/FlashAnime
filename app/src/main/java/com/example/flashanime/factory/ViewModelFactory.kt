package com.example.flashanime.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flashanime.MainViewModel
import com.example.flashanime.all.AllViewModel
import com.example.flashanime.all.category.CategoryViewModel
import com.example.flashanime.collected.CollectedViewModel
import com.example.flashanime.data.source.FlashAnimeRepository
import com.example.flashanime.detail.DetailViewModel
import com.example.flashanime.history.WatchHistoryViewModel
import com.example.flashanime.home.HomeViewModel
import com.example.flashanime.home.viewpage2.season.SeasonViewModel
import com.example.flashanime.home.viewpage2.week.WeekViewModel
import com.example.flashanime.login.LoginViewModel
import com.example.flashanime.profile.ProfileViewModel
import com.example.flashanime.vocabulary.VocabularyViewModel
import com.example.flashanime.vocabularydetail.VocabularyDetailViewModel
import com.example.flashanime.word.WordViewModel
import com.example.flashanime.wordscollection.WordsCollectionViewModel
import com.example.flashanime.wordstest.WordTestViewModel

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

                isAssignableFrom(VocabularyViewModel::class.java) ->
                    VocabularyViewModel(flashAnimeRepository)

                isAssignableFrom(ProfileViewModel::class.java) ->
                    ProfileViewModel(flashAnimeRepository)

                isAssignableFrom(CategoryViewModel::class.java) ->
                    CategoryViewModel(flashAnimeRepository)

                isAssignableFrom(LoginViewModel::class.java) ->
                    LoginViewModel(flashAnimeRepository)

                isAssignableFrom(WatchHistoryViewModel::class.java) ->
                    WatchHistoryViewModel(flashAnimeRepository)

                isAssignableFrom(WordsCollectionViewModel::class.java) ->
                    WordsCollectionViewModel(flashAnimeRepository)


                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}
