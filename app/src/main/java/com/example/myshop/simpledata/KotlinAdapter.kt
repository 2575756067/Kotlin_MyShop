package com.example.myshop.simpledata

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myshop.R
import kotlinx.android.synthetic.main.kotlin_item_layout.view.*

class KotlinAdapter(private val list: List<String>, private val mContext: Context) :
    RecyclerView.Adapter<KotlinAdapter.ViewHolder>() {

    //todo  接口回调
    private var KotlinCallback: KotlinCallback? = null
    fun setOncli(KotlinCallback: KotlinCallback) {
        this.KotlinCallback = KotlinCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(mContext).inflate(R.layout.kotlin_item_layout, null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder?.itemView!!) {
            tv_text.text = list.get(position)
        }
        holder.itemView.setOnClickListener { KotlinCallback!!.onClick(position) }
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item)

}