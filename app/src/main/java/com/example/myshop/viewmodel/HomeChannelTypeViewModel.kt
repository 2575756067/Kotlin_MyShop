package com.shop.viewmodel.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.basemvvm.bean.ChannelTypeCategory
import com.example.basemvvm.bean.HomeChannelTypeData
import com.google.gson.Gson

import com.shop.utils.SpUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class HomeChannelTypeViewModel: ViewModel() {

    //分类居家
    //https://cdplay.cn/api/catalog/index/pages/category/category?id=1005000
    //https://cdplay.cn/api/catalog/index?mur=url(传过来的url)

    // 定义tablayout  vp数据对象
    var category:MutableLiveData<List<ChannelTypeCategory>> = MutableLiveData()

    //网络请求的状态值  -1 网络请求错误
    var loadStatue:MutableLiveData<Int> = MutableLiveData()

    //TODO 加载数据
    fun loadChanneltypeData(){
        GlobalScope.launch {
            loadData()
        }
    }

    suspend fun loadData(){
        val url = SpUtils.instance!!.getStrring("murl")
        var homechanneltypeData = get("https://cdplay.cn/api/catalog/index?mur="+url)
        if(homechanneltypeData != null){
            category.postValue(homechanneltypeData.data.categoryList)
        }else{
            loadStatue.postValue(-1)
        }
    }

    //网络请求
    suspend fun get(str:String) = withContext(Dispatchers.IO){
        var result = URL(str).readText(charset("utf-8"))
        return@withContext Gson().fromJson(result, HomeChannelTypeData::class.java)
    }

}