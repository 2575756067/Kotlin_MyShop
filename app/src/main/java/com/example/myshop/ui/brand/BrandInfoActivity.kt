package com.shop.ui.home.hom

import android.os.Handler
import android.util.Log
import android.util.SparseArray
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basemvvm.BR
import com.example.basemvvm.bean.DataX
import com.example.myshop.R
import com.example.myshop.base.IItemClick
import com.example.myshop.databinding.ActivityBrandInfoBinding
import com.example.myshop.viewmodel.BindBrandViewModel
import com.shop.adapter.home.brand.BrandInAdapter
import com.shop.base.BaseActivity

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BrandInfoActivity : BaseActivity<BindBrandViewModel, ActivityBrandInfoBinding>(
    R.layout.activity_brand_info,
    BindBrandViewModel::class.java
) {

    var mAdapter: BrandInAdapter? = null
    var list: List<DataX> = arrayListOf()

    override fun initView() {
        val layoutManager = LinearLayoutManager(this)
        mDataBinding!!.brandRecycle.layoutManager = layoutManager
    }

    override fun initData() {
        mViewModel.getBrand()


        //封装xml布局界面的id和界面中需要绑定的数据对应的id
        var array = SparseArray<Int>()
        array.put(R.layout.item_tv_brand, BR.brand_item)
//        array.put(R.layout.layout_hotgood_noimage,BR.vmHotGoodNoImage)
        mAdapter = BrandInAdapter(this, list, array, ItemClickImpl())
        mDataBinding!!.brandRecycle.adapter = mAdapter
    }

    override fun initVariable() {

    }

    override fun initVM() {
        mViewModel!!.dataX.observe(this, Observer {
            mAdapter!!.refreshData(it)
        })
    }

    inner class ItemClickImpl : IItemClick<DataX> {
        override fun itemClick(data: DataX) {
            Log.i("TAG", data.name)
        }

    }

}