package com.example.myshop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.basemvvm.bean.sort.SortDataBean
import com.example.basemvvm.bean.SortNavBean
import com.example.basemvvm.mvvm.BaseViewModel
import com.example.basemvvm.net.Injection
import kotlinx.coroutines.launch

class SortViewModel : BaseViewModel(Injection.repository) {

    //竖导航
    var sornav: MutableLiveData<List<SortNavBean.Category>> = MutableLiveData()

    //数据
    var sortdata: MutableLiveData<SortDataBean.CurrentCategory> = MutableLiveData()


    //todo 获取分类
    fun getSortNav() {
        //todo 调用数据仓库需要协程产生一个联系
        viewModelScope.launch {
            var result = repository.getSortTab()
            if (result.errno == 0) {
                //切换线程
                sornav.postValue(result.data.categoryList)
            } else if (result.errno == 665) {
                //todo 刷新token
                refreshToken
            }
        }
    }

    //获取分类上面的数据
    fun getSortData(id: Int) {
        //TODO 调用数据仓库需要协程产生一个联系
        viewModelScope.launch {
            var result = repository.getSortData(id)
            if (result.errno == 0) {
                //切换线程
                sortdata.postValue(result.data.currentCategory)
            } else if (result.errno == 665) {
                //刷新token
                refreshToken
            }
        }
    }
}