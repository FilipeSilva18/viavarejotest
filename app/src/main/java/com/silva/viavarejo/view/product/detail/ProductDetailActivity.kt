package com.silva.viavarejo.view.product.detail

import com.silva.viavarejo.R
import com.silva.viavarejo.databinding.ActivityProductDetailBinding
import com.silva.viavarejo.view.base.BaseActivity
import com.silva.viavarejo.view.ui.extensions.observeNotNull
import com.silva.viavarejo.viewmodel.ProductDetailViewModel

class ProductDetailActivity : BaseActivity<ActivityProductDetailBinding, ProductDetailViewModel>() {
    override fun getLayoutId() = R.layout.activity_product_detail

    override fun getViewModelClass() = ProductDetailViewModel::class.java

    override fun init() {
        setSupportActionBar(bind.viewProductDetail.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        bind.viewProductDetail.toolbar.title = "TESTE"
        viewModel.getProductDetail()
        viewModel.initPresentation()
        observeData()
    }

    private fun observeData() {
        viewModel.presentation.observeNotNull(this) {
            bind.presentation = it
        }
        viewModel.screen.observeNotNull(this) {
            when (it) {
                is ProductDetailViewModel.Screen.ShowLoading -> showLoadingDialog()
                is ProductDetailViewModel.Screen.HideLoading -> hideLoadingDialog()
            }
        }
    }
}