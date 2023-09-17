package com.example.flashanime.home.viewpage2.season

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.source.FlashAnimeRepository
import com.example.flashanime.network.FlashAnimeApi
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.util.regex.Pattern


private const val TAG: String = "HomeViewModel"

class SeasonViewModel(private val flashAnimeRepository: FlashAnimeRepository): ViewModel() {


    private val _animeInfo = MutableLiveData<List<AnimeInfo>>()
    val animeInfo: LiveData<List<AnimeInfo>>
        get() = _animeInfo

    init {
        getAnimeInfo()
    }

    fun animeSnapshot() {
        val db = Firebase.firestore
        val articlesCollection = db.collection("articles")

    }

    private fun getAnimeInfo() {
        viewModelScope.launch {
            val result = flashAnimeRepository.getAnimeInfo(46596) // anime info have to give video_type

            if (result.isSuccessful && result.body() != null){
                Log.i("Pagedetail","all body: ${result.body().toString()}") // all body
                val resultBody = result.body().toString()

                // source
                val titlePattern = Pattern.compile("<meta\\s+name=\"keywords\"\\s+content=\"([^\"]+)\"\\s*/>")
                val imageRegex = """https://myself-bbs.com/data/attachment/forum/\d{6}/\d+/[\w.]+\.jpg""".toRegex()
                val typeRegex = """<li><span style="font-weight:bold;">作品類型</span>: (.*?)</li>""".toRegex()
                val dateRegex = """<li><span style="font-weight:bold;">首播日期</span>: (.*?)</li>""".toRegex()
                val episodeRegex = """<li><span style="font-weight:bold;">播出集數</span>: (.*?)</li>""".toRegex()
                val urlRegex = """(https://v.myself-bbs.com/player/play/\d+/\d+)""".toRegex()  // video_type1
                val hlsUrlRegex = """data-href="(https://v.myself-bbs.com/player/[^"]+)" """.toRegex()  // video_type2


                // filter
                val titleMatcher = titlePattern.matcher(resultBody)
                val imageMatch = imageRegex.findAll(resultBody)
                val typeMatch = typeRegex.find(resultBody)
                val dateMatch = dateRegex.find(resultBody)
                val episodeMatch = episodeRegex.find(resultBody)
                val urlMatches = urlRegex.findAll(resultBody)
                val hlsUrlUrlMatches = hlsUrlRegex.findAll(resultBody)


                // result
                var title: String? = null
                while (titleMatcher.find()) {
                    title = titleMatcher.group(1)
                    Log.i("Pagedetail","標題 : $title")
                }
                val image = imageMatch?.first()?.value ?: "未找到信息"
                var type = typeMatch?.groups?.get(1)?.value ?: "未找到信息"
                type = type.replace("""<font color="Silver">／</font>""", "、")
                val typeList = type.split("、").map { it.trim() }
                val date = dateMatch?.groupValues?.get(1) ?: "未找到信息"
                val episode = episodeMatch?.groupValues?.get(1) ?: "未找到信息"
                val urlListType1 = urlMatches.map { it.value }.toList()
                val m3u8UrlsType1 = urlListType1.map { url ->
                    url.replace("https://v.myself-bbs.com/player/play", "https://vpx16.myself-bbs.com/vpx")
                        .plus("/720p.m3u8")
                }
                val urlListType2 = hlsUrlUrlMatches.map { it.groupValues[1] }.toList()

                val m3u8UrlsType2 = urlListType2.map { originalUrl ->
                    val strippedUrl = originalUrl.removePrefix("https://v.myself-bbs.com/player/")
                    val prefixSegment = strippedUrl.take(4)
                    val middleSegment = strippedUrl.drop(4).take(2) + "/" + strippedUrl.drop(6).take(2) + "/" + strippedUrl.drop(8).take(2)
                    "https://vpx16.myself-bbs.com/hls/$middleSegment/$strippedUrl/index.m3u8"
                }


                Log.i("Pagedetail","圖片網址: $image")
                Log.i("Pagedetail","上架時間: $date")
                Log.i("Pagedetail","作品類型: $typeList")
                Log.i("Pagedetail","播出集數: $episode")
                Log.i("Pagedetail", "type1：每集網址: $m3u8UrlsType1")
                Log.i("Pagedetail", "type2：每集網址: $m3u8UrlsType2")



                // data (ps have to chose video type with m3u8)
                val wordsList = listOf<String>("同感","家")
                val animeInfo1 = AnimeInfo(
                    false,
                    "$title",
                    "$date",
                    "$episode",
                    "4.5",         // har
                    wordsList,          // har
                    typeList,
                    m3u8UrlsType1,
                    "$image"
                )

                val animeInfoList = mutableListOf<AnimeInfo>()
                animeInfoList.add(animeInfo1)
                _animeInfo.value = animeInfoList
            }



        }
    }
}