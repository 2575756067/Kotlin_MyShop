package com.example.myshop.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.basemvvm.bean.SortNavBean
import com.example.myshop.R
import com.example.myshop.adapter.SortMarqueeAdapter
import com.example.myshop.adapter.SortNavAdapter
import com.example.myshop.base.BaseFragment
import com.example.myshop.databinding.FragmentSortBinding
import com.example.myshop.ui.SortCategoryFragment
import com.example.myshop.viewmodel.SortViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_sort.*
import kotlinx.android.synthetic.main.home_toolbar.view.*

class SortFragment : BaseFragment<SortViewModel, FragmentSortBinding>(
    R.layout.fragment_sort,
    SortViewModel::class.java
) {

    companion object {
        val instance by lazy { SortFragment() }
    }

    override fun initView() {

    }

    override fun initVM() {
        val fragments = ArrayList<SortCategoryFragment>()
        if (!isAdded) return
        mViewModel.sornav.observe(this, Observer { categroy ->
            for (i in categroy.indices) {
                var id = categroy.get(i).id
                var categoryFragment = SortCategoryFragment()
                var bundle = Bundle()
                bundle.putInt("id", id)
                categoryFragment.arguments = bundle
                fragments.add(categoryFragment)
            }
            var sortNavAdapter = SortNavAdapter(childFragmentManager)
            vp_sort.adapter = sortNavAdapter
            sortNavAdapter.addList(fragments, categroy as ArrayList<SortNavBean.Category>)
            sort_tab.setupWithViewPager(vp_sort)
        })

        //跑马灯
        initMarque()
    }

    override fun initData() {
        mViewModel.getSortNav()
    }

    override fun initVariable() {
    }

    //跑马灯
    private fun initMarque() {
        val marqueeViewListOf = mutableListOf<String>()
        marqueeViewListOf.add("商品搜索，共239款好物")
        marqueeViewListOf.add("夏日炎炎")
        marqueeViewListOf.add("第一波福利还有30秒到达战场")
        marqueeViewListOf.add("新用户立领1000元优惠卷")

        var sortMarqueeAdapter = SortMarqueeAdapter(context!!, marqueeViewListOf)
        home_toolbar.marquee_item.setAdapter(sortMarqueeAdapter)

    }
}