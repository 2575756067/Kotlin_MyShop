package com.example.myshop.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myshop.R
import com.example.myshop.adapter.*
import com.example.basemvvm.bean.Banner
import com.example.myshop.callback.MyItemClick
import com.example.myshop.ui.brand.HomeBrandActivity
import com.example.myshop.viewmodel.HomeViewModel
import com.shop.ui.home.fragment.home.HomeChannelTypeActivity
import com.shop.ui.home.hom.BrandInfoActivity
import com.shop.utils.SpUtils
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_channel_item.view.*
import kotlinx.android.synthetic.main.layout_home_item.view.*

class VHomeFragment : Fragment(R.layout.fragment_home) {

    companion object {
        val instance by lazy { VHomeFragment() }
    }

    lateinit var homeVM: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val inflate = layoutInflater.inflate(R.layout.fragment_home, container, false)
        initVm()
        homeVM.loadHomeData()
        return inflate
    }

    private fun initVm() {
        //todo  初始化 viewModel
        homeVM = ViewModelProvider(this).get(HomeViewModel::class.java)

        //监听网络状态的变化
        homeVM.loadStatue.observe(this, Observer { status ->
            if (status == -1) {
                Log.e("TAG", "数据加载失败")
            }
        })

        /**
         * 监听轮播图数据的变化
         */
        homeVM.banner.observe(this, Observer { banner ->
            mybanner!!.setImages(banner).setImageLoader(object : ImageLoader() {
                override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
                    //todo   转换path类型
                    var pic = path as Banner
                    Glide.with(context!!).load(pic.image_url).into(imageView!!)
                }
            }).start()
        })

        /**
         * channel 动态栏 数据
         */
        homeVM.channel.observe(this, Observer { channel ->
            layout_tab.removeAllViews()
            val layoutParams =
                LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f)
            //循环
            for (i in channel.indices) {
                val inflate =
                    LayoutInflater.from(activity).inflate(R.layout.layout_channel_item, null)
                inflate.txt_channel_item.text = channel.get(i).name
                Glide.with(this).load(channel.get(i).icon_url).into(inflate.img_channel)
                inflate.txt_channel_item.gravity = Gravity.CENTER      //todo 文字居中
                inflate.layoutParams = layoutParams       //todo  占位
                layout_tab.addView(inflate)             //todo 添加布局

                //todo  点击事件
                inflate.setOnClickListener {
                    //取值
                    var name = channel!!.get(i).name
                    var url = channel!!.get(i).url
                    val intent = Intent(activity, HomeChannelTypeActivity::class.java)
                    intent.putExtra("name", name)
                    intent.putExtra("url", url)
                    startActivity(intent)
                }
            }
        })

        /**
         * 品牌直供
         */
        homeVM.brend.observe(this, Observer {
            //todo  布局管理器
            brand_recycle.layoutManager = GridLayoutManager(activity, 2)
            var brandAdapter = BrandAdapter(it, context)
            //todo  绑定适配器
            brand_recycle.adapter = brandAdapter


            //条目点击
            brandAdapter!!.setOnItemClick(object : MyItemClick {
                override fun onItemCilck(pos: Int) {
                    val intent = Intent(activity, HomeBrandActivity::class.java)
                    var id = it.get(pos)!!.id
                    SpUtils.instance!!.setValue("id", id)
                    startActivity(intent)
                }
            })

            //todo  点击品牌制造商跳转
            home_tv_brand_name!!.setOnClickListener(View.OnClickListener {
                var intent = Intent(context, BrandInfoActivity::class.java)
                startActivity(intent)
            })

        })

        /**
         * newgoods 新品首发
         */
        homeVM.newGoods.observe(this, Observer { newGoods ->
            newgoods_recycle.layoutManager = GridLayoutManager(activity, 2)
            var newBrandAdaoter = NewBrandAdaoter(newGoods, context)
            //todo  绑定适配器
            newgoods_recycle.adapter = newBrandAdaoter
        })

        /**
         *   定义一个人气推荐
         */
        homeVM.hotGoods.observe(this, Observer {
            hotgoods_recycle.layoutManager = LinearLayoutManager(activity)
            var hotGoodsAdapter = HotGoodsAdapter(it, context)
            hotgoods_recycle.adapter = hotGoodsAdapter
        })

        /**
         *   定义专题精选
         */
        homeVM.topic.observe(this, Observer {
            var manager: LinearLayoutManager = LinearLayoutManager(activity)
            manager.setOrientation(LinearLayoutManager.HORIZONTAL)
            topic_recycle.layoutManager = manager

            var topicAdapter = TopicAdapter(it, context)
            topic_recycle.adapter = topicAdapter
        })

        /**
         *  居家动态栏
         */
        homeVM.category.observe(this, Observer {
            //循环放入布局 布局嵌套布局
            for ((index, value) in it.withIndex()) {
                var inflate =
                    LayoutInflater.from(activity).inflate(R.layout.layout_home_item, null)
                inflate.txt_home_title.text = value.name

                var list = value.goodsList
                inflate.mRlv_home.layoutManager = GridLayoutManager(activity, 2)
                var categoryAdapter = CategoryAdapter(list, context)
                inflate.mRlv_home.adapter = categoryAdapter

                ll.addView(inflate)
            }
        })

    }
}