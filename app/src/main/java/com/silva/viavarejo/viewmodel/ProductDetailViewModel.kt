package com.silva.viavarejo.viewmodel

import androidx.lifecycle.MutableLiveData
import com.silva.viavarejo.data.model.MoreInformationValues
import com.silva.viavarejo.data.model.WhoSawBoughtResponse
import com.silva.viavarejo.data.repository.ViaVarejoRepository
import com.silva.viavarejo.view.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class ProductDetailViewModel @Inject constructor(private val viaVarejoRepository: ViaVarejoRepository) :
    BaseViewModel() {

    val screen = MutableLiveData<Screen>()
    val presentation = MutableLiveData<Presentation>()

    fun getProductDetail() {
        screen.value = Screen.ShowLoading
        viaVarejoRepository.run {
            getProductDetail().observeOn(AndroidSchedulers.mainThread()).subscribe({
                screen.value = Screen.HideLoading
                presentation.value = it
            }, {
                screen.value = Screen.ShowError
            })
        }
    }

    fun getWhoSawBought() {
        screen.value = Screen.ShowLoading
        viaVarejoRepository.run {
            getWhoSawBought().observeOn(AndroidSchedulers.mainThread()).subscribe({
                screen.value = Screen.HideLoading
                screen.value = Screen.WhoSawBoughtSuccess(it)
            }, {
                screen.value = Screen.ShowError
            })
        }
    }

    fun initPresentation() {
        presentation.value = getPresentation()
    }

    private fun getPresentation() = presentation.value ?: Presentation()

    data class Presentation(
        var productImagePath: String = "",
        var productName: String = "",
        var productPreviousPrice: String = "",
        var productCurrentPrice: String = "",
        var productInstallmentMaxPrice: String = "",
        val maxCharactersProductName: Int = 24,
        var characteristics: MutableList<MoreInformationValues> = arrayListOf()
    )

    sealed class Screen {
        object ShowLoading : Screen()
        object HideLoading : Screen()
        object ShowError : Screen()
        data class WhoSawBoughtSuccess(val items: MutableList<WhoSawBoughtResponse>) : Screen()
    }
}