package com.example.myshop.tongpao

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.basemvvm.model.bean.tongpao.TongPaoBean
import com.google.gson.Gson
import com.shop.base.BaseViewModel
import com.shop.net.Injection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

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