package com.silva.viavarejo.data.repository

import com.silva.viavarejo.data.model.ProductDetailResponse
import com.silva.viavarejo.data.model.ProductListResponse
import com.silva.viavarejo.data.remote.ViaVarejoApi
import com.silva.viavarejo.viewmodel.ProductDetailViewModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ViaVarejoRepository @Inject constructor(private val viaVarejoApi: ViaVarejoApi) {

    fun getProductList(): Observable<ProductListResponse> {
        return viaVarejoApi.getProductList()
            .subscribeOn(Schedulers.io())
    }

    fun getProductDetail(): Observable<ProductDetailViewModel.Presentation> {
        return viaVarejoApi.getProductDetail()
            .subscribeOn(Schedulers.io())
            .map {
                it.toProductPresentation()
            }
    }
}