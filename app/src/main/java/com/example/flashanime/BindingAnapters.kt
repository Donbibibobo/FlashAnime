package com.example.flashanime

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

/**
 * Uses the Glide library to load an image by URL into an [ImageView]
 */

@BindingAdapter("imageUrl")
fun bindImageMain(imageView: ImageView, mainImage: String?) {
    mainImage?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imgUri)
            .placeholder(R.drawable.sample)
            .into(imageView)
    }
}

@BindingAdapter("imageUrlWithGradient")
fun bindImageWithGradient(imageView: ImageView, mainImage: String?) {
    mainImage?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()

        val bgColor = ContextCompat.getColor(imageView.context, R.color.new_bg)

        // create gradientDrawable
        val gradientDrawable = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(
            Color.TRANSPARENT, bgColor))

        Glide.with(imageView.context)
            .load(imgUri)
            .placeholder(R.drawable.sample)
            .into(object : CustomTarget<Drawable>() {
                override fun onLoadCleared(placeholder: Drawable?) {}
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    // when glide finish, mix with gradientDrawable
                    resource.alpha = 110  // 1 * 255
                    val layers = arrayOf(resource, gradientDrawable)
                    val layerListDrawable = LayerDrawable(layers)

                    imageView.setImageDrawable(layerListDrawable)
                }
            })
    }
}