package com.example.myshop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.basemvvm.bean.topic.TopicBean
import com.example.myshop.base.BaseViewModel
import com.shop.net.Injection
import kotlinx.coroutines.launch

class TopicViewModel : BaseViewModel(Injection.repository) {


    //todo 专题数据
    var dataX: MutableLiveData<List<TopicBean.Data.DataX>> = MutableLiveData()

    fun getTopic(page: Int) {
        //todo  调用数据仓库需要协程产生一个联系
        viewModelScope.launch {
            val result = repository.getTopicData(page)
            if (result.errno == 0) {
                //todo 切换线程
                dataX.postValue(result.data.data)
            } else if (result.errno == 665) {
                //todo 刷新token
                refreshToken
            }
        }
    }
}