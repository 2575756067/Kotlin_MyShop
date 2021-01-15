package com.example.myshop.ui

import android.util.Log
import android.util.SparseArray
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.basemvvm.BR
import com.example.basemvvm.bean.sort.SortDataBean
import com.example.myshop.R
import com.example.myshop.adapter.SortCategroysAdapter
import com.example.myshop.base.BaseFragment
import com.example.myshop.base.IItemClick
import com.example.myshop.databinding.SortDataLayoutBinding
import com.example.myshop.viewmodel.SortViewModel
import kotlinx.android.synthetic.main.sort_data_layout.*

class SortCategoryFragment : BaseFragment<SortViewModel, SortDataLayoutBinding>
    (R.layout.sort_data_layout, SortViewModel::class.java) {

    var list: MutableList<SortDataBean.SubCategory> = mutableListOf()
    var sortcateadapter: SortCategroysAdapter? = null


    //采用伴生对象 companion object==static
    companion object {
        val instance: SortCategoryFragment by lazy { SortCategoryFragment() }

    }

    override fun initView() {
        val layoutManager = GridLayoutManager(activity, 3)
        //设置布局管理器
        rlv_sort.layoutManager = layoutManager
        //封装xml布局界面的id和界面中需要绑定的数据对应的id
        var sortDataArr = SparseArray<Int>()
        sortDataArr.put(R.layout.layout_data_item, BR.sort_recy_data)
        //设置适配器
        sortcateadapter = SortCategroysAdapter(context!!, list, sortDataArr, itemClick())
        //绑定适配器
        rlv_sort.adapter = sortcateadapter
    }

    override fun initVM() {
        mViewModel!!.sortdata.observe(this, Observer {
//            sortcateadapter!!.refreshData(it.subCategoryList)
            mDataBinding.setVariable(BR.sort_data, it)
        })
    }

    inner class itemClick : IItemClick<SortDataBean.SubCategory> {
        override fun itemClick(data: SortDataBean.SubCategory) {
            Log.e("TAG::SubCategory", data.name)
        }
    }

    override fun initData() {
        val id = getArguments()?.getInt("id")
        if (id != null) {
            mViewModel.getSortData(id)
        }
    }

    override fun initVariable() {
    }
}
