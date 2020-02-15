package com.silva.viavarejo.di.module

import com.silva.viavarejo.view.main.HomeFragment
import com.silva.viavarejo.view.product.list.ProductListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {

    @ContributesAndroidInjector
    abstract fun ProductListFragment(): ProductListFragment

    @ContributesAndroidInjector
    abstract fun HomeFragment(): HomeFragment
}