package com.example.myshop.adapter

import android.content.Context
import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import com.example.basemvvm.BR
import com.example.basemvvm.bean.sort.DataX
import com.example.myshop.R
import com.example.myshop.base.BaseAdapter
import com.example.myshop.base.IItemClick

class SortDataInfoAdapter(
    context: Context,
    list: List<DataX>,
    layouts: SparseArray<Int>,
    var click: IItemClick<DataX>
) : BaseAdapter<DataX>(context, list, layouts, click) {

    override fun layoutId(position: Int): Int {
        return R.layout.layout_sort_data_item
    }

    override fun bindData(binding: ViewDataBinding, data: DataX, layId: Int) {
        binding.setVariable(BR.sort_data_item_click, click)
    }

}