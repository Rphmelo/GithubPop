package com.rphmelo.design.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rphmelo.design.R

fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url).into(this)
}

fun ImageView.loadCircularUrl(
    url: String,
    @DrawableRes errorImage: Int = R.drawable.bg_circular
) {
    Glide.with(context).load(url)
        .apply(RequestOptions.circleCropTransform())
        .error(ContextCompat.getDrawable(context, errorImage))
        .into(this)
}