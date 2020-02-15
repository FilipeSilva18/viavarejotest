package com.silva.viavarejo.view.ui.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

fun ImageView.glideLoad(
    url: String?,
    defaultError: Drawable?,
    defaultPlaceholder: Drawable?
) {
    if (url.isNullOrEmpty()) {
        setImageDrawable(defaultPlaceholder)
        return
    }

    val requestBuilder = Glide.with(this)
        .asBitmap()
        .apply(
            RequestOptions.noTransformation()
        )

    requestBuilder
        .load(url)
        .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
        .error(defaultError)
        .placeholder(defaultPlaceholder)
        .into(this)
}