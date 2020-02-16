package com.silva.viavarejo.data.model

import com.google.gson.annotations.SerializedName
import com.silva.viavarejo.view.ui.extensions.toMoneyFormatter
import com.silva.viavarejo.viewmodel.ProductDetailViewModel

data class ProductDetailResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("nome") val name: String,
    @SerializedName("descricao") val description: String,
    @SerializedName("retiraEmLoja") val withdrawStore: Boolean,
    @SerializedName("categorias") val categories: ArrayList<Categories>,
    @SerializedName("maisInformacoes") val moreInformation: ArrayList<MoreInformation>,
    @SerializedName("marca") val brand: Brand,
    @SerializedName("modelo") val model: Model,
    @SerializedName("urlVideo") val pathVideo: String?

) {
    fun toProductPresentation() = ProductDetailViewModel.Presentation(
        productImagePath = model.pattern.images[0].path,
        productCurrentPrice = model.pattern.price.currentPrice.toMoneyFormatter(),
        productPreviousPrice = model.pattern.price.previousPrice.toMoneyFormatter(),
        productInstallmentMaxPrice = model.pattern.price.paymentPlan,
        productName = name,
        characteristics = getListCharacteristic()
    )

    private fun getListCharacteristic(): MutableList<MoreInformationValues> {
        val values: MutableList<MoreInformationValues> = arrayListOf()
        for (c in moreInformation) {
            if (c.description == "Características") {
                for(characteristic in c.values){
                    if(characteristic.name != "Características Gerais "){
                        values.add(characteristic)
                    }
                }
                return values
            }
        }
        return arrayListOf()
    }
}

data class Categories(
    @SerializedName("id") val id: Long,
    @SerializedName("descricao") val description: String
)

data class MoreInformation(
    @SerializedName("descricao") val description: String,
    @SerializedName("valores") val values: ArrayList<MoreInformationValues>
)

data class MoreInformationValues(
    @SerializedName("nome") val name: String,
    @SerializedName("valor") val values: String
)

data class Brand(
    @SerializedName("id") val id: Long,
    @SerializedName("nome") val name: String
)

data class Model(
    @SerializedName("skus") val skud: ArrayList<String>,
    @SerializedName("padrao") val pattern: Pattern
)

data class Pattern(
    @SerializedName("sku") val sku: Long,
    @SerializedName("nome") val name: String,
    @SerializedName("disponivel") val available: Boolean,
    @SerializedName("marketplace") val marketplace: Marketplace,
    @SerializedName("preco") val price: Price,
    @SerializedName("imagens") val images: ArrayList<Images>,
    @SerializedName("servicos") val services: ArrayList<Services>
)

data class Marketplace(
    @SerializedName("maiorPreco") val biggestPrice: Double,
    @SerializedName("menorPreco") val lowestPrice: Double,
    @SerializedName("lojistaPadrao") val shopkeeperDefault: Shopkeeper,
    @SerializedName("lojistasEmDestaque") val shopkeeperFeatured: ArrayList<Shopkeeper>
)

data class Shopkeeper(
    @SerializedName("id") val id: Long,
    @SerializedName("nome") val name: String,
    @SerializedName("preco") val price: Double,
    @SerializedName("retiraRapido") val withdrawQuickly: Boolean,
    @SerializedName("compraOnline") val onlineStore: Boolean,
    @SerializedName("eleito") val elected: Boolean
)

data class Images(
    @SerializedName("id") val id: Long,
    @SerializedName("nome") val name: String,
    @SerializedName("altura") val height: Double,
    @SerializedName("largura") val width: Double,
    @SerializedName("url") val path: String
)

data class Services(
    @SerializedName("nome") val name: String,
    @SerializedName("sku") val sku: Long,
    @SerializedName("idLojista") val idShopkeeper: Long,
    @SerializedName("preco") val price: Double,
    @SerializedName("parcelamento") val installments: String,
    @SerializedName("tipo") val type: String


)