package com.example.myshop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.basemvvm.bean.*
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class HomeViewModel : ViewModel() {

    // 定义轮播图数据对象
    var banner: MutableLiveData<List<Banner>> = MutableLiveData()

    //定义channel 动态栏数据
    var channel: MutableLiveData<List<Channel>> = MutableLiveData()

    // 定义一个品牌制造商直供
    var brend: MutableLiveData<List<Brand>> = MutableLiveData()

    // 定义一个新品首发
    var newGoods: MutableLiveData<List<NewGoods>> = MutableLiveData()

    // 定义一个人气推荐
    var hotGoods: MutableLiveData<List<HotGoods>> = MutableLiveData()

    // 定义专题精选
    var topic: MutableLiveData<List<Topic>> = MutableLiveData()

    //居家动态栏
    var category: MutableLiveData<List<Category>> = MutableLiveData()

    //网络请求的状态值  -1 网络请求错误
    var loadStatue: MutableLiveData<Int> = MutableLiveData()

    /**
     * 加载首页数据
     */
    fun loadHomeData() {
        GlobalScope.launch {
            loadData()
        }
    }

    suspend fun loadData() {
        var homeData = get("https://cdplay.cn/api/index")
        if (homeData != null) {
            banner.postValue(homeData.data.banner)      // 定义轮播图数据对象
            channel.postValue(homeData.data.channel)    //定义channel 动态栏数据
            brend.postValue(homeData.data.brandList)    // 定义一个品牌制造商直供
            newGoods.postValue(homeData.data.newGoodsList)      // 定义一个新品首发
            hotGoods.postValue(homeData.data.hotGoodsList)    // 定义一个人气推荐
            topic.postValue(homeData.data.topicList)  // 定义专题精选
            category.postValue(homeData.data.categoryList)     //居家动态栏

        } else {
            loadStatue.postValue(-1)
        }
    }

    /**
     * 网络请求
     */
    suspend fun get(str: String) = withContext(Dispatchers.IO) {
        var result = URL(str).readText(charset("utf-8"))
        return@withContext Gson().fromJson<HomeBean>(result, HomeBean::class.java)
    }

}