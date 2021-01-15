package com.example.basemvvm.bean.topic

data class HomeTopicBean(
    val `data`: Data,
    val errmsg: String,
    val errno: Int
)

data class Data(
    val count: Int,
    val currentPage: Int,
    val `data`: List<DataX>,
    val pageSize: Int,
    val totalPages: Int
)

data class DataX(
    val id: Int,
    val price_info: Int,
    val scene_pic_url: String,
    val subtitle: String,
    val title: String
)