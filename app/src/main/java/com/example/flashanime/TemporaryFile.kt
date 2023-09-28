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

            val playWords1 = mapOf("time" to "0:00:02.00","word" to "突然","level" to "N3","isCollected" to false)
            val playWords2 = mapOf("time" to "0:00:03.00","word" to "質問","level" to "N3","isCollected" to false)
            val playWords3 = mapOf("time" to "0:00:20.00","word" to "それ","level" to "N5","isCollected" to false)
            val playWords4 = mapOf("time" to "0:00:22.00","word" to "怒る","level" to "N5","isCollected" to false)
            val playWords5 = mapOf("time" to "0:00:29.00","word" to "待つ","level" to "N5","isCollected" to false)
            val playWords6 = mapOf("time" to "0:00:30.00","word" to "言う","level" to "N3","isCollected" to false)
            val playWords7 = mapOf("time" to "0:00:32.00","word" to "しかし","level" to "N3","isCollected" to false)
            val playWords8 = mapOf("time" to "0:00:33.00","word" to "私","level" to "N5","isCollected" to false)
            val playWords9 = mapOf("time" to "0:00:34.00","word" to "思う","level" to "N4","isCollected" to false)
            val playWords10 = mapOf("time" to "0:00:35.00","word" to "かもしれない","level" to "N4","isCollected" to false)
            val playWords11 = mapOf("time" to "0:00:42.00","word" to "働く","level" to "N4","isCollected" to false)
            val playWords12 = mapOf("time" to "0:00:43.00","word" to "前","level" to "N5","isCollected" to false)
            val playWords13 = mapOf("time" to "0:00:51.00","word" to "ちょうど","level" to "N3","isCollected" to false)
            val playWords14 = mapOf("time" to "0:00:55.00","word" to "在庫","level" to "N3","isCollected" to false)
            val playWords15 = mapOf("time" to "0:00:56.00","word" to "全滅","level" to "N1","isCollected" to false)
            val playWords16 = mapOf("time" to "0:00:57.00","word" to "午後","level" to "N5","isCollected" to false)
            val playWords17 = mapOf("time" to "0:01:02.00","word" to "了解","level" to "N4","isCollected" to false)
            val playWords18 = mapOf("time" to "0:01:05.00","word" to "買う","level" to "N5","isCollected" to false)
            val playWords19 = mapOf("time" to "0:01:16.00","word" to "慌ただしい","level" to "N2","isCollected" to false)
            val playWords20 = mapOf("time" to "0:01:17.00","word" to "多い","level" to "N4","isCollected" to false)
            val playWords21 = mapOf("time" to "0:01:19.00","word" to "刺激","level" to "N3","isCollected" to false)
            val playWords22 = mapOf("time" to "0:01:20.00","word" to "仕事","level" to "N5","isCollected" to false)
            val playWords23 = mapOf("time" to "0:01:23.00","word" to "多分","level" to "N4","isCollected" to false)
            val playWords24 = mapOf("time" to "0:01:24.00","word" to "本","level" to "N5","isCollected" to false)
            val playWords25 = mapOf("time" to "0:01:27.00","word" to "これ","level" to "N5","isCollected" to false)
            val playWords26 = mapOf("time" to "0:01:28.00","word" to "ありがとう","level" to "N5","isCollected" to false)
            val playWords27 = mapOf("time" to "0:01:30.00","word" to "孫","level" to "N4","isCollected" to false)
            val playWords28 = mapOf("time" to "0:01:31.00","word" to "最近","level" to "N3","isCollected" to false)
            val playWords29 = mapOf("time" to "0:01:33.00","word" to "楽しい","level" to "N4","isCollected" to false)
            val playWords30 = mapOf("time" to "0:01:35.00","word" to "気","level" to "N5","isCollected" to false)
            val playWords31 = mapOf("time" to "0:01:38.00","word" to "探す","level" to "N4","isCollected" to false)
            val playWords32 = mapOf("time" to "0:01:39.00","word" to "漫画","level" to "N3","isCollected" to false)
            val playWords33 = mapOf("time" to "0:01:40.00","word" to "こんなに","level" to "N4","isCollected" to false)
            val playWords34 = mapOf("time" to "0:01:42.00","word" to "ここ","level" to "N5","isCollected" to false)
            val playWords35 = mapOf("time" to "0:01:47.00","word" to "売り場","level" to "N3","isCollected" to false)
            val playWords36 = mapOf("time" to "0:01:57.00","word" to "濃い","level" to "N4","isCollected" to false)
            val playWords37 = mapOf("time" to "0:02:05.00","word" to "次","level" to "N5","isCollected" to false)
            val playWords38 = mapOf("time" to "0:02:10.00","word" to "分かる","level" to "N5","isCollected" to false)
            val playWords39 = mapOf("time" to "0:02:12.00","word" to "一見","level" to "N2","isCollected" to false)
            val playWords40 = mapOf("time" to "0:02:14.00","word" to "俳優","level" to "N3","isCollected" to false)
            val playWords41 = mapOf("time" to "0:02:18.00","word" to "厳か","level" to "N1","isCollected" to false)
            val playWords42 = mapOf("time" to "0:02:23.00","word" to "ポーズ","level" to "N3","isCollected" to false)
            val playWords43 = mapOf("time" to "0:02:25.00","word" to "欲しい","level" to "N4","isCollected" to false)
            val playWords44 = mapOf("time" to "0:02:27.00","word" to "乗る","level" to "N5","isCollected" to false)
            val playWords45 = mapOf("time" to "0:02:30.00","word" to "凄い","level" to "N3","isCollected" to false)
            val playWords46 = mapOf("time" to "0:02:32.00","word" to "綺麗","level" to "N4","isCollected" to false)
            val playWords47 = mapOf("time" to "0:02:35.00","word" to "もっと","level" to "N5","isCollected" to false)
            val playWords48 = mapOf("time" to "0:02:37.00","word" to "音","level" to "N5","isCollected" to false)
            val playWords49 = mapOf("time" to "0:02:40.00","word" to "大好き","level" to "N4","isCollected" to false)
            val playWords50 = mapOf("time" to "0:02:44.00","word" to "弾く","level" to "N3","isCollected" to false)
            val playWords51 = mapOf("time" to "0:02:50.00","word" to "特に","level" to "N3","isCollected" to false)
            val playWords52 = mapOf("time" to "0:02:53.00","word" to "緩い","level" to "N3","isCollected" to false)
            val playWords53 = mapOf("time" to "0:03:00.00","word" to "窓","level" to "N5","isCollected" to false)
            val playWords54 = mapOf("time" to "0:03:02.00","word" to "漫画家","level" to "N2","isCollected" to false)
            val playWords55 = mapOf("time" to "0:03:03.00","word" to "曲","level" to "N5","isCollected" to false)
            val playWords56 = mapOf("time" to "0:03:07.00","word" to "続ける","level" to "N3","isCollected" to false)
            val playWords57 = mapOf("time" to "0:03:09.00","word" to "画家","level" to "N2","isCollected" to false)
            val playWords58 = mapOf("time" to "0:03:13.00","word" to "所","level" to "N4","isCollected" to false)
            val playWords59 = mapOf("time" to "0:03:17.00","word" to "狭い","level" to "N4","isCollected" to false)
            val playWords60 = mapOf("time" to "0:04:09.00","word" to "薄い","level" to "N4","isCollected" to false)

            val playWordsEmpty = listOf<Map<String,Any>>(mapOf("time" to "0:00:00.00","word" to "(尚未新增單字資源)","level" to "","isCollected" to false))

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
//            val episode01Data = mapOf("episodeNum" to "1", "playWords" to playWordsEmpty)

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
            val animeInfoDocument = db.collection("animeInfo").document("IWoNOgF1WWR7rgX7IOuD") //document(IWoNOgF1WWR7rgX7IOuD)

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

        val monday1a1 = WeeklyAnime("01:00","書店裡的骷髏店員本田","全12集","IWoNOgF1WWR7rgX7IOuD")
        val monday1a2 = WeeklyAnime("01:05","黑暗集會","全12集","ParS5uxVFIekx3A2k1TE")
        val monday1a3 = WeeklyAnime("21:30","LV1 魔王與獨居廢勇者","全12集","R4pEoRSkc840H7swsDxZ")
        val mondayList = WeeklyAnimeList(listOf(monday1a1,monday1a2,monday1a3))

        val tuesday2a1 = WeeklyAnime("23:30","我喜歡的女孩忘記戴眼鏡","全12集","YJUR23GtxGMql64yUg56")
        val tuesdayList = WeeklyAnimeList(listOf(tuesday2a1))

        val wednesday3a1 = WeeklyAnime("01:29","勇者赫魯庫","全11集","W3NeoHNqZXUqgrBYCJXY")
        val wednesday3a2 = WeeklyAnime("22:00","AI 電子基因","全11集","BxS3XoY6VgBlfl4GUIuC")
        val wednesday3a3 = WeeklyAnime("22:00","雖然等級只有 1 級但固有技能是最強的","全12集","nuufstFX2ui4OhYn2Bbf")
        val wednesdayList = WeeklyAnimeList(listOf(wednesday3a1,wednesday3a2,wednesday3a3))

        val thursday4a1 = WeeklyAnime("00:00","不死少女的謀殺鬧劇","全12集","Ybvn5MriCOcdaTiW8kqo")
        val thursday4a2 = WeeklyAnime("01:00","成為悲劇元兇的最強異端，最後頭目女王為了人民犧牲奉獻","全12集","Di0LVMYWEERVOmIBcwGI")
        val thursdayList = WeeklyAnimeList(listOf(thursday4a1,thursday4a2))

        val friday5a1 = WeeklyAnime("01:25","聖者無雙～上班族的異世界生存之道～","全12集","jxmKxYr65kyx9GXVuhvq")
        val friday5a2 = WeeklyAnime("01:30","七魔劍支配天下","全12集","Tdhg6zKQ8dow0Ea4DEoY")
        val friday5a3 = WeeklyAnime("20:35","能幹貓今天也憂鬱","全12集","GPaNphqGKY8JZdUSDAv6")
        val fridayList = WeeklyAnimeList(listOf(friday5a1,friday5a2,friday5a3))

        val saturdayList = WeeklyAnimeList(listOf(monday1a1,monday1a2,monday1a3)) // use monday

        val sundayList = WeeklyAnimeList(listOf(wednesday3a1,wednesday3a2,wednesday3a3)) // use wednesday

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



//    fun addUserCollectedWordsList() {
//        val userAnimeListDocument =
//            db.collection("userInfo").document("Bstm28YuZ3ih78afvdq9")
//                .collection("wordsCollection").document("collectedWords")
//
//        val collectedWords = mapOf("word" to "薄い", "level" to "N4", "isCollected" to true)
//
//        userAnimeListDocument.get().addOnSuccessListener { document ->
//            if (document.exists()) {
//                // 如果文檔已存在，添加到列表中
//                userAnimeListDocument.update("collectedWords", FieldValue.arrayUnion(collectedWords))
//            } else {
//                // 若文檔不存在，創建新的文檔和集合
//                val collectedWordsList = mutableListOf(collectedWords)
//                val userData = hashMapOf("collectedWords" to collectedWordsList)
//
//                userAnimeListDocument.set(userData).addOnSuccessListener {
//                    Log.d("AddFirebase", "DocumentSnapshot successfully written!")
//                }.addOnFailureListener { e ->
//                    Log.w("AddFirebase", "Error writing document", e)
//                }
//            }
//        }.addOnFailureListener { e ->
//            Log.w("GetFirebase", "Error getting document", e)
//        }
//    }

    fun addUserCollectedWordsList() {
        // fire
        val userCollectedWordsList =
            db.collection("userInfo").document("Bstm28YuZ3ih78afvdq9")
                .collection("wordsCollection").document("突然")

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

}