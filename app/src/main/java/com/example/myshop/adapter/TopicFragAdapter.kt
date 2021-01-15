package com.example.myshop.adapter

import android.content.Context
import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import com.example.basemvvm.bean.topic.TopicBean
import com.example.myshop.R
import com.example.myshop.base.BaseAdapter
import com.example.myshop.base.IItemClick

class TopicFragAdapter(
    context: Context,
    list: List<TopicBean.Data.DataX>,
    layouts: SparseArray<Int>,
    var click: IItemClick<TopicBean.Data.DataX>
) : BaseAdapter<TopicBean.Data.DataX>(context, list, layouts, click) {

    override fun layoutId(position: Int): Int {
        return R.layout.layout_topic_item2
    }

    override fun bindData(binding: ViewDataBinding, data: TopicBean.Data.DataX, layId: Int) {

    }


    //刷新适配器
    fun refreshData(lt : List<TopicBean.Data.DataX>){
        list = lt
        notifyDataSetChanged()
    }
}