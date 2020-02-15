package com.silva.viavarejo.data.model

import com.google.gson.annotations.SerializedName

data class ProductListResponse(
    @SerializedName("produtos") val products : ArrayList<Product>,
    @SerializedName("quantidade") val amount : Int
)

data class Product(
    @SerializedName("id") val id : Long,
    @SerializedName("sku") val sku : Long,
    @SerializedName("nome") val name : String,
    @SerializedName("disponivel") val available : Boolean,
    @SerializedName("descricao") val description : String,
    @SerializedName("imagemUrl") val imageUrl : String,
    @SerializedName("classificacao") val rating : String,
    @SerializedName("preco") val price : Price
)

data class Price(
    @SerializedName("planoPagamento") val paymentPlan : String,
    @SerializedName("valorParcela") val installmentValue : Double,
    @SerializedName("quantidadeMaximaParcelas") val installmentMax : Int,
    @SerializedName("precoAtual") val currentPrice : Double,
    @SerializedName("precoAnterior") val previousPrice : Double,
    @SerializedName("porcentagemDesconto") val discountPercentage : Int,
    @SerializedName("descontoFormaPagamento") val formPaymentDiscount : FormPayment?
)

data class FormPayment(
    @SerializedName("preco") val price : Double,
    @SerializedName("possuiDesconto") val hasDiscount : Boolean,
    @SerializedName("descricao") val description: String?,
    @SerializedName("porcentagemDesconto") val discountPercentage : Long

)