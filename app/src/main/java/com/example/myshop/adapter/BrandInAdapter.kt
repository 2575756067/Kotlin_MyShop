package com.shop.adapter.home.brand

import android.content.Context
import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import com.example.basemvvm.BR
import com.example.basemvvm.bean.DataX
import com.example.myshop.R
import com.example.myshop.base.BaseAdapter
import com.example.myshop.base.IItemClick

class BrandInAdapter(context: Context, list: List<DataX>, layouts: SparseArray<Int>, var click: IItemClick<DataX>): BaseAdapter<DataX>(context, list,layouts,click) {
    override fun layoutId(position: Int): Int {
        return R.layout.item_tv_brand

    }

    override fun bindData(binding: ViewDataBinding, data: DataX , layId: Int) {
        binding.setVariable(BR.brand_item_click,click)
//        Glide.with(context).load(data.app_list_pic_url).into(binding.root.findViewById(R.id.img_tvbrand))
    }
    fun refreshData(lt : List<DataX>){
        list = lt
        notifyDataSetChanged()
    }
}