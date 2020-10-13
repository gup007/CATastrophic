package com.zalora.catastrophic

import android.os.Bundle
import androidx.paging.ExperimentalPagingApi
import com.zalora.catastrophic.common.VMBaseActivity
import com.zalora.catastrophic.databinding.ActivityHomeBinding
import com.zalora.catastrophic.home.HomeViewModel
import com.zalora.catastrophic.home.HomeFragment

class HomeActivity : VMBaseActivity<HomeViewModel, ActivityHomeBinding>() {

    private lateinit var viewModel: HomeViewModel
    lateinit var binding: ActivityHomeBinding

    override fun getViewModel(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun getContainerId(): Int {
        return binding.homeContainer.id
    }

    override fun onCreate(
        instance: Bundle?,
        viewModel: HomeViewModel,
        binding: ActivityHomeBinding
    ) {
        this.viewModel = viewModel
        this.binding = binding
        replaceFragment(HomeFragment(), false)
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_home
    }

}
