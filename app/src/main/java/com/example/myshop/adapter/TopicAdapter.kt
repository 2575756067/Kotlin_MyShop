package com.example.myshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myshop.R
import com.example.basemvvm.bean.Topic
import kotlinx.android.synthetic.main.home_topic_item_layout.view.*

class TopicAdapter(private val list: List<Topic>, private val mContext: Context?) :
    RecyclerView.Adapter<TopicAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicAdapter.ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.home_topic_item_layout, null)
        return TopicAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TopicAdapter.ViewHolder, position: Int) {
        //todo  赋值
        with(holder?.itemView!!) {
            val bean = list.get(position)
            tv_name_item_topic.text = bean.title
            tv_price_item_topic.text = "￥" + bean.price_info+"元起"
            tv_brig_item_topic.text =bean.subtitle
            if (mContext != null) {
                Glide.with(mContext).load(bean.scene_pic_url).into(iv_item_topic)
            }
        }
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item)
}