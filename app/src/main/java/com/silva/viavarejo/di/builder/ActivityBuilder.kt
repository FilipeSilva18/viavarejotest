package com.silva.viavarejo.di.builder

import com.silva.viavarejo.di.annotation.Activity
import com.silva.viavarejo.di.module.MainModule
import com.silva.viavarejo.view.main.MainActivity
import com.silva.viavarejo.view.product.detail.ProductDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @Activity
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun bindMainActivity(): MainActivity

    @Activity
    @ContributesAndroidInjector
    abstract fun bindProductDetailActivity(): ProductDetailActivity
}