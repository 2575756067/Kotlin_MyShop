package com.example.myshop.viewmodel

import androidx.fragment.app.Fragment
import com.example.myshop.base.BaseViewModel
import com.example.myshop.ui.fragment.MeFragment
import com.example.myshop.ui.fragment.ShopFragment
import com.example.myshop.ui.fragment.SortFragment
import com.example.myshop.ui.fragment.TopicFragment
import com.example.myshop.ui.home.VHomeFragment

import com.shop.net.Injection

class MainViewModel : BaseViewModel(Injection.repository) {

    var fragments: MutableList<Fragment> = mutableListOf()

    init {
        fragments.add(VHomeFragment.instance)
        fragments.add(TopicFragment.instance)
        fragments.add(SortFragment.instance)
        fragments.add(ShopFragment.instance)
        fragments.add(MeFragment.instance)
    }
}