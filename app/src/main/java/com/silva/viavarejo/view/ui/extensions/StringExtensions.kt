package com.silva.viavarejo.view.ui.extensions

import java.text.DecimalFormat

fun String.toMoneyFormatte(): String {
    val formatter = DecimalFormat("R$#.###.###,##")
    return formatter.format(this.toLong()).toString()
}

fun String.toMaxCharacters(maxCharacters: Int) =
    if (this.length > maxCharacters) "${this.subSequence(
        0,
        maxCharacters
    )}..." else this
