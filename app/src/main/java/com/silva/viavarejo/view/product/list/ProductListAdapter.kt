package com.silva.viavarejo.view.product.list

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.silva.viavarejo.R
import com.silva.viavarejo.VHApplication
import com.silva.viavarejo.data.model.Product
import com.silva.viavarejo.databinding.ItemProductListBinding
import com.silva.viavarejo.view.ui.extensions.toMaxCharacters
import com.silva.viavarejo.view.ui.extensions.toMoneyFormatter

class ProductListAdapter(var products: MutableList<Product>) :
    RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {
    lateinit var onItemClickListener: OnItemClickListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductListViewHolder {
        return ProductListViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_product_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        holder.bind(products[position])
    }

    inner class ProductListViewHolder(val bind: ItemProductListBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun bind(product: Product) {

            Glide.with(VHApplication.getAppContext())
                .load(product.imageUrl)
//                .error(ContextCompat.getDrawable(VHApplication.getAppContext(), R.drawable.grey_logo))
//                .placeholder(ContextCompat.getDrawable(WMApplication.getAppContext(), R.drawable.grey_logo))
                .transition(GenericTransitionOptions.with(R.anim.fade_in))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(bind.ivProduct)
            bind.tvProductName.text = product.name
            bind.tvCurrentPrice.text = product.price.currentPrice.toMoneyFormatter()
            bind.tvPreviousPrice.text = product.price.previousPrice.toMoneyFormatter()
            bind.clContainer.setOnClickListener {
                onItemClickListener.onItemClickListener()
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClickListener()
    }
}