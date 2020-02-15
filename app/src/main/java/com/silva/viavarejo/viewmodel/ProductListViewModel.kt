package com.silva.viavarejo.viewmodel

import androidx.lifecycle.MutableLiveData
import com.silva.viavarejo.data.model.Product
import com.silva.viavarejo.data.repository.ViaVarejoRepository
import com.silva.viavarejo.view.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject


class ProductListViewModel @Inject constructor(private val viaVarejoRepository: ViaVarejoRepository) :
    BaseViewModel() {

    val screen = MutableLiveData<Screen>()

    fun getProductList() {
        screen.value = Screen.ShowLoading
        viaVarejoRepository.run {
            getProductList().observeOn(AndroidSchedulers.mainThread()).subscribe({
                screen.value = Screen.HideLoading
                screen.value = Screen.SuccessProductList(it.products)
            }, {
                screen.value = Screen.HideLoading
                screen.value = Screen.ShowError
            })
        }
    }

    sealed class Screen {
        object ShowLoading : Screen()
        object HideLoading : Screen()
        object ShowError : Screen()
        data class SuccessProductList(val products: MutableList<Product>) : Screen()
    }
}