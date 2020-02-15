package com.silva.viavarejo.view.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.silva.viavarejo.view.dialog.LoadingDialog
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity(),
    HasSupportFragmentInjector {

    protected lateinit var viewModel: V
    protected lateinit var bind: T


    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> =
        fragmentDispatchingAndroidInjector

    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected abstract fun getViewModelClass(): Class<V>

    protected abstract fun init()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)

        bind = DataBindingUtil.setContentView(this, getLayoutId())
        viewModel = ViewModelProviders.of(this, this.viewModelProvider).get(getViewModelClass())

        init()
    }

    fun hideLoadingDialog() {
        LoadingDialog.getInstance().dismiss()
    }

    fun showLoadingDialog() {
        LoadingDialog.getInstance().show(supportFragmentManager, null)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
