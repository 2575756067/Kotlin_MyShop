package com.example.myshop.ui.newgoods

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.SparseArray
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.basemvvm.BR
import com.example.basemvvm.bean.newgoods.HomeNewGoodsBean
import com.example.myshop.R
import com.example.myshop.base.BaseActivity
import com.example.myshop.base.IItemClick
import com.example.myshop.databinding.ActivityNewgoodsdetailsBinding
import com.example.myshop.viewmodel.NewGoodsViewModel
import com.shop.app.Constants
import kotlinx.android.synthetic.main.activity_newgoodsdetails.*

class NewGoodsActivity(
    var lid: Int = R.layout.activity_newgoodsdetails

) : BaseActivity<NewGoodsViewModel, ActivityNewgoodsdetailsBinding>(
    lid,
    NewGoodsViewModel::class.java
), View.OnClickListener {
    //数据
    var list: MutableList<HomeNewGoodsBean.Goods> = mutableListOf()
    lateinit var newgoodsAdapter: NewGoodsAdapter

    //todo  判断是否是新品
    var isNew = 1       //是新品
    var page = 1
    var size = 100
    var order: String? = null
    var sort: String? = null
    var categoryId = 0

    override fun initView() {
        recy_goodList.layoutManager = GridLayoutManager(this, 2)
        var goodslistArr = SparseArray<Int>()
        goodslistArr.put(R.layout.layout_newgoods_item, BR.good)
        newgoodsAdapter = NewGoodsAdapter(this, list, goodslistArr, itemClick())
        recy_goodList.adapter = newgoodsAdapter


        order = Constants.ASC
        sort = Constants.DEFAULT
        categoryId = 0
        mDataBinding.layoutPrice.setTag(0)
        mDataBinding.txtAll.setTextColor(Color.parseColor("#ff0000"))
        mDataBinding.setVariable(R.layout.activity_newgoodsdetails, BR.good)


        txt_all.setOnClickListener(this)
        txt_price.setOnClickListener(this)
        txt_sort.setOnClickListener(this)

    }


    override fun initVM() {
        mViewModel.newgoods.observe(this, Observer {
            updateGoodList(it.goodsList)
        })
    }

    override fun initData() {
        var map = HashMap<String, String>()
        mViewModel.getGoodsList(map)
    }

    override fun initVariable() {

    }

    override fun showTips(tips: String) {
        TODO("Not yet implemented")
    }

    fun updateGoodList(list: List<HomeNewGoodsBean.Goods>) {
        this.list.clear()
        this.list.addAll(list)
        newgoodsAdapter.notifyDataSetChanged()
    }

    inner class itemClick : IItemClick<HomeNewGoodsBean.Goods> {
        override fun itemClick(data: HomeNewGoodsBean.Goods) {

        }
    }

    /**
     * 组装当前的接口参数
     * @return
     */
    private fun getParam(): HashMap<String, String>? {
        val map = HashMap<String, String>()
        map["isNew"] = isNew.toString()
        map["page"] = page.toString()
        map["size"] = size.toString()
        map["order"] = order!!
        map["sort"] = sort!!
        map["category"] = categoryId.toString()
        return map
    }

    /**
     * 重置条件选择的所有状态
     */
    @SuppressLint("ResourceType")
    private fun resetPriceState() {
        mDataBinding.imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_normal)
        mDataBinding.imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_normal)
        mDataBinding.txtPrice.setTextColor(Color.parseColor(getString(R.color.colorblack)))
        mDataBinding.txtAll.setTextColor(Color.parseColor(getString(R.color.colorblack)))
        mDataBinding.txtSort.setTextColor(Color.parseColor(getString(R.color.colorblack)))
        mDataBinding.layoutPrice.setTag(0)
    }

    /**
     * 按价格升序排序
     */
    @SuppressLint("ResourceType")
    private fun priceStateUp() {
        mDataBinding.imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_select)
        mDataBinding.imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_normal)
        mDataBinding.txtPrice.setTextColor(Color.parseColor(getString(R.color.colorred)))
    }

    /**
     * 价格的降序排列
     */
    @SuppressLint("ResourceType")
    private fun priceStateDown() {
        mDataBinding.imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_normal)
        mDataBinding.imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_select)
        mDataBinding.txtPrice.setTextColor(Color.parseColor(getString(R.color.colorred)))
    }

    /**
     * 界面的点击事件
     */
    inner class ClickEvt {
        fun clickPrice() {
            val tag = mDataBinding.layoutPrice.getTag() as Int
            if (tag == 0) {
                resetPriceState()   //重置条件选择的所有状态
                priceStateUp()      //按价格升序排序
                mDataBinding.layoutPrice.setTag(1)
                order = Constants.ASC
            } else if (tag == 1) {
                resetPriceState()       //重置条件选择的所有状态
                priceStateDown()        // 价格的降序排列
                mDataBinding.layoutPrice.setTag(0)
                order = Constants.DESC
            }
            sort = Constants.PRICE
            getParam()?.let { mViewModel.getGoodsList(it) }
        }

        fun clickAll() {
            resetPriceState()
            mDataBinding.txtAll.setTextColor(Color.parseColor("#ff0000"))
            sort = Constants.DEFAULT
            categoryId = 0
            getParam()?.let { mViewModel.getGoodsList(it) }
        }

        fun clickSort() {
            resetPriceState()
            mDataBinding.txtSort.setTextColor(Color.parseColor("#ff0000"))
            sort = Constants.CATEGORY
            getParam()?.let { mViewModel.getGoodsList(it) }
        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            //todo  监听
            R.id.txt_price -> {
                ClickEvt().clickPrice()
            }
            R.id.txt_all -> {
                ClickEvt().clickAll()
            }
            R.id.txt_sort -> {
                ClickEvt().clickSort()
            }
        }
    }
}