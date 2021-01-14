package com.example.myshop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.basemvvm.bean.newgoods.HomeNewGoodsBean
import com.example.myshop.base.BaseViewModel
import com.shop.net.Injection
import kotlinx.coroutines.launch

class NewGoodsViewModel : BaseViewModel(Injection.repository) {

    //todo  可变实时数据
    var newgoods: MutableLiveData<HomeNewGoodsBean> = MutableLiveData()

    //todo 获取新品数据列表
    fun getGoodsList(map: HashMap<String, String>) {
        viewModelScope.launch {
            var result = repository.getGoodsList(map)
            newgoods.postValue(result.data)
        }
    }
}