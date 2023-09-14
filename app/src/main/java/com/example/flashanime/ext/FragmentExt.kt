package com.example.flashanime.ext

import androidx.fragment.app.Fragment
import com.example.flashanime.FlashAnimeApplication
import com.example.flashanime.factory.ViewModelFactory

fun Fragment.getVmFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as FlashAnimeApplication).flashAnimeRepository
    return ViewModelFactory(repository)
}