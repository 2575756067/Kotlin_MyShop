package com.example.myshop.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.basemvvm.bean.SortNavBean
import com.example.myshop.ui.SortCategoryFragment


class SortNavAdapter(fm:FragmentManager):FragmentStatePagerAdapter(fm){

    private var arrayList = ArrayList<SortCategoryFragment>()
    private var category = ArrayList<SortNavBean.Category>()

    fun addList(arrayList:ArrayList<SortCategoryFragment>, category:ArrayList<SortNavBean.Category>){
        this.arrayList = arrayList
        this.category = category
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(position: Int): Fragment {
        return arrayList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return category[position]?.name
    }

}