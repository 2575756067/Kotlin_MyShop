package com.example.myshop.adapter

import android.content.Context
import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import com.example.basemvvm.BR
import com.example.basemvvm.bean.sort.SortDataBean
import com.example.myshop.R
import com.example.myshop.base.BaseAdapter
import com.example.myshop.base.IItemClick

class SortCategroysAdapter(
    context: Context,
    list:List<SortDataBean.SubCategory>,
    layouts: SparseArray<Int>,
    var click: IItemClick<SortDataBean.SubCategory>
)
    : BaseAdapter<SortDataBean.SubCategory>(context,list,layouts,click) {

    override fun layoutId(position: Int): Int {
        return R.layout.layout_data_item
    }

    override fun bindData(binding: ViewDataBinding, data: SortDataBean.SubCategory, layId: Int) {
        binding.setVariable(BR.sort_recy_click,click)
    }

    //刷新适配器
    fun refreshData(lt : List<SortDataBean.SubCategory>){
        list = lt
        notifyDataSetChanged()
    }

}