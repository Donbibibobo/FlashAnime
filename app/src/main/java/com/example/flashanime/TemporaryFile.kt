package com.example.flashanime

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.WeeklyAnime
import com.example.flashanime.data.WeeklyAnimeList
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

object TemporaryFile {

    var TempoAnimeInfo: List<AnimeInfo>? = null

    @SuppressLint("StaticFieldLeak")
    val db = Firebase.firestore


    fun addFirebaseAnimeInfo() {

            val playWords1 = mapOf("time" to "0:00:02.00","word" to "突然","level" to "N3")
            val playWords2 = mapOf("time" to "0:00:03.00","word" to "質問","level" to "N3")
            val playWords3 = mapOf("time" to "0:00:20.00","word" to "それ","level" to "N5")
            val playWords4 = mapOf("time" to "0:00:22.00","word" to "怒る","level" to "N5")
            val playWords5 = mapOf("time" to "0:00:29.00","word" to "待つ","level" to "N5")
            val playWords6 = mapOf("time" to "0:00:30.00","word" to "言う","level" to "N3")
            val playWords7 = mapOf("time" to "0:00:32.00","word" to "しかし","level" to "N3")
            val playWords8 = mapOf("time" to "0:00:33.00","word" to "私","level" to "N5")
            val playWords9 = mapOf("time" to "0:00:34.00","word" to "思う","level" to "N4")
            val playWords10 = mapOf("time" to "0:00:35.00","word" to "かもしれない","level" to "N4")
            val playWords11 = mapOf("time" to "0:00:42.00","word" to "働く","level" to "N4")
            val playWords12 = mapOf("time" to "0:00:43.00","word" to "前","level" to "N5")
            val playWords13 = mapOf("time" to "0:00:51.00","word" to "ちょうど","level" to "N3")
            val playWords14 = mapOf("time" to "0:00:55.00","word" to "在庫","level" to "N3")
            val playWords15 = mapOf("time" to "0:00:56.00","word" to "全滅","level" to "N1")
            val playWords16 = mapOf("time" to "0:00:57.00","word" to "午後","level" to "N5")
            val playWords17 = mapOf("time" to "0:01:02.00","word" to "了解","level" to "N4")
            val playWords18 = mapOf("time" to "0:01:05.00","word" to "買う","level" to "N5")
            val playWords19 = mapOf("time" to "0:01:16.00","word" to "慌ただしい","level" to "N2")
            val playWords20 = mapOf("time" to "0:01:17.00","word" to "多い","level" to "N4")
            val playWords21 = mapOf("time" to "0:01:19.00","word" to "刺激","level" to "N3")
            val playWords22 = mapOf("time" to "0:01:20.00","word" to "仕事","level" to "N5")
            val playWords23 = mapOf("time" to "0:01:23.00","word" to "多分","level" to "N4")
            val playWords24 = mapOf("time" to "0:01:24.00","word" to "本","level" to "N5")
            val playWords25 = mapOf("time" to "0:01:27.00","word" to "これ","level" to "N5")
            val playWords26 = mapOf("time" to "0:01:28.00","word" to "ありがとう","level" to "N5")
            val playWords27 = mapOf("time" to "0:01:30.00","word" to "孫","level" to "N4")
            val playWords28 = mapOf("time" to "0:01:31.00","word" to "最近","level" to "N3")
            val playWords29 = mapOf("time" to "0:01:33.00","word" to "楽しい","level" to "N4")
            val playWords30 = mapOf("time" to "0:01:35.00","word" to "気","level" to "N5")
            val playWords31 = mapOf("time" to "0:01:38.00","word" to "探す","level" to "N4")
            val playWords32 = mapOf("time" to "0:01:39.00","word" to "漫画","level" to "N3")
            val playWords33 = mapOf("time" to "0:01:40.00","word" to "こんなに","level" to "N4")
            val playWords34 = mapOf("time" to "0:01:42.00","word" to "ここ","level" to "N5")
            val playWords35 = mapOf("time" to "0:01:47.00","word" to "売り場","level" to "N3")
            val playWords36 = mapOf("time" to "0:01:57.00","word" to "濃い","level" to "N4")
            val playWords37 = mapOf("time" to "0:02:05.00","word" to "次","level" to "N5")
            val playWords38 = mapOf("time" to "0:02:10.00","word" to "分かる","level" to "N5")
            val playWords39 = mapOf("time" to "0:02:12.00","word" to "一見","level" to "N2")
            val playWords40 = mapOf("time" to "0:02:14.00","word" to "俳優","level" to "N3")
            val playWords41 = mapOf("time" to "0:02:18.00","word" to "厳か","level" to "N1")
            val playWords42 = mapOf("time" to "0:02:23.00","word" to "ポーズ","level" to "N3")
            val playWords43 = mapOf("time" to "0:02:25.00","word" to "欲しい","level" to "N4")
            val playWords44 = mapOf("time" to "0:02:27.00","word" to "乗る","level" to "N5")
            val playWords45 = mapOf("time" to "0:02:30.00","word" to "凄い","level" to "N3")
            val playWords46 = mapOf("time" to "0:02:32.00","word" to "綺麗","level" to "N4")
            val playWords47 = mapOf("time" to "0:02:35.00","word" to "もっと","level" to "N5")
            val playWords48 = mapOf("time" to "0:02:37.00","word" to "音","level" to "N5")
            val playWords49 = mapOf("time" to "0:02:40.00","word" to "大好き","level" to "N4")
            val playWords50 = mapOf("time" to "0:02:44.00","word" to "弾く","level" to "N3")
            val playWords51 = mapOf("time" to "0:02:50.00","word" to "特に","level" to "N3")
            val playWords52 = mapOf("time" to "0:02:53.00","word" to "緩い","level" to "N3")
            val playWords53 = mapOf("time" to "0:03:00.00","word" to "窓","level" to "N5")
            val playWords54 = mapOf("time" to "0:03:02.00","word" to "漫画家","level" to "N2")
            val playWords55 = mapOf("time" to "0:03:03.00","word" to "曲","level" to "N5")
            val playWords56 = mapOf("time" to "0:03:07.00","word" to "続ける","level" to "N3")
            val playWords57 = mapOf("time" to "0:03:09.00","word" to "画家","level" to "N2")
            val playWords58 = mapOf("time" to "0:03:13.00","word" to "所","level" to "N4")
            val playWords59 = mapOf("time" to "0:03:17.00","word" to "狭い","level" to "N4")
            val playWords60 = mapOf("time" to "0:04:09.00","word" to "薄い","level" to "N4")

            val playWordsEmpty = listOf<Map<String,Any>>(mapOf("time" to "0:00:00.00","word" to "(尚未新增單字資源)","level" to ""))

            val episode1Data = mapOf(
                "episodeNum" to "1",
                "playWords" to listOf(
                    playWords1, playWords2, playWords3, playWords4, playWords5, playWords6, playWords7, playWords8, playWords9, playWords10,
                    playWords11, playWords12, playWords13, playWords14, playWords15, playWords16, playWords17, playWords18, playWords19, playWords20,
                    playWords21, playWords22, playWords23, playWords24, playWords25, playWords26, playWords27, playWords28, playWords29, playWords30,
                    playWords31, playWords32, playWords33, playWords34, playWords35, playWords36, playWords37, playWords38, playWords39, playWords40,
                    playWords41, playWords42, playWords43, playWords44, playWords45, playWords46, playWords47, playWords48, playWords49, playWords50,
                    playWords51, playWords52, playWords53, playWords54, playWords55, playWords56, playWords57, playWords58, playWords59, playWords60
                )
            )
            val episode01Data = mapOf("episodeNum" to "1", "playWords" to playWordsEmpty)
            val episode2Data = mapOf("episodeNum" to "2", "playWords" to playWordsEmpty)
            val episode3Data = mapOf("episodeNum" to "3", "playWords" to playWordsEmpty)
            val episode4Data = mapOf("episodeNum" to "4", "playWords" to playWordsEmpty)
            val episode5Data = mapOf("episodeNum" to "5", "playWords" to playWordsEmpty)
            val episode6Data = mapOf("episodeNum" to "6", "playWords" to playWordsEmpty)
            val episode7Data = mapOf("episodeNum" to "7", "playWords" to playWordsEmpty)
            val episode8Data = mapOf("episodeNum" to "8", "playWords" to playWordsEmpty)
            val episode9Data = mapOf("episodeNum" to "9", "playWords" to playWordsEmpty)
            val episode10Data = mapOf("episodeNum" to "10", "playWords" to playWordsEmpty)
            val episode11Data = mapOf("episodeNum" to "11", "playWords" to playWordsEmpty)
            val episode12Data = mapOf("episodeNum" to "12", "playWords" to playWordsEmpty)


            val category = listOf<String>("有趣")
            val videosId = listOf<String>("6TnHF1cW1gs","JGPsOi0Rb4Y","k8SoVKHY3t8","pZFp1AQ6Vds",
                "Y1M09EMwOeI","G1NzVLbCMIQ","L6X-wA4rtko","wlkJRNw1Wl4","stjOwqbNHto","jLJ-utNorgk","Jso8YTWktqo","47xetpxC_u4")

        // add new anime below



            // fire
            val animeInfoDocument = db.collection("animeInfo").document() //document(IWoNOgF1WWR7rgX7IOuD)

            val wordsDataList = mutableListOf<Map<String, Any>>()

            wordsDataList.add(episode1Data)
            wordsDataList.add(episode2Data)
            wordsDataList.add(episode3Data)
            wordsDataList.add(episode4Data)
            wordsDataList.add(episode5Data)
            wordsDataList.add(episode6Data)
            wordsDataList.add(episode7Data)
            wordsDataList.add(episode8Data)
            wordsDataList.add(episode9Data)
            wordsDataList.add(episode10Data)
            wordsDataList.add(episode11Data)
            wordsDataList.add(episode12Data)


            val animeData = hashMapOf(
                "animeId" to animeInfoDocument.id,
                "title" to "書店裡的骷髏店員本田",
                "releaseTime" to "2020/12/2",
                "episode" to "全12集",
                "rate" to "4.5",
                "wordsList" to wordsDataList,
                "category" to category,
                "videosId" to videosId,
                "pictureURL" to "https://p2.bahamut.com.tw/B/ACG/c/13/0000092513.JPG"
            )

        // [add different document]
            animeInfoDocument.set(animeData)
                .addOnSuccessListener { documentReference ->
                    Log.d("AddFirebase", "DocumentSnapshot written with ID: $documentReference")
                }
                .addOnFailureListener { e ->
                    Log.w("AddFirebase", "Error adding document", e)
                }

    }



    fun addWeekList() {
        // fire
        val articlesCollection = db.collection("weekInfo")

        val monday1a1 = WeeklyAnime("01:05","黑暗集會","第12集")
        val monday1a2 = WeeklyAnime("21:30","LV1 魔王與獨居廢勇者","第12集")
        val monday1a3 = WeeklyAnime("22:00","正宗君的復仇","第12集")
        val mondayList = WeeklyAnimeList(listOf(monday1a1,monday1a2,monday1a3))

        val tuesday2a1 = WeeklyAnime("00:30","物之古物奇譚","第12集")
        val tuesday2a2 = WeeklyAnime("01:00","甜點轉生","第12集")
        val tuesday2a3 = WeeklyAnime("01:30","妖幻三重奏","第12集")
        val tuesday2a4 = WeeklyAnime("02:00","滿懷美夢的少年是現實主義者","第12集")
        val tuesday2a5 = WeeklyAnime("23:30","我喜歡的女孩忘記戴眼鏡","第12集")
        val tuesdayList = WeeklyAnimeList(listOf(tuesday2a1,tuesday2a2,tuesday2a3,tuesday2a4,tuesday2a5))

        val wednesday3a1 = WeeklyAnime("01:29","勇者赫魯庫","第12集")
        val wednesday3a2 = WeeklyAnime("22:00","文豪野犬 第五季","第12集")
        val wednesdayList = WeeklyAnimeList(listOf(wednesday3a1,wednesday3a2))

        val thursday4a1 = WeeklyAnime("00:00","獻祭公主與獸王","第22集")
        val thursday4a2 = WeeklyAnime("01:00","白聖女與黑牧師","第10集")
        val thursday4a3 = WeeklyAnime("01:25","不死少女的謀殺鬧劇","第11集")
        val thursday4a4 = WeeklyAnime("21:35","BanG Dream! It's MyGO!!!!!","第13集")
        val thursday4a5 = WeeklyAnime("22:30","間諜教室","第22集")
        val thursday4a6 = WeeklyAnime("22:35","打工吧，魔王大人！第二季","第22集")
        val thursday4a7 = WeeklyAnime("23:40","成為悲劇元兇的最強異端，最後頭目女王為了人民犧牲奉獻","第22集")
        val thursday4a8 = WeeklyAnime("23:56","咒術迴戰 第二季","第22集")
        val thursdayList = WeeklyAnimeList(listOf(thursday4a1,thursday4a2,thursday4a3,thursday4a4,thursday4a5,thursday4a6,thursday4a7,thursday4a8))

        val friday5a1 = WeeklyAnime("01:25","神劍闖江湖 ―明治劍客浪漫譚―","第11集")
        val friday5a2 = WeeklyAnime("01:30","聖者無雙～上班族的異世界生存之道～","第11集")
        val friday5a3 = WeeklyAnime("20:35","銀砂糖師與黑妖精～sugar apple fairy tale～","第23集")
        val fridayList = WeeklyAnimeList(listOf(friday5a1,friday5a2,friday5a3))

        val saturdayList = WeeklyAnimeList(listOf(monday1a1,monday1a2,monday1a3))

        val sundayList = WeeklyAnimeList(listOf(tuesday2a1,tuesday2a2,tuesday2a3,tuesday2a4,tuesday2a5))

        val weeklyData = hashMapOf(
            "monday" to mondayList,
            "tuesday" to tuesdayList,
            "wednesday" to wednesdayList,
            "thursday" to thursdayList,
            "friday" to fridayList,
            "saturday" to saturdayList,
            "sunday" to sundayList,
        )

        // [update same document]
        articlesCollection.document("lYM4NtqSo2UVEjLxQwXA")
            .set(weeklyData)
            .addOnSuccessListener { documentReference ->
                Log.d("AddFirebase", "DocumentSnapshot written with ID: $documentReference")
            }
            .addOnFailureListener { e ->
                Log.w("AddFirebase", "Error adding document", e)
            }
    }
}