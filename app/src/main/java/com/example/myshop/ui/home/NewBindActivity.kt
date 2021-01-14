package com.example.myshop.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myshop.BR
import com.example.myshop.R
import com.example.myshop.viewmodel.NewBindViewModel
import com.example.myshop.databinding.HomeBrandItemLayoutBinding

class NewBindActivity : AppCompatActivity() {

    var mBinding: HomeBrandItemLayoutBinding? = null
    var vm: NewBindViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.home_brand_item_layout)
        initVM()
    }

    fun initVM() {
        vm = ViewModelProvider(this).get(NewBindViewModel::class.java)
        vm!!.homeData()
        vm!!.status.observe(this, Observer {
            if (it == 0) {
                mBinding!!.setVariable(BR.bindbrand, vm)
            }
        })
    }
}