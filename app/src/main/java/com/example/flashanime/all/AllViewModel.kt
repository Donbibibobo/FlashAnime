package com.example.flashanime.all

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.source.FlashAnimeRepository

class AllViewModel(private val flashAnimeRepository: FlashAnimeRepository): ViewModel() {


    var selecting: Boolean = false

    var categoriesSelected = listOf<String>()

    private val _combinedList: LiveData<List<AnimeInfo>> = flashAnimeRepository.getAllAnimeInfo()
    val combinedList: LiveData<List<AnimeInfo>>
        get() = _combinedList



    private val _selectedCategoryList = MutableLiveData<List<AnimeInfo>>()
    val selectedCategoryList: LiveData<List<AnimeInfo>>
        get() = _selectedCategoryList


    fun setSelectedList(categories: List<String>) {
        if (categories[0] != "全部"){
            val selectedCategoryList =
                _combinedList.value?.filter { animeInfo ->
                    categories.all { selectedCategory ->
                        selectedCategory in animeInfo.category
                    }
                }
            _selectedCategoryList.value = selectedCategoryList!!
        } else {
            _selectedCategoryList.value = _combinedList.value
        }
    }
}