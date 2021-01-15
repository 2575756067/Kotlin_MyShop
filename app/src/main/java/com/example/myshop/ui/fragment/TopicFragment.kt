package com.example.myshop.ui.fragment

import android.util.Log
import android.util.SparseArray
import android.view.View
import android.widget.ScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basemvvm.BR
import com.example.basemvvm.bean.Topic
import com.example.basemvvm.bean.topic.TopicBean
import com.example.myshop.R
import com.example.myshop.adapter.TopicAdapter
import com.example.myshop.adapter.TopicFragAdapter
import com.example.myshop.base.BaseFragment
import com.example.myshop.base.IItemClick
import com.example.myshop.databinding.FragmentTopicBinding
import com.example.myshop.viewmodel.TopicViewModel
import kotlinx.android.synthetic.main.fragment_topic.*

class TopicFragment : BaseFragment<TopicViewModel, FragmentTopicBinding>(
    R.layout.fragment_topic,
    TopicViewModel::class.java
),
    View.OnClickListener {
    var list: MutableList<TopicBean.Data.DataX> = mutableListOf()
    var topicadapter: TopicFragAdapter? = null
    var page: Int? = null
    val ONE: Int = 1
    val TWO: Int = 2

    //todo 采用半生对象
    companion object {
        val instance by lazy { TopicFragment() }
    }

    override fun initView() {
        //todo 设置布局管理器
        topic_rlv.layoutManager = LinearLayoutManager(activity)
        //todo  封装xml布局界面的id 和界面中需要绑定的数据对应的id
        var TopicArr = SparseArray<Int>()
        TopicArr.put(R.layout.layout_topic_item2, BR.topic_item)
        //设置适配器
        topicadapter = TopicFragAdapter(context!!, list, TopicArr, itemClick())
        //绑定适配器
        mDataBinding!!.topicRlv.adapter = topicadapter

    }

    inner class itemClick : IItemClick<TopicBean.Data.DataX> {
        override fun itemClick(data: TopicBean.Data.DataX) {
            Log.e("TAG::TopicDatax", data.title)
        }
    }

    override fun initVM() {
        mViewModel!!.dataX.observe(this, Observer {
            topicadapter!!.refreshData(it)
            //隐藏加载中...
            lfr_topic_loading!!.visibility = View.GONE
            fr_topic_all!!.visibility = View.GONE
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.topic_btn_shang -> {
                //更换page页
                page = ONE
                //显示加载中...      白版
                lfr_topic_loading!!.visibility = View.VISIBLE
                fr_topic_all!!.visibility = View.VISIBLE
                //请求数据
                mViewModel.getTopic(page!!)
                // 返回顶部
                fr_topic_sc!!.fullScroll(ScrollView.FOCUS_UP)
            }
            R.id.topic_btn_xia -> {
                //更换page页
                page = TWO
                //显示加载中...  白板
                lfr_topic_loading!!.visibility = View.VISIBLE
                fr_topic_all!!.visibility = View.VISIBLE
                //请求数据
                mViewModel.getTopic(page!!)
                // 返回顶部
                fr_topic_sc!!.fullScroll(ScrollView.FOCUS_UP)
            }
        }
    }

    override fun initData() {
        page = ONE
        mViewModel.getTopic(page!!)
    }

    override fun initVariable() {
    }

}