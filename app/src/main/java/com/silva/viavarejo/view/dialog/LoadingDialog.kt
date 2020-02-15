package com.silva.viavarejo.view.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.silva.viavarejo.R
import com.silva.viavarejo.databinding.DialogLoadingBinding
import java.util.*

class LoadingDialog: DialogFragment() {

    lateinit var bind: DialogLoadingBinding
    private var shown = false

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    companion object {
        private var INSTANCE: LoadingDialog? = null
        fun getInstance(): LoadingDialog {
            if (INSTANCE == null) {
                INSTANCE = LoadingDialog()
            }
            return INSTANCE!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.dialog_loading, container, false)
        with(Objects.requireNonNull<Window>(dialog?.window))
        {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            attributes.windowAnimations = R.style.DialogAnimation
        }

        isCancelable = false

        return bind.root
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (shown || isVisible || isAdded) return

        try {
            manager.beginTransaction().add(this, tag).commitNowAllowingStateLoss()
        } catch (e: Exception) {
            try {
                manager.beginTransaction().add(this, tag).commitAllowingStateLoss()
            } catch (e: Exception) {
            }
        }
        shown = true
    }

    override fun dismiss() {
        if (!shown || !isVisible) return

        shown = false
        super.dismissAllowingStateLoss()
    }
}