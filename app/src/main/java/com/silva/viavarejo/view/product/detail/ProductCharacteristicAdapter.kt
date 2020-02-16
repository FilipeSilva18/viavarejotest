package com.silva.viavarejo.view.product.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.silva.viavarejo.R
import com.silva.viavarejo.data.model.MoreInformationValues
import com.silva.viavarejo.databinding.ItemProductCharacteristicBinding

class ProductCharacteristicAdapter(var characteristics: MutableList<MoreInformationValues>) :
    RecyclerView.Adapter<ProductCharacteristicAdapter.ProductCharacteristicViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductCharacteristicViewHolder {
        return ProductCharacteristicViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_product_characteristic,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = characteristics.size

    override fun onBindViewHolder(holder: ProductCharacteristicViewHolder, position: Int) {
        holder.bind(characteristics[position])
    }

    inner class ProductCharacteristicViewHolder(private val bind: ItemProductCharacteristicBinding) :
        RecyclerView.ViewHolder(bind.root) {

        fun bind(characteristic: MoreInformationValues) {
            bind.tvTitleCharacteristic.text = characteristic.name
            bind.tvCharacteristic.text = characteristic.values
        }
    }
}