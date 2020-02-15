package com.silva.viavarejo.view.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.silva.viavarejo.view.dialog.LoadingDialog
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment() {

    protected lateinit var bind: T
    protected lateinit var viewModel: V
    protected lateinit var actViewModel: V
    protected lateinit var parentFragmentViewModel: V

    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory

    protected abstract val fragmentLayout: Int

    protected abstract val viewModelClass: Class<V>?

    protected abstract fun init()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, fragmentLayout, container, false)

        if (viewModelClass != null) {
            viewModel = ViewModelProviders.of(this, viewModelProvider).get(viewModelClass!!)

            if (activity != null) {
                actViewModel = ViewModelProviders.of(requireActivity(), viewModelProvider)
                    .get(viewModelClass!!)
            }

            if (parentFragment != null) {
                parentFragmentViewModel =
                    ViewModelProviders.of(parentFragment!!, viewModelProvider).get(viewModelClass!!)
            }
        }
        init()
        return bind.root
    }

    fun hideLoadingDialog() {
        LoadingDialog.getInstance().dismiss()
    }

    fun showLoadingDialog(context: Context?) {
        if (context != null)
            LoadingDialog.getInstance().show(
                (context as AppCompatActivity).supportFragmentManager,
                null
            )
    }
}