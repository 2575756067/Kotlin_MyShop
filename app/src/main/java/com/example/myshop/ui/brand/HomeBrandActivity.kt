package com.example.myshop.ui.brand

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myshop.BR
import com.example.myshop.R
import com.example.myshop.databinding.ActivityHomeBrandBinding
import com.example.myshop.viewmodel.HomeBrandViewModel


class HomeBrandActivity : AppCompatActivity() {

    var mBinding: ActivityHomeBrandBinding? = null
    var vm: HomeBrandViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home_brand)
        initVM()

    }

    fun initVM(){
        vm = ViewModelProvider(this).get(HomeBrandViewModel::class.java)
        vm !!.homebrandData()


        //view 层观察者模式
        vm !!.stauts.observe(this, Observer {
            if (it == 0){
                mBinding!!.setVariable(BR.brand,vm)
            }
        })
    }

}