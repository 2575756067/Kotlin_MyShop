package com.example.myshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myshop.R
import com.example.basemvvm.bean.NewGoods
import kotlinx.android.synthetic.main.home_newbrand_item_layout.view.*

class NewBrandAdaoter(private val list: List<NewGoods>, private val mContext: Context?) :
    RecyclerView.Adapter<NewBrandAdaoter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewBrandAdaoter.ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.home_newbrand_item_layout, null)
        return NewBrandAdaoter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NewBrandAdaoter.ViewHolder, position: Int) {
        //todo  赋值
        with(holder ?.itemView!!){
            val bean = list.get(position)
            tv_name_newbrand.text=bean.name
            tv_price_newbrand.text="￥"+bean.retail_price
            if (mContext != null) {
                Glide.with(mContext).load(bean.list_pic_url).into(iv_item_newbrand)
            }
        }
    }

    class ViewHolder(item: View) :RecyclerView.ViewHolder(item)

}
