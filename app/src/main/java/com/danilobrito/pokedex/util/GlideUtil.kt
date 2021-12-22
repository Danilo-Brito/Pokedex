package com.danilobrito.pokedex.util

import android.content.Context
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.google.android.material.card.MaterialCardView
import com.google.android.material.imageview.ShapeableImageView

class GlideUtil() {

    fun setCardColor(
        imageUrl: String,
        cardView: MaterialCardView,
        image: AppCompatImageView,
        context: Context
    ) {
        Glide.with(context).load(imageUrl)
            .listener(
                GlidePalette.with(imageUrl)
                    .use(BitmapPalette.Profile.MUTED_LIGHT)
                    .intoCallBack { palette ->
                        val rgb = palette?.dominantSwatch?.rgb
                        if (rgb != null) {
                            cardView.setCardBackgroundColor(rgb)
                        }
                    }.crossfade(true)
            ).into(image)
    }

    fun setCardColor(
        imageUrl: String,
        cardView: ShapeableImageView,
        image: AppCompatImageView,
        context: Context
    ) {
        Glide.with(context)
            .load(imageUrl)
            .listener(
                GlidePalette.with(imageUrl)
                    .use(BitmapPalette.Profile.MUTED_LIGHT)
                    .intoCallBack { palette ->
                        val domain = palette?.dominantSwatch?.rgb
                        if (domain != null) {
                                cardView.setBackgroundColor(domain)
                            if (context is AppCompatActivity) {
                                context.window.apply {
                                    addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                                    statusBarColor = domain
                                }
                            }
                        }
                    }.crossfade(true)
            ).into(image)
    }

    fun loadImage(
        imageUrl: String,
        image: AppCompatImageView,
        context: Context
    ) {
        Glide.with(context)
            .load(imageUrl)
            .centerCrop()
            .into(image)
    }
}