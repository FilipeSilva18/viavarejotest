package com.silva.viavarejo.view.product.list

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.silva.viavarejo.R
import com.silva.viavarejo.data.model.Product
import com.silva.viavarejo.databinding.FragmentProductListBinding
import com.silva.viavarejo.view.base.BaseFragment
import com.silva.viavarejo.view.ui.extensions.initActivity
import com.silva.viavarejo.view.ui.extensions.observeNotNull
import com.silva.viavarejo.view.product.detail.ProductDetailActivity
import com.silva.viavarejo.viewmodel.ProductListViewModel

class ProductListFragment : BaseFragment<FragmentProductListBinding, ProductListViewModel>() {
    companion object {
        fun newInstance(): ProductListFragment {
            return ProductListFragment()
        }
    }

    lateinit var adapter: ProductListAdapter

    override val fragmentLayout = R.layout.fragment_product_list
    override val viewModelClass = ProductListViewModel::class.java

    override fun init() {
        activity?.title = "Produtos"
        viewModel.getProductList()
        observeData()
    }

    private fun observeData() {
        viewModel.screen.observeNotNull(this) {
            when (it) {
                is ProductListViewModel.Screen.ShowLoading -> showLoadingDialog(context)
                is ProductListViewModel.Screen.HideLoading -> hideLoadingDialog()
                is ProductListViewModel.Screen.ShowError -> setErrorVisibility(View.VISIBLE)
                is ProductListViewModel.Screen.SuccessProductList -> initAdapter(it.products)
            }
        }
    }

    private fun initAdapter(products: MutableList<Product>) {
        setErrorVisibility(View.GONE)
        adapter = ProductListAdapter(products)
        adapter.onItemClickListener = object : ProductListAdapter.OnItemClickListener {
            override fun onItemClickListener() {
                activity?.initActivity<ProductDetailActivity>()
            }
        }
        val layoutManager = GridLayoutManager(context, 2)
        bind.rvProducts.layoutManager = layoutManager
        bind.rvProducts.adapter = adapter

    }

    private fun setErrorVisibility(visibility: Int) {
        bind.viewError.clContainerError.visibility = visibility
    }
}