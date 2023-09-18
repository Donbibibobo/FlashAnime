package com.example.flashanime.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.Episode
import com.example.flashanime.data.source.FlashAnimeRepository

class DetailViewModel(
    private val flashAnimeRepository: FlashAnimeRepository,
    private val arguments: AnimeInfo
): ViewModel() {

    // Detail has product data from arguments
    private val _animeInfoArg = MutableLiveData<AnimeInfo>().apply {
        value = arguments
    }
    val animeInfoArg: LiveData<AnimeInfo>
        get() = _animeInfoArg




    // episode recyclerview list default
    private val _episodeMutableListDefault = MutableLiveData<List<Episode>>()
    val episodeMutableListDefault: LiveData<List<Episode>>
        get() = _episodeMutableListDefault

    // episode recyclerview list selected
    private val _episodeMutableListSelected = MutableLiveData<List<Episode>>()
    val episodeMutableListSelected: LiveData<List<Episode>>
        get() = _episodeMutableListSelected


    // episode index for exoPlayer
    private var _episodeExo = 0
    val episodeExo: Int
        get() = _episodeExo




    init {
        createEpisodeList()
    }

    private fun createEpisodeList() {
        val mutableEpisodeList = mutableListOf<Episode>()
        var episodeCount: Int = 0
        List(arguments.videoSourceM3U8.size) {
            mutableEpisodeList.add(Episode.EpisodeUnSelected((it + 1).toString()))
            episodeCount++
        }
        mutableEpisodeList.removeLast()
        mutableEpisodeList.add(Episode.EpisodeSelected(episodeCount.toString()))
        _episodeMutableListDefault.value = mutableEpisodeList
    }

    fun selectedEpisodeList(selectedIndex: Int) {
        _episodeExo = selectedIndex
        val mutableEpisodeList = mutableListOf<Episode>()
        List(arguments.videoSourceM3U8.size) {
            mutableEpisodeList.add(Episode.EpisodeUnSelected((it + 1).toString()))
        }
        mutableEpisodeList[selectedIndex] = Episode.EpisodeSelected((selectedIndex+1).toString())
        _episodeMutableListSelected.value = mutableEpisodeList
        Log.i("test11","viewModel: $_episodeExo")
    }
}