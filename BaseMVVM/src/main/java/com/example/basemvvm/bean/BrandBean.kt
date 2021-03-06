package com.example.basemvvm.bean


//data class BrandData(
//    val `data`: Data1,
//    val errmsg: String,
//    val errno: Int
//)

data class BrandBean(
    val count: Int,
    val currentPage: Int,
    val `data`: List<DataX>,
    val pageSize: Int,
    val totalPages: Int
)

data class DataX(
    val app_list_pic_url: String,
    val floor_price: String,
    val id: Int,
    val name: String
)