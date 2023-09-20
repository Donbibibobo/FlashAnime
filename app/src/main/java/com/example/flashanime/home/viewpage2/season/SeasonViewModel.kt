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

    val db = Firebase.firestore

    private val _animeInfo = MutableLiveData<List<AnimeInfo>>()
    val animeInfo: LiveData<List<AnimeInfo>>
        get() = _animeInfo

    init {
//        getAnimeInfo()
        animeSnapshot()
//        TemporaryFile.addFirebaseAnimeInfo()
    }

    private fun animeSnapshot() {
        val TAG: String = "SeasonViewModel"
        val articlesCollection = db.collection("animeInfo")
        Log.i("animeInfooo", "animeSnapshot called")

//        articlesCollection.addSnapshotListener { value, error ->
//            if (error != null) {
//                Log.w(TAG, "Listen failed.", error)
//                return@addSnapshotListener
//
//            } else if (value != null && !value.metadata.hasPendingWrites()) {
//
//                for (document in value!!) {
//                    Log.i(TAG, "====================")
//                    Log.i(TAG, "isCollected: ${document.data["isCollected"]}")
//                    Log.i(TAG, "title: ${document.data["title"]}")
//                    Log.i(TAG, "releaseTime: ${document.data["releaseTime"]}")
//                    Log.i(TAG, "episode: ${document.data["episode"]}")
//                    Log.i(TAG, "rate: ${document.data["rate"]}")
//                    Log.i(TAG, "wordsList: ${document.data["wordsList"]}")
//                    Log.i(TAG, "category: ${document.data["category"]}")
//                    Log.i(TAG, "videosId: ${document.data["videosId"]}")
//                    Log.i(TAG, "pictureURL: ${document.data["pictureURL"]}")
//                    Log.i(TAG, "====================")
//
//
//                    val animeInfo = AnimeInfo(
//                        document.data["isCollected"] as Boolean,
//                        document.data["title"].toString(),
//                        document.data["releaseTime"].toString(),
//                        document.data["episode"].toString(),
//                        document.data["rate"].toString(),
//                        document.data["wordsList"] as List<PlayWordEpisode>,
//                        document.data["category"] as List<String>,
//                        document.data["videosId"] as List<String>,
//                        document.data["pictureURL"].toString()
//                    )

        articlesCollection.document("Ni6QMzZJIIV6bHUzU4hO")
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.i(TAG, "Document data: ${document.data}")
                    val animeInfo = document.toObject(AnimeInfo::class.java)
                    if (animeInfo != null) {
                        // 這裡可以使用 animeInfo

                        val animeInfoList = mutableListOf<AnimeInfo>()
                        animeInfoList.add(animeInfo)
                        _animeInfo.value = animeInfoList
                        Log.i("animeInfooo", "$animeInfoList")
                        Log.i("animeInfooo", "animeInfo != null")


                        // add to TemporaryFile to let allAnimePage access info
                        TemporaryFile.TempoAnimeInfo = animeInfoList
                    }
                } else {
                    Log.i("animeInfooo", "No such document")

                }
            }
            .addOnFailureListener { exception ->
                Log.i("animeInfooo", "Error getting documents: $exception")
            }
//
//                    val animeInfoList = mutableListOf<AnimeInfo>()
//                    animeInfoList.add(animeInfo)
//                    _animeInfo.value = animeInfoList
//                    Log.i("animeInfooo", "$animeInfoList")
//
//                    // add to TemporaryFile to let allAnimePage access info
//                    TemporaryFile.TempoAnimeInfo = animeInfoList
//                }
//            }
//        }


    }

    private fun getAnimeInfo() {
        viewModelScope.launch {

            // data
//            val tplayWords1 = PlayWords("0:00:02.00","突然","N3")
//            val tplayWords2 = PlayWords("0:00:03.00","質問","N3")
//            val tplayWords3 = PlayWords("0:00:20.00","それ","N5")
//            val tplayWords4 = PlayWords("0:00:22.00","怒る","N5")
//            val tplayWords5 = PlayWords("0:00:29.00","待つ","N5")
//            val tplayWords6 = PlayWords("0:00:30.00","言う","N3")
//            val tplayWords7 = PlayWords("0:00:32.00","しかし","N3")
//            val tplayWords8 = PlayWords("0:00:33.00","私","N5")
//            val tplayWords9 = PlayWords("0:00:34.00","思う","N4")
//            val tplayWords10 = PlayWords("0:00:35.00","かもしれない","N4")
//            val tplayWords11 = PlayWords("0:00:42.00","働く","N4")
//            val tplayWords12 = PlayWords("0:00:43.00","前","N5")
//            val tplayWords13 = PlayWords("0:00:51.00","ちょうど","N3")
//            val tplayWords14 = PlayWords("0:00:55.00","在庫","N3")
//            val tplayWords15 = PlayWords("0:00:56.00","全滅","N1")
//            val tplayWords16 = PlayWords("0:00:57.00","午後","N5")
//            val tplayWords17 = PlayWords("0:01:02.00","了解","N4")
//            val tplayWords18 = PlayWords("0:01:05.00","買う","N5")
//            val tplayWords19 = PlayWords("0:01:16.00","慌ただしい","N2")
//            val tplayWords20 = PlayWords("0:01:17.00","多い","N4")
//            val tplayWords21 = PlayWords("0:01:19.00","刺激","N3")
//            val tplayWords22 = PlayWords("0:01:20.00","仕事","N5")
//            val tplayWords23 = PlayWords("0:01:23.00","多分","N4")
//            val tplayWords24 = PlayWords("0:01:24.00","本","N5")
//            val tplayWords25 = PlayWords("0:01:27.00","これ","N5")
//            val tplayWords26 = PlayWords("0:01:28.00","ありがとう","N5")
//            val tplayWords27 = PlayWords("0:01:30.00","孫","N4")
//            val tplayWords28 = PlayWords("0:01:31.00","最近","N3")
//            val tplayWords29 = PlayWords("0:01:33.00","楽しい","N4")
//            val tplayWords30 = PlayWords("0:01:35.00","気","N5")
//            val tplayWords31 = PlayWords("0:01:38.00","探す","N4")
//            val tplayWords32 = PlayWords("0:01:39.00","漫画","N3")
//            val tplayWords33 = PlayWords("0:01:40.00","こんなに","N4")
//            val tplayWords34 = PlayWords("0:01:42.00","ここ","N5")
//            val tplayWords35 = PlayWords("0:01:47.00","売り場","N3")
//            val tplayWords36 = PlayWords("0:01:57.00","濃い","N4")
//            val tplayWords37 = PlayWords("0:02:05.00","次","N5")
//            val tplayWords38 = PlayWords("0:02:10.00","分かる","N5")
//            val tplayWords39 = PlayWords("0:02:12.00","一見","N2")
//            val tplayWords40 = PlayWords("0:02:14.00","俳優","N3")
//            val tplayWords41 = PlayWords("0:02:18.00","厳か","N1")
//            val tplayWords42 = PlayWords("0:02:23.00","ポーズ","N3")
//            val tplayWords43 = PlayWords("0:02:25.00","欲しい","N4")
//            val tplayWords44 = PlayWords("0:02:27.00","見つかる","N4")
//            val tplayWords45 = PlayWords("0:02:39.00","どこ","N5")
//            val tplayWords46 = PlayWords("0:02:42.00","娘","N4")
//            val tplayWords47 = PlayWords("0:02:43.00","頼む","N4")
//            val tplayWords48 = PlayWords("0:02:45.00","見つかる","N4")
//            val tplayWords49 = PlayWords("0:02:46.00","帰る","N5")
//            val tplayWords50 = PlayWords("0:02:51.00","色","N5")
//            val tplayWords51 = PlayWords("0:02:55.00","興味","N3")
//            val tplayWords52 = PlayWords("0:02:59.00","絶版","N1")
//            val tplayWords53 = PlayWords("0:03:02.00","世の中","N3")
//            val tplayWords54 = PlayWords("0:03:17.00","全然","N4")
//            val tplayWords55 = PlayWords("0:03:28.00","商品","N3")
//            val tplayWords56 = PlayWords("0:03:40.00","整理","N3")
//            val tplayWords57 = PlayWords("0:03:53.00","素人","N3")
//            val tplayWords58 = PlayWords("0:03:55.00","混乱","N2")
//            val tplayWords59 = PlayWords("0:03:59.00","映画","N3")
//            val tplayWords60 = PlayWords("0:04:09.00","薄い","N4")
//
//
//
//
//            val playWordsListY = mutableListOf<PlayWords>(
//                tplayWords1, tplayWords2, tplayWords3, tplayWords4, tplayWords5, tplayWords6, tplayWords7, tplayWords8, tplayWords9, tplayWords10,
//                tplayWords11, tplayWords12, tplayWords13, tplayWords14, tplayWords15, tplayWords16, tplayWords17, tplayWords18, tplayWords19, tplayWords20,
//                tplayWords21, tplayWords22, tplayWords23, tplayWords24, tplayWords25, tplayWords26, tplayWords27, tplayWords28, tplayWords29, tplayWords30,
//                tplayWords31, tplayWords32, tplayWords33, tplayWords34, tplayWords35, tplayWords36, tplayWords37, tplayWords38, tplayWords39, tplayWords40,
//                tplayWords41, tplayWords42, tplayWords43, tplayWords44, tplayWords45, tplayWords46, tplayWords47, tplayWords48, tplayWords49, tplayWords50,
//                tplayWords51, tplayWords52, tplayWords53, tplayWords54, tplayWords55, tplayWords56, tplayWords57, tplayWords58, tplayWords59, tplayWords60
//            )
//            val playWordsListN = mutableListOf<PlayWords>()
//
//
//            val playWordEpisode = mutableListOf<PlayWordEpisode>()
//            playWordEpisode.add(PlayWordEpisode("1",playWordsListY))
//            playWordEpisode.add(PlayWordEpisode("2",playWordsListN))
//            playWordEpisode.add(PlayWordEpisode("3",playWordsListN))
//            playWordEpisode.add(PlayWordEpisode("4",playWordsListN))
//            playWordEpisode.add(PlayWordEpisode("5",playWordsListN))
//            playWordEpisode.add(PlayWordEpisode("6",playWordsListN))
//            playWordEpisode.add(PlayWordEpisode("7",playWordsListN))
//            playWordEpisode.add(PlayWordEpisode("8",playWordsListN))
//            playWordEpisode.add(PlayWordEpisode("9",playWordsListN))
//            playWordEpisode.add(PlayWordEpisode("10",playWordsListN))
//            playWordEpisode.add(PlayWordEpisode("11",playWordsListN))
//            playWordEpisode.add(PlayWordEpisode("12",playWordsListN))
//
//
//            val category = listOf<String>("有趣")
//            val videosId = listOf<String>("6TnHF1cW1gs","JGPsOi0Rb4Y","k8SoVKHY3t8","pZFp1AQ6Vds",
//                "Y1M09EMwOeI","G1NzVLbCMIQ","L6X-wA4rtko","wlkJRNw1Wl4","stjOwqbNHto","jLJ-utNorgk","Jso8YTWktqo","47xetpxC_u4")

//                    val animeInfo1 = AnimeInfo(
//                        false,
//                        "書店裡的骷髏店員本田",
//                        "2020/12/2",
//                        "全12集",
//                        "4.5",
//                        playWordEpisode,
//                        category,
//                        videosId,
//                        "https://p2.bahamut.com.tw/B/ACG/c/13/0000092513.JPG"
//                    )

//            val animeInfoList = mutableListOf<AnimeInfo>()
//                    animeInfoList.add(animeInfo1)
//                    _animeInfo.value = animeInfoList
//
//                    // add to TemporaryFile to let allAnimePage access info
//                    TemporaryFile.TempoAnimeInfo = animeInfoList



        }
    }
}