package com.example.myshop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.basemvvm.bean.NewGoodsUpBean
import com.example.basemvvm.mvvm.BaseViewModel
import com.example.basemvvm.net.Injection
import kotlinx.coroutines.launch

class NewGoodsUpViewModel : BaseViewModel(Injection.repository) {

    //todo  新品详情数据 上面的数据  img  text
    var NewGoodsUp: MutableLiveData<NewGoodsUpBean.BannerInfo> = MutableLiveData()

    //todo 新品详情数据  上面的数据  img  text
    suspend fun getNewGoodsUpData() {
        //todo  调用数据仓库产生联系
        viewModelScope.launch {
            val result = repository.getNewGoodsUpData()
            if (result.errno == 0) {
                //todo    请求成功  切换线程
                NewGoodsUp.postValue(result.data.bannerInfo)
            }
        }
    }
}