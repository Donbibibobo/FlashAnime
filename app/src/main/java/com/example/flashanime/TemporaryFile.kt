package com.example.flashanime

import android.annotation.SuppressLint
import android.util.Log
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.WeeklyAnime
import com.example.flashanime.data.WeeklyAnimeList
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object TemporaryFile {

    var TempoAnimeInfo: List<AnimeInfo>? = null

    @SuppressLint("StaticFieldLeak")
    val db = Firebase.firestore


    fun addFirebaseAnimeInfo() {

    // 1 --------
            // 0:00 - 3:00
            val playWords1 = mapOf("time" to "0:00:02.00","word" to "突然","level" to "N3","isCollected" to false)
            val playWords2 = mapOf("time" to "0:00:03.00","word" to "質問","level" to "N5","isCollected" to false)
            val playWords3 = mapOf("time" to "0:00:04.00","word" to "書店","level" to "N2","isCollected" to false)
            val playWords4 = mapOf("time" to "0:00:05.00","word" to "言う","level" to "N5","isCollected" to false)
            val playWords5 = mapOf("time" to "0:00:08.00","word" to "優しい","level" to "N4","isCollected" to false)
            val playWords6 = mapOf("time" to "0:00:09.00","word" to "穏やか","level" to "N3","isCollected" to false)
            val playWords7 = mapOf("time" to "0:00:13.00","word" to "女子","level" to "N3","isCollected" to false)
            val playWords8 = mapOf("time" to "0:00:14.00","word" to "髪","level" to "N4","isCollected" to false)
            val playWords9 = mapOf("time" to "0:00:17.00","word" to "男子","level" to "N3","isCollected" to false)
            val playWords10 = mapOf("time" to "0:00:19.00","word" to "それ","level" to "N5","isCollected" to false)
            val playWords11 = mapOf("time" to "0:00:21.00","word" to "怒る","level" to "N4","isCollected" to false)
            val playWords12 = mapOf("time" to "0:00:23.00","word" to "暇","level" to "N5","isCollected" to false)
            val playWords13 = mapOf("time" to "0:00:25.00","word" to "刺激","level" to "N3","isCollected" to false)
            val playWords14 = mapOf("time" to "0:00:27.00","word" to "単純","level" to "N3","isCollected" to false)
            val playWords15 = mapOf("time" to "0:00:28.00","word" to "待つ","level" to "N5","isCollected" to false)
            val playWords17 = mapOf("time" to "0:00:31.00","word" to "しかし","level" to "N5","isCollected" to false)
            val playWords18 = mapOf("time" to "0:00:32.00","word" to "私","level" to "N5","isCollected" to false)
            val playWords19 = mapOf("time" to "0:00:33.00","word" to "思う","level" to "N4","isCollected" to false)
            val playWords20 = mapOf("time" to "0:00:34.00","word" to "かもしれない","level" to "N3","isCollected" to false)
            val playWords21 = mapOf("time" to "0:00:41.00","word" to "働く","level" to "N5","isCollected" to false)
            val playWords22 = mapOf("time" to "0:00:42.00","word" to "前","level" to "N5","isCollected" to false)
            val playWords23 = mapOf("time" to "0:00:51.00","word" to "ちょうど","level" to "N5","isCollected" to false)
            val playWords24 = mapOf("time" to "0:00:54.00","word" to "在庫","level" to "N1","isCollected" to false)
            val playWords25 = mapOf("time" to "0:00:55.00","word" to "全滅","level" to "N1","isCollected" to false)
            val playWords26 = mapOf("time" to "0:00:56.00","word" to "午後","level" to "N5","isCollected" to false)
            val playWords27 = mapOf("time" to "0:00:58.00","word" to "店","level" to "N5","isCollected" to false)
            val playWords28 = mapOf("time" to "0:01:00.00","word" to "了解","level" to "N1","isCollected" to false)
            val playWords29 = mapOf("time" to "0:01:02.00","word" to "冊","level" to "N1","isCollected" to false)
            val playWords30 = mapOf("time" to "0:01:04.00","word" to "買う","level" to "N5","isCollected" to false)
            val playWords31 = mapOf("time" to "0:01:07.00","word" to "映画","level" to "N5","isCollected" to false)
            val playWords32 = mapOf("time" to "0:01:15.00","word" to "慌ただしい","level" to "N1","isCollected" to false)
            val playWords33 = mapOf("time" to "0:01:16.00","word" to "多い","level" to "N5","isCollected" to false)
            val playWords34 = mapOf("time" to "0:01:19.00","word" to "仕事","level" to "N5","isCollected" to false)
            val playWords35 = mapOf("time" to "0:01:22.00","word" to "多分","level" to "N3","isCollected" to false)
            val playWords36 = mapOf("time" to "0:01:23.00","word" to "本","level" to "N5","isCollected" to false)
            val playWords37 = mapOf("time" to "0:01:27.00","word" to "これ","level" to "N5","isCollected" to false)
            val playWords38 = mapOf("time" to "0:01:28.00","word" to "ありがとう","level" to "N3","isCollected" to false)
            val playWords39 = mapOf("time" to "0:01:29.00","word" to "孫","level" to "N3","isCollected" to false)
            val playWords40 = mapOf("time" to "0:01:30.00","word" to "最近","level" to "N4","isCollected" to false)
            val playWords41 = mapOf("time" to "0:01:32.00","word" to "楽しい","level" to "N5","isCollected" to false)
            val playWords42 = mapOf("time" to "0:01:33.00","word" to "気","level" to "N4","isCollected" to false)
            val playWords43 = mapOf("time" to "0:01:36.00","word" to "探す","level" to "N4","isCollected" to false)
            val playWords44 = mapOf("time" to "0:01:38.00","word" to "漫画","level" to "N4","isCollected" to false)
            val playWords45 = mapOf("time" to "0:01:39.00","word" to "こんなに","level" to "N3","isCollected" to false)
            val playWords46 = mapOf("time" to "0:01:42.00","word" to "ここ","level" to "N5","isCollected" to false)
            val playWords47 = mapOf("time" to "0:01:46.00","word" to "売り場","level" to "N4","isCollected" to false)
            val playWords48 = mapOf("time" to "0:01:51.00","word" to "主に","level" to "N3","isCollected" to false)
            val playWords49 = mapOf("time" to "0:01:53.00","word" to "濃い","level" to "N3","isCollected" to false)
            val playWords50 = mapOf("time" to "0:01:54.00","word" to "客","level" to "N4","isCollected" to false)
            val playWords51 = mapOf("time" to "0:01:58.00","word" to "願い","level" to "N3","isCollected" to false)
            val playWords52 = mapOf("time" to "0:02:03.00","word" to "次","level" to "N5","isCollected" to false)
            val playWords53 = mapOf("time" to "0:02:10.00","word" to "分かる","level" to "N5","isCollected" to false)
            val playWords54 = mapOf("time" to "0:02:11.00","word" to "一見","level" to "N1","isCollected" to false)
            val playWords55 = mapOf("time" to "0:02:13.00","word" to "俳優","level" to "N3","isCollected" to false)
            val playWords56 = mapOf("time" to "0:02:16.00","word" to "厳か","level" to "N1","isCollected" to false)
            val playWords57 = mapOf("time" to "0:02:20.00","word" to "狂う","level" to "N3","isCollected" to false)
            val playWords58 = mapOf("time" to "0:02:23.00","word" to "ポーズ","level" to "N1","isCollected" to false)
            val playWords59 = mapOf("time" to "0:02:24.00","word" to "欲しい","level" to "N5","isCollected" to false)
            val playWords60 = mapOf("time" to "0:02:25.00","word" to "見つける","level" to "N4","isCollected" to false)
            val playWords61 = mapOf("time" to "0:02:27.00","word" to "見せる","level" to "N5","isCollected" to false)
            val playWords62 = mapOf("time" to "0:02:38.00","word" to "どこ","level" to "N5","isCollected" to false)
            val playWords63 = mapOf("time" to "0:02:41.00","word" to "娘","level" to "N4","isCollected" to false)
            val playWords64 = mapOf("time" to "0:02:42.00","word" to "頼む","level" to "N5","isCollected" to false)
            val playWords65 = mapOf("time" to "0:02:44.00","word" to "見つかる","level" to "N4","isCollected" to false)
            val playWords66 = mapOf("time" to "0:02:45.00","word" to "帰る","level" to "N5","isCollected" to false)
            val playWords67 = mapOf("time" to "0:02:50.00","word" to "疲れる","level" to "N5","isCollected" to false)
            val playWords68 = mapOf("time" to "0:02:54.00","word" to "興味","level" to "N4","isCollected" to false)
            val playWords69 = mapOf("time" to "0:02:57.00","word" to "未定","level" to "N1","isCollected" to false)
            val playWords70 = mapOf("time" to "0:02:58.00","word" to "切る","level" to "N5","isCollected" to false)
            val playWords71 = mapOf("time" to "0:02:59.00","word" to "絶版","level" to "N1","isCollected" to false)

            // 04:00
            val playWords101 = mapOf("time" to "0:04:08.00","word" to "薄い","level" to "N5","isCollected" to false)
            // 05:00
            val playWords102 = mapOf("time" to "0:05:06.00","word" to "無念","level" to "N1","isCollected" to false)
            // 06:00
            val playWords103 = mapOf("time" to "0:06:00.00","word" to "心掛け","level" to "N1","isCollected" to false)
            // 07:00
            val playWords104 = mapOf("time" to "0:07:03.00","word" to "すごい","level" to "N3","isCollected" to false)
            // 08:00
            val playWords105 = mapOf("time" to "0:08:05.00","word" to "見る","level" to "N3","isCollected" to false)
            // 09:00
            val playWords106 = mapOf("time" to "0:09:04.00","word" to "面白い","level" to "N1","isCollected" to false)
            // 10:00
            val playWords107 = mapOf("time" to "0:10:09.00","word" to "内容","level" to "N3","isCollected" to false)

        // 2 --------
            val words1 = mapOf("time" to "0:00:04.00","word" to "連休","level" to "N1","isCollected" to false)
            val words2 = mapOf("time" to "0:00:05.00","word" to "休み","level" to "N5","isCollected" to false)
            val words3 = mapOf("time" to "0:00:07.00","word" to "出る","level" to "N5","isCollected" to false)
            val words4 = mapOf("time" to "0:00:08.00","word" to "発売","level" to "N2","isCollected" to false)
            val words5 = mapOf("time" to "0:00:13.00","word" to "察する","level" to "N1","isCollected" to false)
            val words6 = mapOf("time" to "0:00:16.00","word" to "書籍","level" to "N2","isCollected" to false)
            val words7 = mapOf("time" to "0:00:17.00","word" to "まだ","level" to "N5","isCollected" to false)
            val words8 = mapOf("time" to "0:00:22.00","word" to "いちばん","level" to "N5","isCollected" to false)
            val words9 = mapOf("time" to "0:00:27.00","word" to "危ない","level" to "N5","isCollected" to false)
            val words10 = mapOf("time" to "0:00:29.00","word" to "冊","level" to "N1","isCollected" to false)
            val words11 = mapOf("time" to "0:00:30.00","word" to "補充","level" to "N1","isCollected" to false)
            val words12 = mapOf("time" to "0:00:32.00","word" to "明日","level" to "N5","isCollected" to false)
            val words13 = mapOf("time" to "0:00:33.00","word" to "死ぬ","level" to "N5","isCollected" to false)
            val words14 = mapOf("time" to "0:00:34.00","word" to "準備","level" to "N4","isCollected" to false)
            val words15 = mapOf("time" to "0:00:35.00","word" to "帰る","level" to "N5","isCollected" to false)
            val words16 = mapOf("time" to "0:00:40.00","word" to "足す","level" to "N4","isCollected" to false)
            val words17 = mapOf("time" to "0:00:42.00","word" to "消える","level" to "N5","isCollected" to false)
            val words18 = mapOf("time" to "0:00:45.00","word" to "見る","level" to "N3","isCollected" to false)
            val words19 = mapOf("time" to "0:00:46.00","word" to "働く","level" to "N5","isCollected" to false)
            val words20 = mapOf("time" to "0:00:48.00","word" to "思う","level" to "N4","isCollected" to false)

            // 01:00
            val words21 = mapOf("time" to "0:01:02.00","word" to "分ける","level" to "N3","isCollected" to false)
            // 02:00
            val words22 = mapOf("time" to "0:01:58.00","word" to "在庫","level" to "N1","isCollected" to false)
            // 03:00
            val words23 = mapOf("time" to "0:03:01.00","word" to "付く","level" to "N4","isCollected" to false)
            // 04: 00
            val words24 = mapOf("time" to "0:03:59.00","word" to "終わる","level" to "N1","isCollected" to false)
            // 05:00
            val words25 = mapOf("time" to "0:05:05.00","word" to "重い","level" to "N5","isCollected" to false)
            // 06:00
            val words26 = mapOf("time" to "0:06:04.00","word" to "皆さん","level" to "N5","isCollected" to false)
            // 07:00
            val words27 = mapOf("time" to "0:07:04.00","word" to "死ぬ","level" to "N5","isCollected" to false)
            // 08:00
            val words28 = mapOf("time" to "0:08:02.00","word" to "全然","level" to "N3","isCollected" to false)
            // 09:00
            val words29 = mapOf("time" to "0:08:59.00","word" to "担当","level" to "N3","isCollected" to false)
            // 10:00
            val words30 = mapOf("time" to "0:09:25.00","word" to "広い","level" to "N5","isCollected" to false)


//            val playWordsEmpty = listOf<Map<String,Any>>(mapOf("time" to "0:00:00.00","word" to "(尚未新增單字資源)","level" to "","isCollected" to false))

            val episode1Data = mapOf(
                "episodeNum" to "1",
                "playWords" to listOf(
                    playWords1, playWords2, playWords3, playWords4, playWords5, playWords6, playWords7, playWords8, playWords9, playWords10,
                    playWords11, playWords12, playWords13, playWords14, playWords15, playWords17, playWords18, playWords19, playWords20,
                    playWords21, playWords22, playWords23, playWords24, playWords25, playWords26, playWords27, playWords28, playWords29, playWords30,
                    playWords31, playWords32, playWords33, playWords34, playWords35, playWords36, playWords37, playWords38, playWords39, playWords40,
                    playWords41, playWords42, playWords43, playWords44, playWords45, playWords46, playWords47, playWords48, playWords49, playWords50,
                    playWords51, playWords52, playWords53, playWords54, playWords55, playWords56, playWords57, playWords58, playWords59, playWords60,
                    playWords61, playWords62, playWords63, playWords64, playWords65, playWords66, playWords67, playWords68, playWords69, playWords70,
                    playWords71, playWords101, playWords102, playWords103, playWords104, playWords105, playWords106, playWords107
                )
            )
            val episode2Data = mapOf(
                "episodeNum" to "2",
                "playWords" to listOf(
                    words1, words2, words3, words4, words5, words6, words7, words8, words9, words10,
                    words11, words12, words13, words14, words15, words16, words17, words18, words19, words20,
                    words21, words22, words23, words24, words25, words26, words27, words28, words29, words30,
                )
            )


//            val episode01Data = mapOf("episodeNum" to "1", "playWords" to playWordsEmpty)


            val category = listOf<String>("搞笑")
            val videosId = listOf<String>("6TnHF1cW1gs","JGPsOi0Rb4Y")

        // add new anime below

            // fire
            val animeInfoDocument = db.collection("animeInfo").document("IWoNOgF1WWR7rgX7IOuD") //document(IWoNOgF1WWR7rgX7IOuD)

            val wordsDataList = mutableListOf<Map<String, Any>>()

            wordsDataList.add(episode1Data)
            wordsDataList.add(episode2Data)

            val animeData = hashMapOf(
                "isCollected" to false,
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

        val monday1a1 = WeeklyAnime("01:00","書店裡的骷髏店員本田","第2集","IWoNOgF1WWR7rgX7IOuD")
        val monday1a2 = WeeklyAnime("01:05","黑暗集會","第1集","ParS5uxVFIekx3A2k1TE")
        val monday1a3 = WeeklyAnime("21:30","LV1 魔王與獨居廢勇者","第1集","R4pEoRSkc840H7swsDxZ")
        val mondayList = WeeklyAnimeList(listOf(monday1a1,monday1a2,monday1a3))

        val tuesday2a1 = WeeklyAnime("23:30","我喜歡的女孩忘記戴眼鏡","第1集","YJUR23GtxGMql64yUg56")
        val tuesdayList = WeeklyAnimeList(listOf(tuesday2a1))

        val wednesday3a1 = WeeklyAnime("01:29","勇者赫魯庫","第1集","W3NeoHNqZXUqgrBYCJXY")
        val wednesday3a2 = WeeklyAnime("22:00","雖然等級只有 1 級但固有技能是最強的","第1集","nuufstFX2ui4OhYn2Bbf")
        val wednesdayList = WeeklyAnimeList(listOf(wednesday3a1,wednesday3a2))


        val thursday4a1 = WeeklyAnime("01:25","聖者無雙～上班族的異世界生存之道～","第1集","jxmKxYr65kyx9GXVuhvq")
        val thursday4a2 = WeeklyAnime("01:30","七魔劍支配天下","第1集","Tdhg6zKQ8dow0Ea4DEoY")
        val thursday4a3 = WeeklyAnime("20:35","能幹貓今天也憂鬱","第1集","GPaNphqGKY8JZdUSDAv6")
        val thursdayList = WeeklyAnimeList(listOf(thursday4a1,thursday4a2,thursday4a3))

        val friday5a1 = WeeklyAnime("00:00","不死少女的謀殺鬧劇","第1集","Ybvn5MriCOcdaTiW8kqo")
        val fridayList = WeeklyAnimeList(listOf(friday5a1))

        val saturdayList = WeeklyAnimeList(listOf(monday1a1,monday1a2,monday1a3)) // use monday

        val sundayList = WeeklyAnimeList(listOf(wednesday3a1,wednesday3a2)) // use wednesday

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



    fun addUser() {
        // fire
        val userInfoDocument = db.collection("userInfo").document()

        val userData = hashMapOf(
            "userId" to userInfoDocument.id,
        )

        // [update same user document]
        userInfoDocument.set(userData)
            .addOnSuccessListener { documentReference ->
                Log.d("AddFirebase", "DocumentSnapshot written with ID: $documentReference")
            }
            .addOnFailureListener { e ->
                Log.w("AddFirebase", "Error adding document", e)
            }
    }

    fun setUserCollectedAnimeList() {
        // fire
        val userAnimeListDocument =
            db.collection("userInfo").document("Bstm28YuZ3ih78afvdq9")
                .collection("animeCollection").document("IWoNOgF1WWR7rgX7IOuD")
        Log.i("loginTest","${UserManager.user?.uid}")


        val userData = hashMapOf(
            "animeId" to "IWoNOgF1WWR7rgX7IOuD",
        )

        // [update same user document]
        userAnimeListDocument.set(userData)
            .addOnSuccessListener { documentReference ->
                Log.d("AddFirebase", "DocumentSnapshot written with ID: $documentReference")
            }
            .addOnFailureListener { e ->
                Log.w("AddFirebase", "Error adding document", e)
            }
    }




    // add 11 anime
    fun addOtherAnimeInfo() {

        val playWordsEmpty = listOf<Map<String,Any>>(mapOf("time" to "0:00:00.00","word" to "(尚未新增單字資源)","level" to "","isCollected" to false))

        val episode1Data = mapOf("episodeNum" to "1", "playWords" to playWordsEmpty)
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


        // change here
        val category = listOf<String>("搞笑")
        val videosId = listOf<String>("BBmz-B5kSO8","TFAjniv77gQ","9HAtA03GNOg","E--_bTfdCZc",
            "JH4Y0eiZNbg","5wHJ0S3hQrc","wuDrWYT8LZ8","uCKSZzCjPvI","ArmtLGi99jU","4_x_mAR6u10","xbYjl3YC7xs","MqG9Nu-dpd8")

        val animeInfoDocument = db.collection("animeInfo").document()


        val animeData = hashMapOf(
            "isCollected" to false,
            "animeId" to animeInfoDocument.id,
            "title" to "能幹貓今天也憂鬱",
            "releaseTime" to "2023/09/23",
            "episode" to "全12集",
            "rate" to "5.0",
            "wordsList" to wordsDataList,
            "category" to category,
            "videosId" to videosId,
            "pictureURL" to "https://p2.bahamut.com.tw/B/ACG/c/45/0000124645.JPG"
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



    fun addUserCollectedWordsList() {
        // fire
        val userCollectedWordsList =
            db.collection("userInfo").document("Bstm28YuZ3ih78afvdq9")
                .collection("wordsCollection").document("突然")
        Log.i("loginTest","${UserManager.user?.uid}")


        val collectedWords = mapOf(
            "word" to "突然",
            "level" to "N4",
            "isCollected" to true
        )

        userCollectedWordsList.set(collectedWords)
            .addOnSuccessListener { documentReference ->
                Log.d("AddFirebase", "DocumentSnapshot written with ID: $documentReference")
            }
            .addOnFailureListener { e ->
                Log.w("AddFirebase", "Error adding document", e)
            }
    }


    // add words info 10/2
    fun setAnimeInfo1_2() {

        // 0:00 ~ 05:00
        val playWords1 = mapOf("time" to "0:00:50.00","word" to "隠れる","level" to "N3","isCollected" to false)
        val playWords2 = mapOf("time" to "0:00:57.00","word" to "見つける","level" to "N4","isCollected" to false)
        val playWords3 = mapOf("time" to "0:02:48.00","word" to "すごい","level" to "N3","isCollected" to false)
        val playWords4 = mapOf("time" to "0:03:03.00","word" to "僕","level" to "N4","isCollected" to false)
        val playWords5 = mapOf("time" to "0:03:05.00","word" to "類","level" to "N1","isCollected" to false)
        val playWords6 = mapOf("time" to "0:03:06.00","word" to "嫌い","level" to "N5","isCollected" to false)
        val playWords7 = mapOf("time" to "0:03:14.00","word" to "やっぱり","level" to "N2","isCollected" to false)
        val playWords8 = mapOf("time" to "0:03:20.00","word" to "無理","level" to "N4","isCollected" to false)
        val playWords9 = mapOf("time" to "0:03:21.00","word" to "撮る","level" to "N5","isCollected" to false)
        val playWords10 = mapOf("time" to "0:03:28.00","word" to "パーセント","level" to "N3","isCollected" to false)
        val playWords11 = mapOf("time" to "0:03:36.00","word" to "中学","level" to "N3","isCollected" to false)
        val playWords12 = mapOf("time" to "0:03:38.00","word" to "受ける","level" to "N4","isCollected" to false)
        val playWords13 = mapOf("time" to "0:03:42.00","word" to "巻く","level" to "N2","isCollected" to false)
        val playWords14 = mapOf("time" to "0:03:43.00","word" to "以来","level" to "N3","isCollected" to false)
        val playWords15 = mapOf("time" to "0:03:45.00","word" to "大切","level" to "N5","isCollected" to false)
        val playWords16 = mapOf("time" to "0:03:46.00","word" to "付ける","level" to "N3","isCollected" to false)
        val playWords17 = mapOf("time" to "0:03:47.00","word" to "怖い","level" to "N4","isCollected" to false)
        val playWords18 = mapOf("time" to "0:03:50.00","word" to "けれど","level" to "N1","isCollected" to false)
        val playWords19 = mapOf("time" to "0:03:52.00","word" to "家族","level" to "N5","isCollected" to false)
        val playWords20 = mapOf("time" to "0:03:54.00","word" to "周囲","level" to "N3","isCollected" to false)


        // 05:00
        val playWords21 = mapOf("time" to "0:05:00.00","word" to "抜く","level" to "N3","isCollected" to false)

        // 07:00
        val playWords22 = mapOf("time" to "0:07:08.00","word" to "担当","level" to "N3","isCollected" to false)

        // 09:00
        val playWords23 = mapOf("time" to "0:09:06.00","word" to "電話","level" to "N5","isCollected" to false)

        // 11:00
        val playWords24 = mapOf("time" to "0:11:01.00","word" to "分かる","level" to "N5","isCollected" to false)

        // 13:00
        val playWords25 = mapOf("time" to "0:12:59.00","word" to "入る","level" to "N5","isCollected" to false)

        // 15:00
        val playWords26 = mapOf("time" to "0:15:04.00","word" to "真似","level" to "N3","isCollected" to false)

        // 17:00
        val playWords27 = mapOf("time" to "0:17:02.00","word" to "できる","level" to "N5","isCollected" to false)

        // 19:00
        val playWords28 = mapOf("time" to "0:19:03.00","word" to "効果","level" to "N3","isCollected" to false)

        // 21:00
        val playWords29 = mapOf("time" to "0:21:02.00","word" to "改める","level" to "N2","isCollected" to false)

        // 23:00 後隨意
        val playWords30 = mapOf("time" to "0:22:41.00","word" to "壷","level" to "N1","isCollected" to false)




        val episode01Data = mapOf(
            "episodeNum" to "1",
            "playWords" to listOf(
                playWords1, playWords2, playWords3, playWords4, playWords5, playWords6, playWords7, playWords8, playWords9, playWords10,
                playWords11, playWords12, playWords13, playWords14, playWords15, playWords16, playWords17, playWords18, playWords19, playWords20,
                playWords21, playWords22, playWords23, playWords24, playWords25, playWords26, playWords27, playWords28, playWords29, playWords30
            )
        )


        val category = listOf<String>("有趣")
        val videosId = listOf<String>("1zM_1f4L80M")

        // fire
        val animeInfoDocument = db.collection("animeInfo").document("ParS5uxVFIekx3A2k1TE")

        val wordsDataList = mutableListOf<Map<String, Any>>()

        wordsDataList.add(episode01Data)

        val animeData = hashMapOf(
            "isCollected" to false,
            "animeId" to animeInfoDocument.id,
            "title" to "黑暗集會",
            "releaseTime" to "2023/09/25",
            "episode" to "第1集",
            "rate" to "4.9",
            "wordsList" to wordsDataList,
            "category" to category,
            "videosId" to videosId,
            "pictureURL" to "https://p2.bahamut.com.tw/B/ACG/c/37/0000128537.JPG"
        )

        animeInfoDocument.set(animeData)
            .addOnSuccessListener { documentReference ->
                Log.d("AddFirebase", "DocumentSnapshot written with ID: $documentReference")
            }
            .addOnFailureListener { e ->
                Log.w("AddFirebase", "Error adding document", e)
            }
    }


    fun setAnimeInfo1_3() {

        // 0:00 ~ 05:00
        val playWords1 = mapOf("time" to "0:00:27.00","word" to "敵","level" to "N3","isCollected" to false)

        val playWords2 = mapOf("time" to "0:00:29.00","word" to "見事","level" to "N3","isCollected" to false)

        val playWords3 = mapOf("time" to "0:00:32.00","word" to "しかし","level" to "N5","isCollected" to false)

        val playWords4 = mapOf("time" to "0:00:35.00","word" to "相手","level" to "N3","isCollected" to false)

        val playWords5 = mapOf("time" to "0:00:36.00","word" to "人間","level" to "N3","isCollected" to false)

        val playWords6 = mapOf("time" to "0:00:39.00","word" to "支配","level" to "N3","isCollected" to false)

        val playWords7 = mapOf("time" to "0:00:41.00","word" to "俺","level" to "N1","isCollected" to false)

        val playWords8 = mapOf("time" to "0:00:43.00","word" to "諦める","level" to "N3","isCollected" to false)

        val playWords9 = mapOf("time" to "0:01:10.00","word" to "最後","level" to "N4","isCollected" to false)

        val playWords10 = mapOf("time" to "0:01:11.00","word" to "手","level" to "N5","isCollected" to false)

        val playWords11 = mapOf("time" to "0:01:12.00","word" to "刺す","level" to "N2","isCollected" to false)

        val playWords12 = mapOf("time" to "0:01:26.00","word" to "腹","level" to "N3","isCollected" to false)

        val playWords13 = mapOf("time" to "0:01:33.00","word" to "持つ","level" to "N5","isCollected" to false)

        val playWords14 = mapOf("time" to "0:02:15.00","word" to "打つ","level" to "N4","isCollected" to false)

        val playWords15 = mapOf("time" to "0:02:21.00","word" to "生きる","level" to "N4","isCollected" to false)

        val playWords16 = mapOf("time" to "0:02:23.00","word" to "必ず","level" to "N4","isCollected" to false)

        val playWords17 = mapOf("time" to "0:02:25.00","word" to "血","level" to "N4","isCollected" to false)

        val playWords18 = mapOf("time" to "0:02:26.00","word" to "味わう","level" to "N2","isCollected" to false)

        val playWords19 = mapOf("time" to "0:02:29.00","word" to "楽しみ","level" to "N4","isCollected" to false)

        val playWords20 = mapOf("time" to "0:02:38.00","word" to "復活","level" to "N1","isCollected" to false)



        // 05:00
        val playWords21 = mapOf("time" to "0:05:07.00","word" to "姿","level" to "N3","isCollected" to false)

        // 07:00
        val playWords22 = mapOf("time" to "0:07:05.00","word" to "恐怖","level" to "N3","isCollected" to false)

        // 09:00
        val playWords23 = mapOf("time" to "0:10:17.00","word" to "冗談","level" to "N3","isCollected" to false)

        // 11:00
        val playWords24 = mapOf("time" to "0:10:59.00","word" to "共","level" to "N1","isCollected" to false)

        // 13:00
        val playWords25 = mapOf("time" to "0:13:00.00","word" to "一体","level" to "N3","isCollected" to false)

        // 15:00
        val playWords26 = mapOf("time" to "0:14:50.00","word" to "否定","level" to "N3","isCollected" to false)

        // 17:00
        val playWords27 = mapOf("time" to "0:16:49.00","word" to "仲間","level" to "N3","isCollected" to false)

        // 19:00
        val playWords28 = mapOf("time" to "0:18:51.00","word" to "意味","level" to "N5","isCollected" to false)

        // 21:00
        val playWords29 = mapOf("time" to "0:20:54.00","word" to "風呂","level" to "N3","isCollected" to false)

        // 23:00 後隨意
        val playWords30 = mapOf("time" to "0:22:10.00","word" to "別に","level" to "N3","isCollected" to false)




        val episode01Data = mapOf(
            "episodeNum" to "1",
            "playWords" to listOf(
                playWords1, playWords2, playWords3, playWords4, playWords5, playWords6, playWords7, playWords8, playWords9, playWords10,
                playWords11, playWords12, playWords13, playWords14, playWords15, playWords16, playWords17, playWords18, playWords19, playWords20,
                playWords21, playWords22, playWords23, playWords24, playWords25, playWords26, playWords27, playWords28, playWords29, playWords30
            )
        )


        val category = listOf<String>("奇幻","搞笑")
        val videosId = listOf<String>("M34_h8WxmzU")

        // fire
        val animeInfoDocument = db.collection("animeInfo").document("R4pEoRSkc840H7swsDxZ")

        val wordsDataList = mutableListOf<Map<String, Any>>()

        wordsDataList.add(episode01Data)

        val animeData = hashMapOf(
            "isCollected" to false,
            "animeId" to animeInfoDocument.id,
            "title" to "LV1魔王與獨居廢勇者",
            "releaseTime" to "2023/09/18",
            "episode" to "第1集",
            "rate" to "4.9",
            "wordsList" to wordsDataList,
            "category" to category,
            "videosId" to videosId,
            "pictureURL" to "https://p2.bahamut.com.tw/B/ACG/c/31/0000123631.JPG"
        )

        animeInfoDocument.set(animeData)
            .addOnSuccessListener { documentReference ->
                Log.d("AddFirebase", "DocumentSnapshot written with ID: $documentReference")
            }
            .addOnFailureListener { e ->
                Log.w("AddFirebase", "Error adding document", e)
            }
    }



    fun setAnimeInfo2_1() {

        // 0:00 ~ 05:00
        val playWords1 = mapOf("time" to "0:00:06.00","word" to "動物","level" to "N5","isCollected" to false)

        val playWords2 = mapOf("time" to "0:00:08.00","word" to "見る","level" to "N3","isCollected" to false)

        val playWords3 = mapOf("time" to "0:00:16.00","word" to "きつい","level" to "N3","isCollected" to false)

        val playWords4 = mapOf("time" to "0:00:19.00","word" to "おはよう","level" to "N2","isCollected" to false)

        val playWords5 = mapOf("time" to "0:00:27.00","word" to "買う","level" to "N5","isCollected" to false)

        val playWords6 = mapOf("time" to "0:00:28.00","word" to "限定","level" to "N1","isCollected" to false)

        val playWords7 = mapOf("time" to "0:00:30.00","word" to "ほど","level" to "N4","isCollected" to false)

        val playWords8 = mapOf("time" to "0:00:31.00","word" to "着く","level" to "N5","isCollected" to false)

        val playWords9 = mapOf("time" to "0:00:35.00","word" to "俺","level" to "N1","isCollected" to false)

        val playWords10 = mapOf("time" to "0:00:49.00","word" to "いつも","level" to "N5","isCollected" to false)

        val playWords11 = mapOf("time" to "0:00:50.00","word" to "こと","level" to "N4","isCollected" to false)

        val playWords12 = mapOf("time" to "0:01:00.00","word" to "頭","level" to "N5","isCollected" to false)

        val playWords13 = mapOf("time" to "0:01:10.00","word" to "想像","level" to "N3","isCollected" to false)

        val playWords14 = mapOf("time" to "0:02:32.00","word" to "忘れ物","level" to "N4","isCollected" to false)

        val playWords15 = mapOf("time" to "0:02:38.00","word" to "今日","level" to "N5","isCollected" to false)

        val playWords16 = mapOf("time" to "0:02:43.00","word" to "分かる","level" to "N5","isCollected" to false)

        val playWords17 = mapOf("time" to "0:02:50.00","word" to "突っ込む","level" to "N2","isCollected" to false)

        val playWords18 = mapOf("time" to "0:02:53.00","word" to "タイミング","level" to "N1","isCollected" to false)

        val playWords19 = mapOf("time" to "0:02:57.00","word" to "かわいい","level" to "N5","isCollected" to false)

        val playWords20 = mapOf("time" to "0:03:04.00","word" to "好き","level" to "N5","isCollected" to false)



        // 05:00
        val playWords21 = mapOf("time" to "0:05:01.00","word" to "遊ぶ","level" to "N5","isCollected" to false)

        // 07:00
        val playWords22 = mapOf("time" to "0:07:00.00","word" to "使う","level" to "N5","isCollected" to false)

        // 09:00
        val playWords23 = mapOf("time" to "0:09:02.00","word" to "記憶","level" to "N3","isCollected" to false)

        // 11:00
        val playWords24 = mapOf("time" to "0:11:01.00","word" to "思い出す","level" to "N4","isCollected" to false)

        // 13:00
        val playWords25 = mapOf("time" to "0:13:00.00","word" to "平気","level" to "N2","isCollected" to false)

        // 15:00
        val playWords26 = mapOf("time" to "0:14:57.00","word" to "一緒","level" to "N5","isCollected" to false)

        // 17:00
        val playWords27 = mapOf("time" to "0:16:56.00","word" to "忘れる","level" to "N5","isCollected" to false)

        // 19:00
        val playWords28 = mapOf("time" to "0:18:57.00","word" to "集中","level" to "N3","isCollected" to false)

        // 21:00
        val playWords29 = mapOf("time" to "0:21:00.00","word" to "間違える","level" to "N4","isCollected" to false)

        // 23:00 後隨意
        val playWords30 = mapOf("time" to "0:22:58.00","word" to "慣れる","level" to "N4","isCollected" to false)




        val episode01Data = mapOf(
            "episodeNum" to "1",
            "playWords" to listOf(
                playWords1, playWords2, playWords3, playWords4, playWords5, playWords6, playWords7, playWords8, playWords9, playWords10,
                playWords11, playWords12, playWords13, playWords14, playWords15, playWords16, playWords17, playWords18, playWords19, playWords20,
                playWords21, playWords22, playWords23, playWords24, playWords25, playWords26, playWords27, playWords28, playWords29, playWords30
            )
        )


        val category = listOf<String>("校園","戀愛")      // 改
        val videosId = listOf<String>("kjBb7KTrVYc")    // 改

        // fire
        val animeInfoDocument = db.collection("animeInfo").document("YJUR23GtxGMql64yUg56")    // 改

        val wordsDataList = mutableListOf<Map<String, Any>>()

        wordsDataList.add(episode01Data)

        val animeData = hashMapOf(    // 改
            "isCollected" to false,
            "animeId" to animeInfoDocument.id,
            "title" to "我喜歡的女孩忘記戴眼鏡",
            "releaseTime" to "2023/09/19",
            "episode" to "第1集",
            "rate" to "4.6",
            "wordsList" to wordsDataList,
            "category" to category,
            "videosId" to videosId,
            "pictureURL" to "https://p2.bahamut.com.tw/B/ACG/c/53/0000129653.JPG"
        )

        animeInfoDocument.set(animeData)
            .addOnSuccessListener { documentReference ->
                Log.d("AddFirebase", "DocumentSnapshot written with ID: $documentReference")
            }
            .addOnFailureListener { e ->
                Log.w("AddFirebase", "Error adding document", e)
            }
    }


    fun setAnimeInfo3_1() {

        // 0:00 ~ 05:00
        val playWords1 = mapOf("time" to "0:00:57.00","word" to "妙","level" to "N3","isCollected" to false)

        val playWords2 = mapOf("time" to "0:01:00.00","word" to "底","level" to "N3","isCollected" to false)

        val playWords3 = mapOf("time" to "0:01:02.00","word" to "感じる","level" to "N3","isCollected" to false)

        val playWords4 = mapOf("time" to "0:01:05.00","word" to "彼","level" to "N4","isCollected" to false)

        val playWords5 = mapOf("time" to "0:01:06.00","word" to "優勝","level" to "N3","isCollected" to false)

        val playWords6 = mapOf("time" to "0:01:07.00","word" to "名","level" to "N3","isCollected" to false)

        val playWords7 = mapOf("time" to "0:01:10.00","word" to "優秀","level" to "N3","isCollected" to false)

        val playWords8 = mapOf("time" to "0:01:11.00","word" to "成績","level" to "N3","isCollected" to false)

        val playWords9 = mapOf("time" to "0:01:12.00","word" to "通過","level" to "N3","isCollected" to false)

        val playWords10 = mapOf("time" to "0:01:13.00","word" to "人間","level" to "N3","isCollected" to false)

        val playWords11 = mapOf("time" to "0:03:07.00","word" to "国","level" to "N5","isCollected" to false)

        val playWords12 = mapOf("time" to "0:03:09.00","word" to "一人","level" to "N5","isCollected" to false)

        val playWords13 = mapOf("time" to "0:03:11.00","word" to "倒す","level" to "N3","isCollected" to false)

        val playWords14 = mapOf("time" to "0:03:15.00","word" to "軍","level" to "N3","isCollected" to false)

        val playWords15 = mapOf("time" to "0:03:16.00","word" to "攻める","level" to "N2","isCollected" to false)

        val playWords16 = mapOf("time" to "0:03:17.00","word" to "城","level" to "N3","isCollected" to false)

        val playWords17 = mapOf("time" to "0:03:19.00","word" to "戦い","level" to "N3","isCollected" to false)

        val playWords18 = mapOf("time" to "0:03:20.00","word" to "勝利","level" to "N1","isCollected" to false)

        val playWords19 = mapOf("time" to "0:03:23.00","word" to "怯える","level" to "N1","isCollected" to false)

        val playWords20 = mapOf("time" to "0:03:26.00","word" to "渦","level" to "N1","isCollected" to false)



        // 05:00
        val playWords21 = mapOf("time" to "0:05:23.00","word" to "勝ち","level" to "N3","isCollected" to false)

        // 07:00
        val playWords22 = mapOf("time" to "0:07:01.00","word" to "決まる","level" to "N1","isCollected" to false)

        // 09:00
        val playWords23 = mapOf("time" to "0:09:03.00","word" to "処罰","level" to "N1","isCollected" to false)

        // 11:00
        val playWords24 = mapOf("time" to "0:11:02.00","word" to "いつも","level" to "N5","isCollected" to false)

        // 13:00
        val playWords25 = mapOf("time" to "0:13:02.00","word" to "タワー","level" to "N1","isCollected" to false)

        // 15:00
        val playWords26 = mapOf("time" to "0:15:01.00","word" to "冷静","level" to "N3","isCollected" to false)

        // 17:00
        val playWords27 = mapOf("time" to "0:17:00.00","word" to "審査","level" to "N1","isCollected" to false)

        // 19:00
        val playWords28 = mapOf("time" to "0:19:02.00","word" to "誕生","level" to "N3","isCollected" to false)

        // 21:00
        val playWords29 = mapOf("time" to "0:21:02.00","word" to "契約","level" to "N3","isCollected" to false)

        // 23:00 後隨意
        val playWords30 = mapOf("time" to "0:21:10.00","word" to "人間","level" to "N3","isCollected" to false)




        val episode01Data = mapOf(
            "episodeNum" to "1",
            "playWords" to listOf(
                playWords1, playWords2, playWords3, playWords4, playWords5, playWords6, playWords7, playWords8, playWords9, playWords10,
                playWords11, playWords12, playWords13, playWords14, playWords15, playWords16, playWords17, playWords18, playWords19, playWords20,
                playWords21, playWords22, playWords23, playWords24, playWords25, playWords26, playWords27, playWords28, playWords29, playWords30
            )
        )


        val category = listOf<String>("奇幻","冒險")      // 改
        val videosId = listOf<String>("bAABHgjmbLg")    // 改

        // fire
        val animeInfoDocument = db.collection("animeInfo").document("W3NeoHNqZXUqgrBYCJXY")    // 改

        val wordsDataList = mutableListOf<Map<String, Any>>()

        wordsDataList.add(episode01Data)

        val animeData = hashMapOf(    // 改
            "isCollected" to false,
            "animeId" to animeInfoDocument.id,
            "title" to "勇者赫魯庫",
            "releaseTime" to "2023/09/20",
            "episode" to "第1集",
            "rate" to "4.9",
            "wordsList" to wordsDataList,
            "category" to category,
            "videosId" to videosId,
            "pictureURL" to "https://p2.bahamut.com.tw/B/ACG/c/09/0000130209.JPG"
        )

        animeInfoDocument.set(animeData)
            .addOnSuccessListener { documentReference ->
                Log.d("AddFirebase", "DocumentSnapshot written with ID: $documentReference")
            }
            .addOnFailureListener { e ->
                Log.w("AddFirebase", "Error adding document", e)
            }
    }


    fun setAnimeInfo3_2() {

        // 0:00 ~ 05:00
        val playWords1 = mapOf("time" to "0:00:08.00","word" to "音","level" to "N4","isCollected" to false)

        val playWords2 = mapOf("time" to "0:00:10.00","word" to "ゲーム","level" to "N3","isCollected" to false)

        val playWords3 = mapOf("time" to "0:00:15.00","word" to "起きる","level" to "N5","isCollected" to false)

        val playWords4 = mapOf("time" to "0:00:16.00","word" to "人間","level" to "N3","isCollected" to false)

        val playWords5 = mapOf("time" to "0:00:23.00","word" to "型","level" to "N3","isCollected" to false)

        val playWords6 = mapOf("time" to "0:00:26.00","word" to "触る","level" to "N4","isCollected" to false)

        val playWords7 = mapOf("time" to "0:00:28.00","word" to "待つ","level" to "N5","isCollected" to false)

        val playWords8 = mapOf("time" to "0:00:32.00","word" to "びっくり","level" to "N3","isCollected" to false)

        val playWords9 = mapOf("time" to "0:00:34.00","word" to "本当","level" to "N3","isCollected" to false)

        val playWords10 = mapOf("time" to "0:00:36.00","word" to "どこ","level" to "N5","isCollected" to false)

        val playWords11 = mapOf("time" to "0:00:40.00","word" to "どうして","level" to "N5","isCollected" to false)

        val playWords12 = mapOf("time" to "0:00:45.00","word" to "おかしい","level" to "N4","isCollected" to false)

        val playWords13 = mapOf("time" to "0:00:49.00","word" to "はず","level" to "N4","isCollected" to false)

        val playWords14 = mapOf("time" to "0:00:52.00","word" to "頭","level" to "N5","isCollected" to false)

        val playWords15 = mapOf("time" to "0:00:53.00","word" to "大丈夫","level" to "N5","isCollected" to false)

        val playWords16 = mapOf("time" to "0:00:55.00","word" to "知る","level" to "N5","isCollected" to false)

        val playWords17 = mapOf("time" to "0:01:06.00","word" to "危ない","level" to "N5","isCollected" to false)

        val playWords18 = mapOf("time" to "0:01:20.00","word" to "地下","level" to "N3","isCollected" to false)

        val playWords19 = mapOf("time" to "0:01:23.00","word" to "常識","level" to "N3","isCollected" to false)

        val playWords20 = mapOf("time" to "0:01:31.00","word" to "言う","level" to "N5","isCollected" to false)



        // 05:00
        val playWords21 = mapOf("time" to "0:05:00.00","word" to "レベル","level" to "N2","isCollected" to false)

        // 07:00
        val playWords22 = mapOf("time" to "0:07:13.00","word" to "どうぞ","level" to "N5","isCollected" to false)

        // 09:00
        val playWords23 = mapOf("time" to "0:08:58.00","word" to "しかも","level" to "N3","isCollected" to false)

        // 11:00
        val playWords24 = mapOf("time" to "0:11:07.00","word" to "時","level" to "N3","isCollected" to false)

        // 13:00
        val playWords25 = mapOf("time" to "0:13:01.00","word" to "飲む","level" to "N5","isCollected" to false)

        // 15:00
        val playWords26 = mapOf("time" to "0:15:03.00","word" to "角","level" to "N5","isCollected" to false)

        // 17:00
        val playWords27 = mapOf("time" to "0:16:59.00","word" to "放置","level" to "N1","isCollected" to false)

        // 19:00
        val playWords28 = mapOf("time" to "0:19:01.00","word" to "うまい","level" to "N4","isCollected" to false)

        // 21:00
        val playWords29 = mapOf("time" to "0:21:03.00","word" to "耳","level" to "N5","isCollected" to false)

        // 23:00 後隨意
        val playWords30 = mapOf("time" to "0:21:56.00","word" to "嫌い","level" to "N5","isCollected" to false)




        val episode01Data = mapOf(
            "episodeNum" to "1",
            "playWords" to listOf(
                playWords1, playWords2, playWords3, playWords4, playWords5, playWords6, playWords7, playWords8, playWords9, playWords10,
                playWords11, playWords12, playWords13, playWords14, playWords15, playWords16, playWords17, playWords18, playWords19, playWords20,
                playWords21, playWords22, playWords23, playWords24, playWords25, playWords26, playWords27, playWords28, playWords29, playWords30
            )
        )


        val category = listOf<String>("奇幻","冒險")      // 改
        val videosId = listOf<String>("ZXikkdvIZIs")    // 改

        // fire
        val animeInfoDocument = db.collection("animeInfo").document("nuufstFX2ui4OhYn2Bbf")    // 改

        val wordsDataList = mutableListOf<Map<String, Any>>()

        wordsDataList.add(episode01Data)

        val animeData = hashMapOf(    // 改
            "isCollected" to false,
            "animeId" to animeInfoDocument.id,
            "title" to "雖然等級只有1級但固有技能是最強的",
            "releaseTime" to "2023/09/19",
            "episode" to "第1集",
            "rate" to "4.2",
            "wordsList" to wordsDataList,
            "category" to category,
            "videosId" to videosId,
            "pictureURL" to "https://p2.bahamut.com.tw/B/ACG/c/20/0000127820.JPG"
        )

        animeInfoDocument.set(animeData)
            .addOnSuccessListener { documentReference ->
                Log.d("AddFirebase", "DocumentSnapshot written with ID: $documentReference")
            }
            .addOnFailureListener { e ->
                Log.w("AddFirebase", "Error adding document", e)
            }
    }


    fun setAnimeInfo4_1() {

        // 0:00 ~ 05:00
        val playWords1 = mapOf("time" to "0:00:02.00","word" to "部長","level" to "N4","isCollected" to false)

        val playWords2 = mapOf("time" to "0:00:04.00","word" to "取る","level" to "N5","isCollected" to false)

        val playWords3 = mapOf("time" to "0:00:05.00","word" to "営業","level" to "N3","isCollected" to false)

        val playWords4 = mapOf("time" to "0:00:06.00","word" to "向かう","level" to "N4","isCollected" to false)

        val playWords5 = mapOf("time" to "0:00:09.00","word" to "だめ","level" to "N4","isCollected" to false)

        val playWords6 = mapOf("time" to "0:00:12.00","word" to "思う","level" to "N4","isCollected" to false)

        val playWords7 = mapOf("time" to "0:00:15.00","word" to "一つ","level" to "N5","isCollected" to false)

        val playWords8 = mapOf("time" to "0:00:17.00","word" to "次","level" to "N5","isCollected" to false)

        val playWords9 = mapOf("time" to "0:00:20.00","word" to "日々","level" to "N1","isCollected" to false)

        val playWords10 = mapOf("time" to "0:00:21.00","word" to "鍛える","level" to "N1","isCollected" to false)

        val playWords11 = mapOf("time" to "0:00:24.00","word" to "目標","level" to "N3","isCollected" to false)

        val playWords12 = mapOf("time" to "0:00:25.00","word" to "歩く","level" to "N5","isCollected" to false)

        val playWords13 = mapOf("time" to "0:00:28.00","word" to "回る","level" to "N4","isCollected" to false)

        val playWords14 = mapOf("time" to "0:00:40.00","word" to "達成","level" to "N1","isCollected" to false)

        val playWords15 = mapOf("time" to "0:00:42.00","word" to "昇進","level" to "N1","isCollected" to false)

        val playWords16 = mapOf("time" to "0:00:55.00","word" to "待つ","level" to "N5","isCollected" to false)

        val playWords17 = mapOf("time" to "0:00:57.00","word" to "戻る","level" to "N4","isCollected" to false)

        val playWords18 = mapOf("time" to "0:00:59.00","word" to "祝う","level" to "N3","isCollected" to false)

        val playWords19 = mapOf("time" to "0:01:02.00","word" to "おとなしい","level" to "N2","isCollected" to false)

        val playWords20 = mapOf("time" to "0:01:11.00","word" to "痛い","level" to "N5","isCollected" to false)



        // 05:00
        val playWords21 = mapOf("time" to "0:04:42.00","word" to "幸福","level" to "N3","isCollected" to false)

        // 07:00
        val playWords22 = mapOf("time" to "0:07:00.00","word" to "知識","level" to "N3","isCollected" to false)

        // 09:00
        val playWords23 = mapOf("time" to "0:09:03.00","word" to "生死","level" to "N1","isCollected" to false)

        // 11:00
        val playWords24 = mapOf("time" to "0:11:03.00","word" to "街","level" to "N3","isCollected" to false)

        // 13:00
        val playWords25 = mapOf("time" to "0:13:03.00","word" to "信じる","level" to "N3","isCollected" to false)

        // 15:00
        val playWords26 = mapOf("time" to "0:15:01.00","word" to "期待","level" to "N3","isCollected" to false)

        // 17:00
        val playWords27 = mapOf("time" to "0:17:02.00","word" to "減る","level" to "N3","isCollected" to false)

        // 19:00
        val playWords28 = mapOf("time" to "0:19:05.00","word" to "おめでとう","level" to "N3","isCollected" to false)

        // 21:00
        val playWords29 = mapOf("time" to "0:21:01.00","word" to "実際","level" to "N3","isCollected" to false)

        // 23:00 後隨意
        val playWords30 = mapOf("time" to "0:24:00.00","word" to "救う","level" to "N3","isCollected" to false)




        val episode01Data = mapOf(
            "episodeNum" to "1",
            "playWords" to listOf(
                playWords1, playWords2, playWords3, playWords4, playWords5, playWords6, playWords7, playWords8, playWords9, playWords10,
                playWords11, playWords12, playWords13, playWords14, playWords15, playWords16, playWords17, playWords18, playWords19, playWords20,
                playWords21, playWords22, playWords23, playWords24, playWords25, playWords26, playWords27, playWords28, playWords29, playWords30
            )
        )


        val category = listOf<String>("奇幻","冒險")      // 改
        val videosId = listOf<String>("hiYjfQaTUsY")    // 改

        // fire
        val animeInfoDocument = db.collection("animeInfo").document("jxmKxYr65kyx9GXVuhvq")    // 改

        val wordsDataList = mutableListOf<Map<String, Any>>()

        wordsDataList.add(episode01Data)

        val animeData = hashMapOf(    // 改
            "isCollected" to false,
            "animeId" to animeInfoDocument.id,
            "title" to "聖者無雙～上班族的異世界生存之道～",
            "releaseTime" to "2023/09/19",
            "episode" to "第1集",
            "rate" to "4.2",
            "wordsList" to wordsDataList,
            "category" to category,
            "videosId" to videosId,
            "pictureURL" to "https://p2.bahamut.com.tw/B/ACG/c/50/0000127850.JPG"
        )

        animeInfoDocument.set(animeData)
            .addOnSuccessListener { documentReference ->
                Log.d("AddFirebase", "DocumentSnapshot written with ID: $documentReference")
            }
            .addOnFailureListener { e ->
                Log.w("AddFirebase", "Error adding document", e)
            }
    }


    fun setAnimeInfo4_2() {

        // 0:00 ~ 05:00
        val playWords1 = mapOf("time" to "0:00:02.00","word" to "学校","level" to "N5","isCollected" to false)

        val playWords2 = mapOf("time" to "0:00:04.00","word" to "卒業","level" to "N4","isCollected" to false)

        val playWords3 = mapOf("time" to "0:00:06.00","word" to "割","level" to "N1","isCollected" to false)

        val playWords4 = mapOf("time" to "0:00:20.00","word" to "咲く","level" to "N5","isCollected" to false)

        val playWords5 = mapOf("time" to "0:00:21.00","word" to "見事","level" to "N3","isCollected" to false)

        val playWords6 = mapOf("time" to "0:00:27.00","word" to "緊張","level" to "N3","isCollected" to false)

        val playWords7 = mapOf("time" to "0:00:30.00","word" to "見える","level" to "N4","isCollected" to false)

        val playWords8 = mapOf("time" to "0:00:32.00","word" to "肩","level" to "N3","isCollected" to false)

        val playWords9 = mapOf("time" to "0:00:33.00","word" to "抜く","level" to "N3","isCollected" to false)

        val playWords10 = mapOf("time" to "0:00:34.00","word" to "入学","level" to "N4","isCollected" to false)

        val playWords11 = mapOf("time" to "0:00:37.00","word" to "恐ろしい","level" to "N3","isCollected" to false)

        val playWords12 = mapOf("time" to "0:00:38.00","word" to "待つ","level" to "N5","isCollected" to false)

        val playWords13 = mapOf("time" to "0:00:41.00","word" to "恐縮","level" to "N2","isCollected" to false)

        val playWords14 = mapOf("time" to "0:00:46.00","word" to "未来","level" to "N3","isCollected" to false)

        val playWords15 = mapOf("time" to "0:00:48.00","word" to "新入生","level" to "N1","isCollected" to false)

        val playWords16 = mapOf("time" to "0:00:50.00","word" to "脅す","level" to "N1","isCollected" to false)

        val playWords17 = mapOf("time" to "0:00:53.00","word" to "言う","level" to "N5","isCollected" to false)

        val playWords18 = mapOf("time" to "0:00:56.00","word" to "何しろ","level" to "N1","isCollected" to false)

        val playWords19 = mapOf("time" to "0:01:02.00","word" to "怖い","level" to "N4","isCollected" to false)

        val playWords20 = mapOf("time" to "0:01:14.00","word" to "植物","level" to "N3","isCollected" to false)



        // 05:00
        val playWords21 = mapOf("time" to "0:05:05.00","word" to "余興","level" to "N1","isCollected" to false)

        // 07:00
        val playWords22 = mapOf("time" to "0:07:05.00","word" to "起こる","level" to "N3","isCollected" to false)

        // 09:00
        val playWords23 = mapOf("time" to "0:09:02.00","word" to "刀","level" to "N3","isCollected" to false)

        // 11:00
        val playWords24 = mapOf("time" to "0:11:08.00","word" to "学ぶ","level" to "N3","isCollected" to false)

        // 13:00
        val playWords25 = mapOf("time" to "0:13:09.00","word" to "引っ張る","level" to "N3","isCollected" to false)

        // 15:00
        val playWords26 = mapOf("time" to "0:15:03.00","word" to "送る","level" to "N4","isCollected" to false)

        // 17:00
        val playWords27 = mapOf("time" to "0:17:00.00","word" to "名字","level" to "N2","isCollected" to false)

        // 19:00
        val playWords28 = mapOf("time" to "0:18:54.00","word" to "うまい","level" to "N4","isCollected" to false)

        // 21:00
        val playWords29 = mapOf("time" to "0:21:10.00","word" to "朝","level" to "N5","isCollected" to false)

        // 23:00 後隨意
        val playWords30 = mapOf("time" to "0:22:54.00","word" to "におい","level" to "N4","isCollected" to false)




        val episode01Data = mapOf(
            "episodeNum" to "1",
            "playWords" to listOf(
                playWords1, playWords2, playWords3, playWords4, playWords5, playWords6, playWords7, playWords8, playWords9, playWords10,
                playWords11, playWords12, playWords13, playWords14, playWords15, playWords16, playWords17, playWords18, playWords19, playWords20,
                playWords21, playWords22, playWords23, playWords24, playWords25, playWords26, playWords27, playWords28, playWords29, playWords30
            )
        )


        val category = listOf<String>("奇幻","冒險","校園")      // 改
        val videosId = listOf<String>("MJrPgYwWMKs")    // 改

        // fire
        val animeInfoDocument = db.collection("animeInfo").document("Tdhg6zKQ8dow0Ea4DEoY")    // 改

        val wordsDataList = mutableListOf<Map<String, Any>>()

        wordsDataList.add(episode01Data)

        val animeData = hashMapOf(    // 改
            "isCollected" to false,
            "animeId" to animeInfoDocument.id,
            "title" to "七魔劍支配天下",
            "releaseTime" to "2023/09/23",
            "episode" to "第1集",
            "rate" to "4.5",
            "wordsList" to wordsDataList,
            "category" to category,
            "videosId" to videosId,
            "pictureURL" to "https://p2.bahamut.com.tw/B/ACG/c/23/0000121023.JPG"
        )

        animeInfoDocument.set(animeData)
            .addOnSuccessListener { documentReference ->
                Log.d("AddFirebase", "DocumentSnapshot written with ID: $documentReference")
            }
            .addOnFailureListener { e ->
                Log.w("AddFirebase", "Error adding document", e)
            }
    }

    fun setAnimeInfo4_3() {

        // 0:00 ~ 05:00
        val playWords1 = mapOf("time" to "0:01:12.00","word" to "痛い","level" to "N5","isCollected" to false)

        val playWords2 = mapOf("time" to "0:01:33.00","word" to "気を付ける","level" to "N2","isCollected" to false)

        val playWords3 = mapOf("time" to "0:01:34.00","word" to "おはよう","level" to "N2","isCollected" to false)

        val playWords4 = mapOf("time" to "0:01:42.00","word" to "電車","level" to "N5","isCollected" to false)

        val playWords5 = mapOf("time" to "0:01:45.00","word" to "黄色い","level" to "N5","isCollected" to false)

        val playWords6 = mapOf("time" to "0:01:48.00","word" to "下がる","level" to "N1","isCollected" to false)

        val playWords7 = mapOf("time" to "0:01:51.00","word" to "すみません","level" to "N3","isCollected" to false)

        val playWords8 = mapOf("time" to "0:01:52.00","word" to "乗車","level" to "N2","isCollected" to false)

        val playWords9 = mapOf("time" to "0:01:56.00","word" to "待つ","level" to "N5","isCollected" to false)

        val playWords10 = mapOf("time" to "0:02:06.00","word" to "駅","level" to "N5","isCollected" to false)

        val playWords11 = mapOf("time" to "0:02:07.00","word" to "利用","level" to "N4","isCollected" to false)

        val playWords12 = mapOf("time" to "0:02:09.00","word" to "誠","level" to "N1","isCollected" to false)

        val playWords13 = mapOf("time" to "0:02:35.00","word" to "打ち合わせ","level" to "N1","isCollected" to false)

        val playWords14 = mapOf("time" to "0:02:36.00","word" to "資料","level" to "N2","isCollected" to false)

        val playWords15 = mapOf("time" to "0:02:37.00","word" to "準備","level" to "N4","isCollected" to false)

        val playWords16 = mapOf("time" to "0:02:40.00","word" to "出張","level" to "N2","isCollected" to false)

        val playWords17 = mapOf("time" to "0:02:41.00","word" to "必要","level" to "N4","isCollected" to false)

        val playWords18 = mapOf("time" to "0:02:53.00","word" to "降りる","level" to "N5","isCollected" to false)

        val playWords19 = mapOf("time" to "0:04:18.00","word" to "先輩","level" to "N4","isCollected" to false)

        val playWords20 = mapOf("time" to "0:04:20.00","word" to "お昼","level" to "N3","isCollected" to false)



        // 05:00
        val playWords21 = mapOf("time" to "0:05:03.00","word" to "結婚","level" to "N5","isCollected" to false)

        // 07:00
        val playWords22 = mapOf("time" to "0:07:09.00","word" to "今","level" to "N5","isCollected" to false)

        // 09:00
        val playWords23 = mapOf("time" to "0:09:04.00","word" to "チーズ","level" to "N3","isCollected" to false)

        // 11:00
        val playWords24 = mapOf("time" to "0:11:00.00","word" to "会社","level" to "N5","isCollected" to false)

        // 13:00
        val playWords25 = mapOf("time" to "0:13:02.00","word" to "暗記","level" to "N3","isCollected" to false)

        // 15:00
        val playWords26 = mapOf("time" to "0:15:11.00","word" to "会う","level" to "N5","isCollected" to false)

        // 17:00
        val playWords27 = mapOf("time" to "0:17:04.00","word" to "やる","level" to "N5","isCollected" to false)

        // 19:00
        val playWords28 = mapOf("time" to "0:19:06.00","word" to "おいしい","level" to "N5","isCollected" to false)

        // 21:00
        val playWords29 = mapOf("time" to "0:21:06.00","word" to "大きい","level" to "N5","isCollected" to false)

        // 23:00 後隨意
        val playWords30 = mapOf("time" to "0:22:58.00","word" to "切り替える","level" to "N1","isCollected" to false)




        val episode01Data = mapOf(
            "episodeNum" to "1",
            "playWords" to listOf(
                playWords1, playWords2, playWords3, playWords4, playWords5, playWords6, playWords7, playWords8, playWords9, playWords10,
                playWords11, playWords12, playWords13, playWords14, playWords15, playWords16, playWords17, playWords18, playWords19, playWords20,
                playWords21, playWords22, playWords23, playWords24, playWords25, playWords26, playWords27, playWords28, playWords29, playWords30
            )
        )


        val category = listOf<String>("搞笑")      // 改
        val videosId = listOf<String>("BBmz-B5kSO8")    // 改

        // fire
        val animeInfoDocument = db.collection("animeInfo").document("GPaNphqGKY8JZdUSDAv6")    // 改

        val wordsDataList = mutableListOf<Map<String, Any>>()

        wordsDataList.add(episode01Data)

        val animeData = hashMapOf(    // 改
            "isCollected" to false,
            "animeId" to animeInfoDocument.id,
            "title" to "能幹貓今天也憂鬱",
            "releaseTime" to "2023/09/23",
            "episode" to "第1集",
            "rate" to "5.0",
            "wordsList" to wordsDataList,
            "category" to category,
            "videosId" to videosId,
            "pictureURL" to "https://p2.bahamut.com.tw/B/ACG/c/45/0000124645.JPG"
        )

        animeInfoDocument.set(animeData)
            .addOnSuccessListener { documentReference ->
                Log.d("AddFirebase", "DocumentSnapshot written with ID: $documentReference")
            }
            .addOnFailureListener { e ->
                Log.w("AddFirebase", "Error adding document", e)
            }
    }

    fun setAnimeInfo5_1() {

        // 0:00 ~ 05:00
        val playWords1 = mapOf("time" to "0:01:01.00","word" to "鬼","level" to "N3","isCollected" to false)

        val playWords2 = mapOf("time" to "0:01:04.00","word" to "勝つ","level" to "N4","isCollected" to false)

        val playWords3 = mapOf("time" to "0:01:11.00","word" to "泣く","level" to "N4","isCollected" to false)

        val playWords4 = mapOf("time" to "0:01:15.00","word" to "湧く","level" to "N2","isCollected" to false)

        val playWords5 = mapOf("time" to "0:01:18.00","word" to "恐ろしい","level" to "N3","isCollected" to false)

        val playWords6 = mapOf("time" to "0:01:19.00","word" to "拍手","level" to "N3","isCollected" to false)

        val playWords7 = mapOf("time" to "0:01:27.00","word" to "疲れる","level" to "N5","isCollected" to false)

        val playWords8 = mapOf("time" to "0:01:31.00","word" to "今日","level" to "N5","isCollected" to false)

        val playWords9 = mapOf("time" to "0:01:36.00","word" to "取る","level" to "N5","isCollected" to false)

        val playWords10 = mapOf("time" to "0:01:39.00","word" to "買う","level" to "N5","isCollected" to false)

        val playWords11 = mapOf("time" to "0:01:40.00","word" to "目","level" to "N5","isCollected" to false)

        val playWords12 = mapOf("time" to "0:01:42.00","word" to "毎度","level" to "N2","isCollected" to false)

        val playWords13 = mapOf("time" to "0:01:43.00","word" to "用意","level" to "N4","isCollected" to false)

        val playWords14 = mapOf("time" to "0:01:45.00","word" to "道","level" to "N5","isCollected" to false)

        val playWords15 = mapOf("time" to "0:01:50.00","word" to "ともかく","level" to "N2","isCollected" to false)

        val playWords16 = mapOf("time" to "0:01:51.00","word" to "飼う","level" to "N3","isCollected" to false)

        val playWords17 = mapOf("time" to "0:01:55.00","word" to "海","level" to "N5","isCollected" to false)

        val playWords18 = mapOf("time" to "0:01:57.00","word" to "聞く","level" to "N5","isCollected" to false)

        val playWords19 = mapOf("time" to "0:01:58.00","word" to "関する","level" to "N3","isCollected" to false)

        val playWords20 = mapOf("time" to "0:01:59.00","word" to "かもしれない","level" to "N3","isCollected" to false)



        // 05:00
        val playWords21 = mapOf("time" to "0:05:12.00","word" to "寝坊","level" to "N4","isCollected" to false)

        // 07:00
        val playWords22 = mapOf("time" to "0:07:20.00","word" to "安い","level" to "N5","isCollected" to false)

        // 09:00
        val playWords23 = mapOf("time" to "0:09:04.00","word" to "半分","level" to "N5","isCollected" to false)

        // 11:00
        val playWords24 = mapOf("time" to "0:11:03.00","word" to "言う","level" to "N5","isCollected" to false)

        // 13:00
        val playWords25 = mapOf("time" to "0:13:06.00","word" to "濃度","level" to "N2","isCollected" to false)

        // 15:00
        val playWords26 = mapOf("time" to "0:15:01.00","word" to "周り","level" to "N4","isCollected" to false)

        // 17:00
        val playWords27 = mapOf("time" to "0:17:02.00","word" to "頼む","level" to "N5","isCollected" to false)

        // 19:00
        val playWords28 = mapOf("time" to "0:19:03.00","word" to "切れる","level" to "N3","isCollected" to false)

        // 21:00
        val playWords29 = mapOf("time" to "0:20:51.00","word" to "取る","level" to "N5","isCollected" to false)

        // 23:00 後隨意
        val playWords30 = mapOf("time" to "0:20:55.00","word" to "遠慮","level" to "N4","isCollected" to false)




        val episode01Data = mapOf(
            "episodeNum" to "1",
            "playWords" to listOf(
                playWords1, playWords2, playWords3, playWords4, playWords5, playWords6, playWords7, playWords8, playWords9, playWords10,
                playWords11, playWords12, playWords13, playWords14, playWords15, playWords16, playWords17, playWords18, playWords19, playWords20,
                playWords21, playWords22, playWords23, playWords24, playWords25, playWords26, playWords27, playWords28, playWords29, playWords30
            )
        )


        val category = listOf<String>("懸疑")      // 改
        val videosId = listOf<String>("y72_kQdxU68")    // 改

        // fire
        val animeInfoDocument = db.collection("animeInfo").document("Ybvn5MriCOcdaTiW8kqo")    // 改

        val wordsDataList = mutableListOf<Map<String, Any>>()

        wordsDataList.add(episode01Data)

        val animeData = hashMapOf(    // 改
            "isCollected" to false,
            "animeId" to animeInfoDocument.id,
            "title" to "不死少女的謀殺鬧劇",
            "releaseTime" to "2023/09/19",
            "episode" to "第1集",
            "rate" to "4.7",
            "wordsList" to wordsDataList,
            "category" to category,
            "videosId" to videosId,
            "pictureURL" to "https://p2.bahamut.com.tw/B/ACG/c/21/0000131121.JPG"
        )

        animeInfoDocument.set(animeData)
            .addOnSuccessListener { documentReference ->
                Log.d("AddFirebase", "DocumentSnapshot written with ID: $documentReference")
            }
            .addOnFailureListener { e ->
                Log.w("AddFirebase", "Error adding document", e)
            }
    }
}