package com.example.flashanime.home.viewpage2.season

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
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
import com.google.android.material.tabs.TabLayout
import kotlin.math.roundToInt


private const val TAG: String = "HomeViewModel"

class SeasonViewModel(private val flashAnimeRepository: FlashAnimeRepository): ViewModel() {


    init {

//        TemporaryFile.addOtherAnimeInfo()

//        TemporaryFile.setAnimeInfo5_1()
//        TemporaryFile.addFirebaseAnimeInfo()

//        TemporaryFile.addUserCollectedWordsList()
//        TemporaryFile.addWeekList()
//        TemporaryFile.addFirebaseAnimeInfo()
//        TemporaryFile.addWeekList()
//        TemporaryFile.addUser()
//        TemporaryFile.setUserCollectedAnimeList()
    }


    var hotList: List<AnimeInfo>? = null

    private val _combinedList: LiveData<List<AnimeInfo>> = flashAnimeRepository.getAllAnimeInfo()
    val combinedList: LiveData<List<AnimeInfo>>
        get() = _combinedList


// carousel view

    val currentNum = MutableLiveData(0)

    var hotListReady = false
    var currentNumReady = false

    val callColor = MutableLiveData<Boolean?>()


    fun findFirstVisibleItemPosition(layoutManager: RecyclerView.LayoutManager): Int? {
        val childCount = layoutManager.childCount
        for (i in 0 until childCount) {
            val child = layoutManager.getChildAt(i)
            if (child != null) {
                if (layoutManager.getDecoratedBottom(child) > 0) {
                    return layoutManager.getPosition(child)
                }
            }
        }
        return null
    }

    fun findLastVisibleItemPosition(layoutManager: RecyclerView.LayoutManager): Int? {
        val childCount = layoutManager.childCount
        for (i in childCount - 1 downTo 0) {
            val child = layoutManager.getChildAt(i)
            if (child != null) {
                if (layoutManager.getDecoratedTop(child) < layoutManager.height) {
                    return layoutManager.getPosition(child)
                }
            }
        }
        return null
    }

    fun currentPosition(average: Double): Double {

        var position = 0.0

        if (average < 1) {
            position = 0.0
        }else{
            position = average.roundToInt().toDouble()
        }

        return position
    }

    fun callSetBackgroundColor(){
        if (hotListReady && currentNumReady){
            callColor.value = true
        }
    }

    fun setBackgroundColor(): String?{
        return currentNum.value?.let { index ->
            hotList?.let{
                hotList?.get(index)!!.pictureURL
            }
        }
    }

    fun bindImageMainWithPalette(layout: ConstraintLayout, mainImage: String?, imageView: ImageView) {

        val bgColor = ContextCompat.getColor(layout.context, R.color.new_bg)
        var generateColor: Int? = null

        mainImage?.let {
            val imgUri = it.toUri().buildUpon().scheme("https").build()
            Glide.with(layout.context)
                .asBitmap() // Specify that we want a Bitmap, not a Drawable
                .load(imgUri)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        Palette.from(resource).generate { palette ->
                            val dominantColor = palette?.getDominantColor(Color.BLACK)
                            dominantColor?.let { colorWithoutAlpha ->
                                // layout
                                val alpha = (0.4 * 255).toInt()
                                val newColor = Color.argb(alpha, Color.red(colorWithoutAlpha), Color.green(colorWithoutAlpha), Color.blue(colorWithoutAlpha))
                                generateColor = newColor

                                val oldColor = (layout.background as? ColorDrawable)?.color ?: bgColor

                                // animation
                                val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), oldColor, newColor)
                                colorAnimation.duration = 600

                                // imageView
                                val initialColors = generateColor?.let { intArrayOf(generateColor!!, bgColor) } ?: intArrayOf(bgColor, bgColor)
                                val gradientDrawable = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, initialColors)
                                imageView.background = gradientDrawable

                                colorAnimation.addUpdateListener { animator ->
                                    val animatedColor = animator.animatedValue as Int

                                    // layout
                                    layout.setBackgroundColor(animatedColor)

                                    // imageView
                                    gradientDrawable.colors = intArrayOf(bgColor, animatedColor, animatedColor, animatedColor, bgColor)

                                }

                                colorAnimation.start()

                            }
                        }
                    }
                    override fun onLoadCleared(placeholder: Drawable?) {}
                })




        }
    }



}