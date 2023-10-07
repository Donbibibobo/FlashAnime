package com.example.flashanime.all.category

import android.animation.ValueAnimator
import android.view.View
import android.view.animation.BounceInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flashanime.data.JLPTWordInfo
import com.example.flashanime.data.source.FlashAnimeRepository

class CategoryViewModel(private val flashAnimeRepository: FlashAnimeRepository) : ViewModel() {



    // handle leave dialog
    private val _leave = MutableLiveData<Boolean>()
    val leave: LiveData<Boolean>
        get() = _leave

    // leave dialog
    fun leave() {
        _leave.value = true
    }

    fun onLeaveCompleted() {
        _leave.value = null
    }

    fun categoryIcon(view: View) {
        val bounceAnimator = ValueAnimator.ofFloat(1f, 0.9f, 1f).apply {
            duration = 300
            interpolator = DecelerateInterpolator()
            addUpdateListener {
                val scaleValue = it.animatedValue as Float
                view.scaleX = scaleValue
                view.scaleY = scaleValue
            }
        }
        bounceAnimator.start()
    }


}