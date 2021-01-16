package com.example.basemvvm.net.repository

import com.example.basemvvm.api.ServiceApi
import com.shop.net.RetrofitFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 数据仓库
 * 用来连接ViewModel和Model
 * 定义业务相关的网络请求接口的api
 */
class SystemRepository {

    private lateinit var serviceApi: ServiceApi

    //初始化的方法
    init {
        serviceApi = RetrofitFactory.instance.create(ServiceApi::class.java)
    }

    /**
     * 刷新token
     */
    suspend fun refreshToken() = withContext(Dispatchers.IO) {
        serviceApi.refreshToken()
    }


    /**
     * 获取主页数据
     */
    suspend fun getHome() = withContext(Dispatchers.IO) {
        serviceApi.getHome()
    }

    /**
     * 获取专题数据
     */
    suspend fun getTopic() = withContext(Dispatchers.IO) {
        serviceApi.getTopic()
    }

    /**
     * 同袍多布局
     */
    suspend fun getMore() = withContext(Dispatchers.IO) {
        serviceApi.getMore()
    }

    //todo 专题
    suspend fun getBrand() = withContext(Dispatchers.IO) {
        serviceApi.getBrand()
    }

    //todo  新品
    suspend fun getGoodsList(map: HashMap<String, String>) = withContext(Dispatchers.IO) {
        serviceApi.getNewGoods(map)
    }

    //todo 新品详情数据上面的 img，text
    suspend fun getNewGoodsUpData() = withContext(Dispatchers.IO) {
        serviceApi.getNewGoodsUp()
    }

    suspend fun getSortTab() = with(Dispatchers.IO) {
        serviceApi.getSortTab()
    }

    suspend fun getSortData(id: Int) = with(Dispatchers.IO) {
        serviceApi.getSortData(id)
    }

    suspend fun getTopicData(page: Int) = with(Dispatchers.IO) {
        serviceApi.getTopic(page)
    }

    suspend fun getSortDataInfo(id: Int) = with(Dispatchers.IO) {
        serviceApi.getSortDataInfo(id)
    }

    suspend fun getSortDataInfoItem(id: Int) = with(Dispatchers.IO) {
        serviceApi.getSortInfoItem(id)
    }
}