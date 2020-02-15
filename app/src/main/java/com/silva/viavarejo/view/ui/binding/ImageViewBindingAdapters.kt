package com.silva.viavarejo.view.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.silva.viavarejo.view.ui.extensions.glideLoad


@BindingAdapter(
    "loadUrl"
)
fun ImageView.loadUrl(
    loadUrl: String?
) {
    glideLoad(
        url = loadUrl,
        defaultError = null,
        defaultPlaceholder = null
    )
}