package com.example.myshop.ui.newgoods


import android.content.Context
import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import com.example.basemvvm.bean.newgoods.HomeNewGoodsBean
import com.example.myshop.R
import com.example.myshop.base.BaseAdapter
import com.example.myshop.base.IItemClick


class NewGoodsAdapter(
    context: Context,
    list: List<HomeNewGoodsBean.Goods>,
    layouts: SparseArray<Int>,
    click: IItemClick<HomeNewGoodsBean.Goods>
) : BaseAdapter<HomeNewGoodsBean.Goods>(context, list, layouts, click) {
    override fun layoutId(position: Int): Int {
        return R.layout.layout_newgoods_item
    }

    override fun bindData(binding: ViewDataBinding, data: HomeNewGoodsBean.Goods, layId: Int) {
    }


}