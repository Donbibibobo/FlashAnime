package com.example.flashanime.detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.Episode
import com.example.flashanime.data.JLPTWordInfo
import com.example.flashanime.data.PlayWordEpisode
import com.example.flashanime.data.PlayWords
import com.example.flashanime.data.source.FlashAnimeRepository
import kotlinx.coroutines.launch

class DetailViewModel(
    private val flashAnimeRepository: FlashAnimeRepository,
//    private val arguments: AnimeInfo
): ViewModel() {

    // Detail has product data from arguments
    private var arguments = MutableLiveData<AnimeInfo>()
//        .apply {
//        value = arguments
//    }
    val animeInfoArg: LiveData<AnimeInfo>
        get() = arguments

    // fix ==================

    @SuppressLint("SuspiciousIndentation")
    fun fix() {
    val playWords1 = PlayWords("0:00:29.00","これ","N5")  // 少資訊
    val playWords2 = PlayWords("0:00:30.00","大変","N4")
    val playWords3 = PlayWords("0:00:33.00","早い","N5")
    val playWords4 = PlayWords("0:00:40.00","食べる","N5")
    val playWords5 = PlayWords("0:00:41.00","死ぬ","N1")
    val playWords6 = PlayWords("0:00:42.00","苦しい","N3")
    val playWords7 = PlayWords("0:00:49.00","眼鏡","N3")  // 兩個意思
    val playWords8 = PlayWords("0:00:54.00","僕達","N3") // 兩個意思
    val playWords9 = PlayWords("0:00:56.00","荒てる","N1")
    val playWords10 = PlayWords("0:01:00.00","ビル","N3")
    val playWords11 = PlayWords("0:01:01.00","全然","N5")
    val playWords12 = PlayWords("0:01:02.00","匂い","N3")
    val playWords13 = PlayWords("0:01:04.00","あそこ","N3")
    val playWords14 = PlayWords("0:01:05.00","俺","N3")
    val playWords15 = PlayWords("0:01:06.00","気に入る","N3")
    val playWords16 = PlayWords("0:01:08.00","私","N3")
    val playWords17 = PlayWords("0:01:09.00","有難い","N3")
    //
    val playWordsList1 = mutableListOf<PlayWords>(
        playWords1,playWords2,playWords3,playWords4,playWords5,playWords6,playWords7,playWords8,playWords9,playWords10,
        playWords11,playWords12,playWords13,playWords14,playWords15,playWords16,playWords17
    )
    val playWordsList0 = mutableListOf<PlayWords>()

    val playWordEpisodeFF = mutableListOf<PlayWordEpisode>()
        playWordEpisodeFF.add(PlayWordEpisode("1",playWordsList1))


    val typeList = listOf<String>("有趣")
    val m3u8UrlsType1 = listOf<String>("https://bahamut.akamaized.net/113306eb62e8e64bdc2ba1787c2a834df66e72d4/1080p/hdntl=exp=1695272243~acl=%2f*~data=hdntl,twterry10%3a34861%3a1%3a1%3a68686829~hmac=1efa69b472c0dd7d7ba4c39d3d34899354c147766940d283a2bbd2ab6ac2232a/key_b5000000.m3u8key")
    val image = "a"
    val animeInfo1 = AnimeInfo(
        false,
        "文豪野犬 汪",
        "2022/10/09",
        "全 1 集",
        "4.5",               // har
        playWordEpisodeFF,          // har
        typeList,
        m3u8UrlsType1,
        "$image"
    )
        arguments.value = animeInfo1
    // fix ==================
    }



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

    // episode recyclerview list constrain selected word API
    private val _wordInfoSelected = MutableLiveData<JLPTWordInfo>()
    val wordInfoSelected: LiveData<JLPTWordInfo>
        get() = _wordInfoSelected





    init {
        fix()
        createEpisodeList()

    }

    private fun createEpisodeList() {
        val mutableEpisodeList = mutableListOf<Episode>()
        var episodeCount: Int = 0
        List(arguments!!.value!!.videoSourceM3U8.size) {
            mutableEpisodeList.add(Episode.EpisodeUnSelected((it + 1).toString()))
            episodeCount++
        }
        mutableEpisodeList.removeLast()
        mutableEpisodeList.add(Episode.EpisodeSelected(episodeCount.toString()))
        _episodeMutableListDefault.value = mutableEpisodeList
    }
//
    fun selectedEpisodeList(selectedIndex: Int) {
        _episodeExo = selectedIndex
        val mutableEpisodeList = mutableListOf<Episode>()
        List(arguments!!.value!!.videoSourceM3U8.size) {
            mutableEpisodeList.add(Episode.EpisodeUnSelected((it + 1).toString()))
        }
        mutableEpisodeList[selectedIndex] = Episode.EpisodeSelected((selectedIndex+1).toString())
        _episodeMutableListSelected.value = mutableEpisodeList
        Log.i("test11","viewModel: $_episodeExo")
    }
//
    fun scrollToWord(position: Int, recyclerView: RecyclerView) {
        val center = recyclerView.height / 2
        val targetView = recyclerView.layoutManager?.findViewByPosition(position)
        targetView?.let {
            val top = it.top
            val toScroll = top - center + it.height / 2
            recyclerView.smoothScrollBy(0, toScroll)
        }
    }
//
    fun findMatchingWordPosition(currentTime: Long): Int {
        val currentEpisodeIndex = _episodeExo
        val currentEpisode = animeInfoArg.value?.wordsList?.getOrNull(currentEpisodeIndex)

        return currentEpisode?.playWords?.indexOfFirst {
            timeToMillis(it.time) >= currentTime
        } ?: -1
    }
//
    // change time to mills
    fun timeToMillis(timeString: String): Long {
        val splitByColon = timeString.split(":")
        val hours = splitByColon[0].toLong()
        val minutes = splitByColon[1].toLong()
        val splitByDot = splitByColon[2].split(".")
        val seconds = splitByDot[0].toLong()
        val millis = if (splitByDot.size > 1) splitByDot[1].toLong() else 0L

        return (hours * 3600000) + (minutes * 60000) + (seconds * 1000) + (millis * 10)
    }
//
    fun getWordInfo(word: String) {
        viewModelScope.launch {
            try {
                val wordInfo = flashAnimeRepository.getWordInfo(word)
                Log.i("getWordInfo", wordInfo.words[0].romaji)
                _wordInfoSelected.value = wordInfo.words[0]
            } catch (e: Exception) {
                throw IllegalArgumentException("DetailViewModel getWordInfo fail!")
            }
        }
    }

}