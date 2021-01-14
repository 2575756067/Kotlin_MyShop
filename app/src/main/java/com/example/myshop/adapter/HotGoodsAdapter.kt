package com.example.myshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myshop.R
import com.example.basemvvm.bean.HotGoods
import kotlinx.android.synthetic.main.home_hotgoods_item_layout.view.*

class HotGoodsAdapter(private val list: List<HotGoods>, private val mContext: Context?) :
    RecyclerView.Adapter<HotGoodsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotGoodsAdapter.ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.home_hotgoods_item_layout, null)
        return HotGoodsAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: HotGoodsAdapter.ViewHolder, position: Int) {
        //todo  赋值
        with(holder?.itemView!!) {
            val bean = list.get(position)
            tv_name_item_hotgoods.text = bean.name
            tv_price_item_hotgoods.text = "￥" + bean.retail_price
            tv_brif_item_hotgoods.text = bean.goods_brief
            if (mContext != null) {
                Glide.with(mContext).load(bean.list_pic_url).into(iv_item_hotgoods)
            }
        }
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item)

}