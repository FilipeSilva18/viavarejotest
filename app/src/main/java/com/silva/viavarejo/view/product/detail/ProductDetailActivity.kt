package com.silva.viavarejo.view.product.detail

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.silva.viavarejo.R
import com.silva.viavarejo.data.model.MoreInformationValues
import com.silva.viavarejo.data.model.WhoSawBoughtResponse
import com.silva.viavarejo.databinding.ActivityProductDetailBinding
import com.silva.viavarejo.view.base.BaseActivity
import com.silva.viavarejo.view.ui.extensions.observeNotNull
import com.silva.viavarejo.viewmodel.ProductDetailViewModel

class ProductDetailActivity : BaseActivity<ActivityProductDetailBinding, ProductDetailViewModel>() {

    lateinit var adapter: ProductCharacteristicAdapter
    lateinit var whoSawBoughtAdapter: WhoSawBoughtAdapter

    override fun getLayoutId() = R.layout.activity_product_detail

    override fun getViewModelClass() = ProductDetailViewModel::class.java

    override fun init() {
        setupToolbar()
        viewModel.getProductDetail()
        viewModel.getWhoSawBought()
        viewModel.initPresentation()
        observeData()
    }

    private fun setupToolbar() {
        setSupportActionBar(bind.viewProductDetail.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        bind.viewProductDetail.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun observeData() {
        viewModel.presentation.observeNotNull(this) {
            bind.presentation = it
            initAdapter(it.characteristics)
        }
        viewModel.screen.observeNotNull(this) {
            when (it) {
                is ProductDetailViewModel.Screen.ShowLoading -> showLoadingDialog()
                is ProductDetailViewModel.Screen.HideLoading -> hideLoadingDialog()
                is ProductDetailViewModel.Screen.ShowError -> showError()
                is ProductDetailViewModel.Screen.WhoSawBoughtSuccess -> initWhoSawBoughtAdapter(it.items)
            }
        }
    }

    private fun showError() {
        hideLoadingDialog()
        setErrorVisibility(View.VISIBLE)
    }

    private fun setErrorVisibility(visibility: Int) {
        bind.viewError.clContainerError.visibility = visibility
    }

    private fun initWhoSawBoughtAdapter(items: MutableList<WhoSawBoughtResponse>) {
        setErrorVisibility(View.GONE)
        whoSawBoughtAdapter = WhoSawBoughtAdapter(items)
        bind.viewWhoSawBoughtList.rvItems.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        bind.viewWhoSawBoughtList.rvItems.adapter = whoSawBoughtAdapter
    }

    private fun initAdapter(characteristics: MutableList<MoreInformationValues>) {
        setErrorVisibility(View.GONE)
        adapter = ProductCharacteristicAdapter(characteristics)
        bind.viewProductCharacteristics.rvItems.layoutManager =
            LinearLayoutManager(applicationContext)
        bind.viewProductCharacteristics.rvItems.adapter = adapter
    }
}