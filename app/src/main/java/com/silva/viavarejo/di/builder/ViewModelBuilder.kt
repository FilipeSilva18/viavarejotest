package com.silva.viavarejo.di.builder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.silva.viavarejo.di.annotation.ViewModelKey
import com.silva.viavarejo.viewmodel.MainViewModel
import com.silva.viavarejo.viewmodel.ProductDetailViewModel
import com.silva.viavarejo.viewmodel.ProductListViewModel
import com.silva.viavarejo.viewmodel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductListViewModel::class)
    abstract fun bindProductListViewModel(productListViewModel: ProductListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductDetailViewModel::class)
    abstract fun bindProductDetailViewModel(productDetailViewModel: ProductDetailViewModel): ViewModel

    //Factory
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

}