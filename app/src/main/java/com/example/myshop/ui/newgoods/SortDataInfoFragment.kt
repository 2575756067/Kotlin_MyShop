package com.example.myshop.ui.newgoods

import android.util.Log
import android.util.SparseArray
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.basemvvm.BR
import com.example.basemvvm.bean.sort.DataX
import com.example.myshop.R
import com.example.myshop.adapter.SortDataInfoAdapter
import com.example.myshop.base.BaseFragment
import com.example.myshop.base.IItemClick
import com.example.myshop.databinding.FragmentSortdataInfoBinding
import com.example.myshop.viewmodel.sortviewmodel.SortDataInfoBottomViewModel
import kotlinx.android.synthetic.main.fragment_sortdata_info.*

class SortDataInfoFragment(var Iid: Int, var Iname: String, var Ifront_name: String) :
    BaseFragment<SortDataInfoBottomViewModel, FragmentSortdataInfoBinding>(
        R.layout.fragment_sortdata_info,
        SortDataInfoBottomViewModel::class.java
    ) {
    override fun initView() {
        sort_item_name!!.text = Iname
        sort_item_front_name!!.text = Ifront_name

        sort_item_rlv.layoutManager = GridLayoutManager(context, 2)
    }

    override fun initVM() {
        mViewModel.getSortInfoItem(Iid)
    }

    override fun initData() {
        mViewModel.sortdatainfobottom.observe(this, Observer {
            var sortArr = SparseArray<Int>()
            sortArr.put(R.layout.layout_sort_data_item, BR.sort_data_item)
            var sortdatainfoClick = SortdataInfoClick()
            sort_item_rlv!!.adapter = SortDataInfoAdapter(context!!, it, sortArr, sortdatainfoClick)
        })
    }


    inner class SortdataInfoClick : IItemClick<DataX> {
        override fun itemClick(data: DataX) {
            Log.e("TAG", "itemClick: " + data.name)
        }

    }

    override fun initVariable() {
    }

}