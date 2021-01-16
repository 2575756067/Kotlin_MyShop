package com.example.myshop.viewmodel.sortviewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.basemvvm.bean.BrotherCategory
import com.example.basemvvm.bean.sort.DataX
import com.example.basemvvm.mvvm.BaseViewModel
import com.example.basemvvm.net.Injection
import kotlinx.coroutines.launch

class SortDataInfoBottomViewModel : BaseViewModel(Injection.repository) {


    //todo 分类下面详情
    var sortdatainfobottom: MutableLiveData<List<DataX>> = MutableLiveData()

    //todo  分类详情数据
    fun getSortInfoItem(id: Int) {
        //TODO 调用数据仓库需要协程产生一个联系
        viewModelScope.launch {
            var result = repository.getSortDataInfoItem(id)
            if (result.errno == 0) {
                sortdatainfobottom.postValue(result.data.data)
            }
        }
    }
}