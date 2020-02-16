package com.silva.viavarejo.view.product.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.silva.viavarejo.R
import com.silva.viavarejo.VHApplication
import com.silva.viavarejo.data.model.WhoSawBoughtResponse
import com.silva.viavarejo.databinding.ItemWhoSawBoughtBinding
import com.silva.viavarejo.view.ui.extensions.toMoneyFormatter

class WhoSawBoughtAdapter(var items: MutableList<WhoSawBoughtResponse>) :
    RecyclerView.Adapter<WhoSawBoughtAdapter.WhoSawBoughtViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WhoSawBoughtViewHolder {
        return WhoSawBoughtViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_who_saw_bought,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: WhoSawBoughtViewHolder, position: Int) {
        holder.bind(items[position])
    }


    inner class WhoSawBoughtViewHolder(private val bind: ItemWhoSawBoughtBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun bind(product: WhoSawBoughtResponse) {
            Glide.with(VHApplication.getAppContext())
                .load(product.imagePath)
                .error(ContextCompat.getDrawable(VHApplication.getAppContext(), R.drawable.ic_error))
                .transition(GenericTransitionOptions.with(R.anim.fade_in))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(bind.ivProduct)
            bind.tvProductName.text = product.name
            bind.tvCurrentPrice.text = product.currentPrice.toMoneyFormatter()
            bind.tvPreviousPrice.text = product.previousPrice.toMoneyFormatter()

        }
    }
}