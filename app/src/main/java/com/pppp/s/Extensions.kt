package com.pppp.s

import android.widget.ImageView
import com.squareup.picasso.Picasso
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun ImageView.loadImage(url: String?) {
    Picasso.get()
        .load(url)
        .error(R.drawable.missing_image)
        .into(this)
}

operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}