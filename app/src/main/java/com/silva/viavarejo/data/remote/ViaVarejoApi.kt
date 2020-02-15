package com.silva.viavarejo.data.remote

import com.silva.viavarejo.data.model.ProductDetailResponse
import com.silva.viavarejo.data.model.ProductListResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ViaVarejoApi {

    @GET("5d1b4f0f34000074000006dd")
    fun getProductList(): Observable<ProductListResponse>

    @GET("5d1b4fd23400004c000006e1")
    fun getProductDetail(): Observable<ProductDetailResponse>
}