package com.example.flashanime.hot

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.flashanime.R
import com.example.flashanime.data.AnimeInfo
import com.example.flashanime.data.source.FlashAnimeRepository
import kotlin.math.roundToInt

class HotViewModel(private val flashAnimeRepository: FlashAnimeRepository): ViewModel() {

    private val _combinedList: LiveData<List<AnimeInfo>> = flashAnimeRepository.getAllAnimeInfo()
    val combinedList: LiveData<List<AnimeInfo>>
        get() = _combinedList

//    val currentNum = MutableLiveData(0)
//
//    var combinedListReady = false
//    var currentNumReady = false
//
//    val callColor = MutableLiveData<Boolean?>()
//
//
//    fun findFirstVisibleItemPosition(layoutManager: RecyclerView.LayoutManager): Int? {
//        val childCount = layoutManager.childCount
//        for (i in 0 until childCount) {
//            val child = layoutManager.getChildAt(i)
//            if (child != null) {
//                if (layoutManager.getDecoratedBottom(child) > 0) {
//                    return layoutManager.getPosition(child)
//                }
//            }
//        }
//        return null
//    }
//
//    fun findLastVisibleItemPosition(layoutManager: RecyclerView.LayoutManager): Int? {
//        val childCount = layoutManager.childCount
//        for (i in childCount - 1 downTo 0) {
//            val child = layoutManager.getChildAt(i)
//            if (child != null) {
//                if (layoutManager.getDecoratedTop(child) < layoutManager.height) {
//                    return layoutManager.getPosition(child)
//                }
//            }
//        }
//        return null
//    }
//
//    fun currentPosition(average: Double): Double {
//
//        var position = 0.0
//
//        if (average < 1) {
//            position = 0.0
//        }else{
//            position = average.roundToInt().toDouble()
//        }
//
//        return position
//    }
//
//    fun callSetBackgroundColor(){
//        if (combinedListReady && currentNumReady){
//            callColor.value = true
//        }
//    }
//
//    fun setBackgroundColor(): String?{
//        return currentNum.value?.let { _combinedList.value?.get(it)!!.pictureURL }
//    }
//
//    fun bindImageMainWithPalette(layout: ConstraintLayout, mainImage: String?, context: Context, imageView: ImageView) {
//
//        val bgColor = ContextCompat.getColor(layout.context, R.color.new_bg)
//        var generateColor: Int? = null
//
//        mainImage?.let {
//            val imgUri = it.toUri().buildUpon().scheme("https").build()
//            Glide.with(layout.context)
//                .asBitmap() // Specify that we want a Bitmap, not a Drawable
//                .load(imgUri)
//                .into(object : CustomTarget<Bitmap>() {
//                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
//                        Palette.from(resource).generate { palette ->
//                            val dominantColor = palette?.getDominantColor(Color.BLACK)
//                            dominantColor?.let { colorWithoutAlpha ->
//                                val alpha = (0.4 * 255).toInt()
//                                val newColor = Color.argb(alpha, Color.red(colorWithoutAlpha), Color.green(colorWithoutAlpha), Color.blue(colorWithoutAlpha))
//                                generateColor = newColor
//
//                                val oldColor = (layout.background as? ColorDrawable)?.color ?: bgColor
//
//                                val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), oldColor, newColor)
//                                colorAnimation.duration = 600
//
//                                colorAnimation.addUpdateListener { animator ->
//                                    val animatedColor = animator.animatedValue as Int
//                                    layout.setBackgroundColor(animatedColor)
//                                }
//
//                                colorAnimation.start()
//
//
//                                // Here, update the palette ImageView's background
//                                val gradientDrawable = GradientDrawable(
//                                    GradientDrawable.Orientation.TOP_BOTTOM,
//                                    generateColor?.let { intArrayOf(generateColor!!, bgColor) } ?: intArrayOf(bgColor, bgColor)
//                                )
//
//                                imageView.background = gradientDrawable
//                            }
//                        }
//                    }
//                    override fun onLoadCleared(placeholder: Drawable?) {}
//                })
//
//
//
//
//        }
//    }




}
