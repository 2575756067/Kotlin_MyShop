package com.example.basemvvm.api

import com.example.basemvvm.bean.*
import com.example.basemvvm.bean.sort.SortDataBean
import com.example.basemvvm.bean.newgoods.HomeNewGoodsBean
import com.example.basemvvm.bean.sort.SortDataInfoBottomData
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
    suspend fun getSortTab(): SortNavBean

    // https://cdplay.cn/api/  用来请求当前分类的列表数据
    @GET("catalog/current")
    suspend fun getSortData(@Query("id") id: Int): SortDataBean

    //分类右边数据点击详情
    @GET("goods/category")
    suspend fun getSortDataInfo(@Query("id") id: Int): BaseResp<SortDataInfoBean>

    //分类右边数据点击详情rlv
    @GET("goods/list?page=1&size=100")
    suspend fun getSortInfoItem(@Query("categoryId") id: Int): BaseResp<SortDataInfoBottomData>

    //专题
    @GET("topic/list")
    suspend fun getTopic(@Query("page") page: Int): TopicBean
}