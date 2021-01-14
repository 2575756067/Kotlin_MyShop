package com.example.myshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myshop.R
import com.example.basemvvm.bean.Brand
import com.example.myshop.callback.MyItemClick
import kotlinx.android.synthetic.main.home_brand_item1_layout.view.*

class BrandAdapter(private val list: List<Brand>, private val mContext: Context?) :
    RecyclerView.Adapter<BrandAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandAdapter.ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.home_brand_item1_layout, null)
        return BrandAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: BrandAdapter.ViewHolder, position: Int) {
        //TODO  赋值
        with(holder?.itemView!!) {
            val bean = list.get(position)
            brand_item_tv_name.text = bean.name
            brand_item_tv_price.text = "￥" + bean.floor_price + "元起"
            if (mContext != null) {
                Glide.with(mContext).load(bean.new_pic_url).into(brand_item_iv)
            }
        }

        //设置接口
        holder.itemView.setOnClickListener {
            myItemClick!!.onItemCilck(position)
        }
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item)

    //定义条目回调接口
    private var myItemClick: MyItemClick? = null

    fun setOnItemClick(myItemClick: MyItemClick) {
        this.myItemClick = myItemClick
    }

}