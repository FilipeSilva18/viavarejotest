package com.silva.viavarejo.view.ui.binding

import android.graphics.Paint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.silva.viavarejo.view.ui.extensions.toMaxCharacters

@BindingAdapter(
    value = [
        "maxCharacters",
        "maxCharactersText"
    ]
)
fun TextView.maxCharacters(
    maxCharacters: Int,
    maxCharactersText: String
) {
    this.text = maxCharactersText.toMaxCharacters(maxCharacters)
}

@BindingAdapter("paintFlagStrike")
fun TextView.paintFlagStrike(
    paintFlagStrike: Boolean
) {
    this.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
}