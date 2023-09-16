package com.example.flashanime

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

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