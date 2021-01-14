package com.example.myshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myshop.R
import com.example.basemvvm.bean.Goods
import kotlinx.android.synthetic.main.layout_category_item.view.*


class CategoryAdapter(private val list: List<Goods>, private val mContext: Context?) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){
    class ViewHolder(item: View) :RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_category_item, null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //TODO  赋值
        with(holder?.itemView!!) {
            val bean = list.get(position)
            tv_category_name.text = bean.name
            tv_category_price.text = "￥" + bean.retail_price+"元起"
            if (mContext != null) {
                Glide.with(mContext).load(bean.list_pic_url).into(iv_category_img)
            }
        }
    }

}