package com.silva.viavarejo.view.ui.extensions

import java.text.DecimalFormat

fun Double.toMoneyFormatter(): String {
    val formatter = DecimalFormat("R$###,###,##0.00")
    return formatter.format(this).toString()
}