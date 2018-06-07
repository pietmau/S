package com.pppp.s

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url: String?) {
    Picasso.get()
        .load(url)
        .error(R.drawable.no_image)
        .into(this)
}