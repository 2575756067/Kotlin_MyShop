package com.example.myshop.tongpao

import android.os.Handler
import android.util.Log
import android.util.SparseArray
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basemvvm.BR
import com.example.basemvvm.model.bean.tongpao.TongPaoBean
import com.example.myshop.databinding.ActivityManylayoutBinding
import com.shop.base.BaseActivity
import com.example.myshop.R
import com.example.myshop.base.IItemClick
import kotlinx.android.synthetic.main.activity_manylayout.*


class ManyLayoutActivity : BaseActivity<ManyLayoutViewModel, ActivityManylayoutBinding>(
    R.layout.activity_manylayout,
    ManyLayoutViewModel::class.java
) {
    var mAdapter: TongPaoAdapter? = null
    var list: List<TongPaoBean.FilePathList> = arrayListOf()
    var handler = Handler()


    override fun initView() {
        val layoutManager = LinearLayoutManager(this)
        mDataBinding!!.tongpaoRecycle.layoutManager = layoutManager
        //分割线
        tongpao_recycle!!.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
    }

    override fun initVM() {
        mViewModel.stauts.observe(this, Observer {
            mAdapter!!.refreshData(it)
        })
    }

    override fun initData() {
        mViewModel.getMore()

        //封装xml布局界面的id和界面中需要绑定的数据对应的id
        var array = SparseArray<Int>()
        array.put(R.layout.layout_item_noimg, BR.tongpao_item_noImg)
        array.put(R.layout.layout_item_img, BR.tongpao_item_img)
        array.put(R.layout.layout_item_threeimg, BR.tongpao_item_threeimg)
        handler.post {
            mAdapter = TongPaoAdapter(this, list, array,ItemClickImpl())
            mDataBinding!!.tongpaoRecycle.adapter = mAdapter
        }
    }

    override fun initVariable() {

    }

    inner class ItemClickImpl : IItemClick<TongPaoBean.FilePathList> {
        override fun itemClick(data: TongPaoBean.FilePathList) {
            Log.i("TAG", data.title)

        }

    }

}