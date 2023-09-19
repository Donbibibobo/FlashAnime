package com.example.flashanime.home.viewpage2.season

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashanime.TemporaryFile
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.PlayWordEpisode
import com.example.flashanime.data.PlayWords
import com.example.flashanime.data.source.FlashAnimeRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
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
            try {
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

                    val playWordsList = mutableListOf<PlayWords>(
                        playWords1,playWords2,playWords3,playWords4,playWords5,playWords6,playWords7,playWords8,playWords9,playWords10,
                        playWords11,playWords12,playWords13,playWords14,playWords15,playWords16,playWords17
                    )
                    val playWordEpisode = mutableListOf<PlayWordEpisode>()
                    playWordEpisode.add(PlayWordEpisode("1",playWordsList)) //every playWordsList is episode one's words
                    playWordEpisode.add(PlayWordEpisode("2",playWordsList))
                    playWordEpisode.add(PlayWordEpisode("3",playWordsList))
                    playWordEpisode.add(PlayWordEpisode("4",playWordsList))
                    playWordEpisode.add(PlayWordEpisode("5",playWordsList))
                    playWordEpisode.add(PlayWordEpisode("6",playWordsList))
                    playWordEpisode.add(PlayWordEpisode("7",playWordsList))
                    playWordEpisode.add(PlayWordEpisode("8",playWordsList))
                    playWordEpisode.add(PlayWordEpisode("9",playWordsList))
                    playWordEpisode.add(PlayWordEpisode("10",playWordsList))
                    playWordEpisode.add(PlayWordEpisode("11",playWordsList))
                    playWordEpisode.add(PlayWordEpisode("12",playWordsList))


                    val animeInfo1 = AnimeInfo(
                        false,
                        "$title",
                        "$date",
                        "$episode",
                        "4.5",               // har
                        playWordEpisode,          // har
                        typeList,
                        m3u8UrlsType1,
                        "$image"
                    )

                    val animeInfoList = mutableListOf<AnimeInfo>()
                    animeInfoList.add(animeInfo1)
                    _animeInfo.value = animeInfoList

                    // add to TemporaryFile to let allAnimePage access info
                    TemporaryFile.TempoAnimeInfo = animeInfoList
                }
            }catch (e: Exception){
                throw IllegalArgumentException("SeasonViewModel getAnimeInfo fail!")
            }


        }
    }
}