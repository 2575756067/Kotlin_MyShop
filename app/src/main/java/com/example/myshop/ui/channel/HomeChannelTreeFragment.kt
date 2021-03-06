package com.example.myshop.ui.channel

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myshop.R
import com.example.myshop.adapter.HomeTreeAdapter
import com.example.basemvvm.bean.ChannelTreeDataX
import com.example.myshop.viewmodel.HomeChannelTreeViewModel
import com.shop.utils.SpUtils
import kotlinx.android.synthetic.main.fragment_home_channel_tree.*


class HomeChannelTreeFragment : Fragment() {

    lateinit var homeVM: HomeChannelTreeViewModel
    var name:String? = null
    var front_name:String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        name = arguments!!.getString("name")
        front_name = arguments!!.getString("front_name")
        var id = arguments!!.getInt("id")
        SpUtils.instance!!.setValue("id",id)

        val inflate = inflater.inflate(R.layout.fragment_home_channel_tree, container, false)

        initVm()
        homeVM.loadChanneltreeData()
        return inflate

    }

    fun initVm(){

        //初始化ViewModel
        homeVM = ViewModelProvider(this).get(HomeChannelTreeViewModel::class.java)
        //监听网络状态的变化
        homeVM.loadStatue.observe(this, Observer { status ->
            if (status == -1) {
                Log.e("TAG", "HomeChannelTreeFragment: "+"数据加载失败" )
            }
        })

        //监听底部rlv数据的变化
        homeVM.dataX.observe(this, Observer {
            //网格布局
            home_tree_rlv!!.layoutManager = GridLayoutManager(activity, 2)
            //分割线
            home_tree_rlv!!.addItemDecoration(
                DividerItemDecoration(
                    activity,
                    LinearLayout.VERTICAL
                )
            )
            //val 不可变参数
            val data = arrayListOf<ChannelTreeDataX>()
            //添加值
            data.addAll(it)
            //设置适配器
            val homeTreeAdapter = HomeTreeAdapter(data, activity)
            //绑定适配器
            home_tree_rlv!!.adapter = homeTreeAdapter
        })
    }

}