package com.example.flashanime

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.source.FlashAnimeRepository
import com.example.flashanime.util.CurrentFragmentType
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

private const val TAG: String = "MainViewModel"

class MainViewModel(private val flashAnimeRepository: FlashAnimeRepository): ViewModel() {

    // Record current fragment to support data binding
    val currentFragmentType = MutableLiveData<CurrentFragmentType>()


// login
    // handle leave dialog
    private lateinit var auth: FirebaseAuth
    fun getAuthInstance(){
        auth = FirebaseAuth.getInstance()
        UserManager.user = auth
    }
    fun checkIsLogin(): Boolean{
        val currentUser = auth.currentUser
        Log.i("googleLogin","${currentUser != null}")
        return currentUser != null
    }
    @SuppressLint("StaticFieldLeak")
    private lateinit var bottomNavigation: BottomNavigationView
    fun setBottomNavigation(bottomNavigation: BottomNavigationView){
        this.bottomNavigation = bottomNavigation
    }
    fun backToHome() {
        bottomNavigation.selectedItemId = R.id.navigation_home
    }



    val db = Firebase.firestore
    init {
        getAnimeList()
        snapShotFavoriteList()

        getAuthInstance()
    }


    private fun getAnimeList() {
        val animeInfoCollection = db.collection("animeInfo")
        val animeList = mutableListOf<AnimeInfo>()
        animeInfoCollection.get()
            .addOnSuccessListener {value ->
                for (document in value) {
                    val anime = document.toObject(AnimeInfo::class.java)
                    animeList.add(anime)
                }
                Log.i("animeListToCombine", "animeList: $animeList")
                animeListToCombine = animeList

                isAnimeListGet = true
                callOnCombinedList()
            }
    }

    private fun snapShotFavoriteList() {
        val userInfoCollection =
            db.collection("userInfo").document("Bstm28YuZ3ih78afvdq9").collection("animeCollection")
        Log.i("loginTest","${UserManager.user?.uid}")


        val favoriteList = mutableListOf<String>()

        userInfoCollection.addSnapshotListener { value, error ->
            if (error != null) {
                Log.w(TAG, "Listen failed.", error)
                return@addSnapshotListener

            } else if (value != null) {

                favoriteList.clear()

                for (document in value){
                    val favorite = document.id
                    favoriteList.add(favorite)
                }

                Log.i("animeListToCombine", "favoriteList: $favoriteList")
                favoriteListToCombine = favoriteList

                isFavoriteListGet = true
                callOnCombinedList()
            }
        }

    }


    // combine
    private var isAnimeListGet: Boolean = false
    private var isFavoriteListGet: Boolean = false
    private fun callOnCombinedList(){
        if (isAnimeListGet && isFavoriteListGet) {
            onCombinedList()
            isFavoriteListGet = false
        }
    }

    private var animeListToCombine = mutableListOf<AnimeInfo>()
    private var favoriteListToCombine = mutableListOf<String>()

    private fun onCombinedList() {
        val combined = animeListToCombine.map { animeInfo ->
            animeInfo.copy(isCollected = favoriteListToCombine.contains(animeInfo.animeId))
        }
        TemporaryFile.TempoAnimeInfo = combined // Temporary

        viewModelScope.launch {

            combined.forEach {
                flashAnimeRepository.insertAnimeInfoInDatabase(it)
            }
            Log.i("animeListToCombine","after insert")

        }
    }

    fun getCurrentFragmentTypeToHideCollected(): CurrentFragmentType? {
        return currentFragmentType.value
    }

    fun animateIcon(view: View) {
        val bounceAnimator = ValueAnimator.ofFloat(1f, 1.2f, 0.8f, 1.15f, 0.9f, 1f).apply {
            duration = 600
//            interpolator = BounceInterpolator()
            addUpdateListener {
                val scaleValue = it.animatedValue as Float
                view.scaleX = scaleValue
                view.scaleY = scaleValue
            }
        }
        bounceAnimator.start()
    }
}