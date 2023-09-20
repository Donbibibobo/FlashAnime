package com.example.flashanime

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.flashanime.data.AnimeInfo
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object TemporaryFile {

    // let allAnimeInfo use data from home page
    var TempoAnimeInfo: List<AnimeInfo>? = null



    // fire base
    /* collection:
     （工具）動畫 id 列表 -> 用來打 API 然後爬蟲，並將資訊上傳 firebase
     （必要）動畫資訊 -> AnimeInfo, 每個「動畫資訊」的爬蟲資料不包含「單字列表」以及「評分」,影片要分 type 1 或 2
     （必要）每週資訊 -> WeekFragment 顯示用
     （必要）儲存單字 -> 存進 firebase -> snap 進 db -> 觀察 db liveData -> 顯示
     */

//    @SuppressLint("StaticFieldLeak")
//    val db = Firebase.firestore
//    private val animeInfoCollection = db.collection("animeInfo")
//    private val TAG: String = "FirebaseTest"
//
//
//    private val articlesList =  mutableListOf<Articles>()
//
//
//    private val _articlesListAll = MutableLiveData<List<Articles>>()
//    val articlesListAll: LiveData<List<Articles>>
//        get() = _articlesListAll
//
//    fun addSnapshotListener() {
//
//        animeInfoCollection.addSnapshotListener { value, error ->
//            if (error != null) {
//                Log.w(TAG, "Listen failed.", error)
//                return@addSnapshotListener
//
//            } else if (value != null && !value.metadata.hasPendingWrites()) {
//
//                articlesList.clear()
//
//                for (document in value!!) {
//                    Log.i(TAG, "====================")
//                    Log.i(TAG, "author: ${document.data["author"]}")
//                    Log.i(TAG, "author: ${document.data["author"]}")
//                    Log.i(TAG, "category: ${document.data["category"]}")
//                    Log.i(TAG, "content: ${document.data["content"]}")
//                    Log.i(TAG, "createdTime: ${document.data["createdTime"]}")
//                    Log.i(TAG, "id: ${document.data["id"]}")
//                    Log.i(TAG, "title: ${document.data["title"]}")
//                    Log.i(TAG, "====================")
//
//                    // add to list
//                    val authorMap = document.data["author"] as? Map<String, Any> ?: emptyMap()
//
//                    articlesList.add(Articles(
//                        authorMap,
//                        document.data["category"].toString(),
//                        document.data["content"].toString(),
//                        document.data["createdTime"].toString(),
//                        document.data["id"].toString(),
//                        document.data["title"].toString()
//                    ))
//                }
//                _articlesListAll.value = articlesList
//            }
//        }
//    }
}