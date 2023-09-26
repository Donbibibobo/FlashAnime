package com.example.flashanime.profile

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModel
import com.example.flashanime.data.source.FlashAnimeRepository
import kotlin.random.Random

class ProfileViewModel(private val flashAnimeRepository: FlashAnimeRepository): ViewModel() {


    fun showAbout(context: Context) {
        AlertDialog.Builder(context)
            .setTitle("Flash Anime")
            .setMessage("透過看動畫來學習單字，希望大家可以在娛樂中學習，在學習中成長！")
            .setPositiveButton("OK", null)
            .show()
    }
}