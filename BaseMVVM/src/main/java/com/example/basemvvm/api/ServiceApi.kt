package com.example.basemvvm.api

import com.example.basemvvm.bean.BrandBean
import com.example.basemvvm.bean.HomeBean
import com.example.basemvvm.bean.newgoods.HomeNewGoodsBean
import com.example.basemvvm.bean.sort.SortTabBean
import com.example.basemvvm.bean.topic.TopicBean
import com.example.basemvvm.model.bean.tongpao.TongPaoBean
import com.shop.net.BaseResp
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ServiceApi {

    @GET("index")
    suspend fun getHome(): BaseResp<HomeBean>   // BaseResp抽取的一个bean类的外层结构 homeData当前接口返回的具体

    @POST("auth/refreshToken")  //刷新token
    suspend fun refreshToken(): BaseResp<String>

    //专题
    @GET("topic/list?page=1&size=10")
    suspend fun getTopic(): BaseResp<TopicBean>

    //品牌制造
    @GET("brand/list")
    suspend fun getBrand(): BaseResp<BrandBean>

    //同袍多布局
    @GET("discover/hot.json")
    suspend fun getMore(): TongPaoBean

    // 新品详情
    @GET("goods/list")
    suspend fun getNewGoods(@QueryMap map: HashMap<String, String>): BaseResp<HomeNewGoodsBean>

    //https://cdplay.cn/api/catalog/index 分类竖着导航
    @GET("catalog/index")
    suspend fun getSortTab(): SortTabBean

    // https://cdplay.cn/api/  用来请求当前分类的列表数据
    @GET("catalog/current")
    suspend fun getSortData(@Query("id") id: Int): SortTabBean
}