package com.silva.viavarejo.view.main

import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.silva.viavarejo.R
import com.silva.viavarejo.databinding.ActivityMainBinding
import com.silva.viavarejo.view.base.BaseActivity
import com.silva.viavarejo.view.base.BaseViewModel
import com.silva.viavarejo.view.product.list.ProductListFragment

class MainActivity : BaseActivity<ActivityMainBinding, BaseViewModel>() {

    private var active: Fragment = HomeFragment.newInstance()


    override fun getLayoutId() = R.layout.activity_main

    override fun getViewModelClass() = BaseViewModel::class.java

    override fun init() {
        setSupportActionBar(bind.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        val toggle = ActionBarDrawerToggle(
            this,
            bind.dlContainer,
            bind.toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )
        bind.dlContainer.addDrawerListener(toggle)
        toggle.syncState()

        bind.navView.setNavigationItemSelectedListener {
            onNavigationItemSelected(it)
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, active, HomeFragment::class.java.simpleName)
            .commitNow()
    }


    private fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.MenuItemHome -> {
                active = HomeFragment()

                replaceFragment(HomeFragment.newInstance())
            }
            R.id.MenuItemProducts -> {
                replaceFragment(ProductListFragment.newInstance())
            }
        }
        return true
    }

    private fun replaceFragment(fragment: Fragment) {
        active = fragment
        bind.dlContainer.closeDrawers()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, active, ProductListFragment::class.java.simpleName)
            .commitNowAllowingStateLoss()
    }
}
