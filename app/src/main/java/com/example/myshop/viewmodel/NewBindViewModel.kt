package com.example.myshop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.basemvvm.bean.HomeBean
import com.google.gson.Gson

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class NewBindViewModel:ViewModel() {

    var status:MutableLiveData<Int> = MutableLiveData()
    var img:String? = "img"
    var name:String? = "name"
    var price:String? = "price"

    fun homeData(){
        GlobalScope.launch {
            loadData()
        }
    }

    suspend fun loadData(){
        var homeData = get("https://cdplay.cn/api/index")
        if(homeData != null){
            name = homeData.data.hotGoodsList.get(0).name
            price = homeData.data.hotGoodsList.get(0).retail_price
            status.postValue(0)
        }
    }

    /**
     * 网络请求
     */
    suspend fun get(str:String) = withContext(Dispatchers.IO){
        var result = URL(str).readText(charset("utf-8"))
        return@withContext Gson().fromJson<HomeBean>(result, HomeBean::class.java)
    }



}