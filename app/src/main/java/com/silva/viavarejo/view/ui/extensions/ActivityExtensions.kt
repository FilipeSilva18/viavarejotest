package com.silva.viavarejo.view.ui.extensions

import android.content.Context
import android.content.Intent
import android.os.Bundle

inline fun <reified A> Context.initActivity(
    clearTask: Boolean = false,
    extras: ((Bundle) -> Unit) = {}
) {
    val intent = Intent(this, A::class.java)

    val bundle = Bundle()
    extras.invoke(bundle)
    intent.putExtras(bundle)

    if (clearTask) {
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    startActivity(intent)
}