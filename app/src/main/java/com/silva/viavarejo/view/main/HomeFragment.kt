package com.silva.viavarejo.view.main

import com.silva.viavarejo.R
import com.silva.viavarejo.databinding.FragmentHomeBinding
import com.silva.viavarejo.view.base.BaseFragment
import com.silva.viavarejo.view.base.BaseViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, BaseViewModel >() {
    companion object{
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override val fragmentLayout = R.layout.fragment_home

    override val viewModelClass = BaseViewModel::class.java

    override fun init() {
        activity?.title = "Home"
    }
}