package com.example.myshop.tongpao

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.basemvvm.model.bean.tongpao.TongPaoBean
import com.example.basemvvm.mvvm.BaseViewModel
import com.example.basemvvm.net.Injection
import kotlinx.coroutines.launch

class ManyLayoutViewModel : BaseViewModel(Injection.repository) {

    //todo 可变实时数据
    var stauts: MutableLiveData<List<TongPaoBean.FilePathList>> = MutableLiveData()

    fun getMore() {
        viewModelScope.launch {
            //todo  调repositroy 中的方法
            var result = repository.getMore()
            if (result.status.statusCode == 100) {//todo  请求成功
                //todo  切换线程
                stauts.postValue(result.data.list)
            }
        }
    }


}