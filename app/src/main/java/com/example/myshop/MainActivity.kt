package com.example.myshop

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.myshop.base.BaseActivity
import com.example.myshop.databinding.ActivityMainBinding
import com.example.myshop.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(
    R.layout.activity_main,
    MainViewModel::class.java
) {

    var pagerAdapter: MyViewpageAdapter? = null
    override fun initView() {
        //todo  viewpager 适配器
        pagerAdapter = MyViewpageAdapter(supportFragmentManager, mViewModel.fragments)
        main_vp.adapter = pagerAdapter
        main_vp.addOnPageChangeListener(MyPagerChangeListener())

        nav_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    it.setIcon(R.mipmap.ic_menu_choice_pressed)
                    main_vp.currentItem = 0
                }
                R.id.navigation_topic -> {
                    it.setIcon(R.mipmap.ic_menu_topic_pressed)
                    main_vp.currentItem = 1
                }
                R.id.navigation_sort -> {
                    it.setIcon(R.mipmap.ic_menu_sort_pressed)
                    main_vp.currentItem = 2
                }
                R.id.navigation_shop -> {
                    it.setIcon(R.mipmap.ic_menu_shoping_pressed)
                    main_vp.currentItem = 3
                }
                R.id.navigation_me -> {
                    it.setIcon(R.mipmap.ic_menu_me_pressed)
                    main_vp.currentItem = 4
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
    }

    override fun initVM() {
    }

    override fun initData() {
    }

    override fun initVariable() {
    }

    override fun showTips(tips: String) {
        TODO("Not yet implemented")
    }


    /**
     * innner修饰的内部类访问外部类
     */
    inner class MyPagerChangeListener : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {

        }

        override fun onPageSelected(position: Int) {
            nav_view.getMenu().getItem(position).setChecked(true)
        }

        override fun onPageScrollStateChanged(state: Int) {
        }

    }

    //todo viewpager 适配器
    class MyViewpageAdapter(val fm: FragmentManager, val fragmentslist: List<Fragment>) :
        FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return fragmentslist.get(position)
        }

        override fun getCount(): Int {
            return fragmentslist.size
        }

    }
}

