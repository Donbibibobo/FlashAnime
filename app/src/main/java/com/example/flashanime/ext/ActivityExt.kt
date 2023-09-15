package com.example.flashanime.ext

import android.app.Activity
import android.view.Gravity
import android.widget.Toast
import com.example.flashanime.FlashAnimeApplication
import com.example.flashanime.factory.ViewModelFactory

fun Activity.getVmFactory(): ViewModelFactory {
    val repository = (applicationContext as FlashAnimeApplication).flashAnimeRepository
    return ViewModelFactory(repository)
}

fun Activity?.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).apply {
        setGravity(Gravity.CENTER, 0, 0)
        show()
    }
}
