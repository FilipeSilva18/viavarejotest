package com.silva.viavarejo.data.model

import com.google.gson.annotations.SerializedName

data class WhoSawBoughtResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("sku") val sku: Long,
    @SerializedName("nome") val name: String,
    @SerializedName("imagemUrl") val imagePath: String,
    @SerializedName("precoAtual") val currentPrice: Double,
    @SerializedName("precoAnterior") val previousPrice: Double,
    @SerializedName("percentualCompra") val purchasePercentage: Int,
    @SerializedName("classificacao") val classification: Double,
    @SerializedName("parcelamento") val installments: String
)