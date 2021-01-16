package com.example.myshop.ui.newgoods

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import com.example.myshop.R
import com.example.myshop.base.BaseActivity
import com.example.myshop.databinding.ActivitySortDataInfoBinding
import com.example.myshop.viewmodel.sortviewmodel.SortDataInfoViewModel
import kotlinx.android.synthetic.main.activity_sort_data_info.*

class SortDataInfoActivity : BaseActivity<SortDataInfoViewModel, ActivitySortDataInfoBinding>
    (
    R.layout.activity_sort_data_info,
    SortDataInfoViewModel::class.java
) {

    var id: Int? = null
    var name: String? = null

    override fun initView() {
        id = intent.getIntExtra("id", 0) //todo 点击事件传过来的id
        name = intent.getStringExtra("name").toString()  //标题名字
    }

    override fun initVM() {
        mViewModel.getSortDataInfo(id!!)
    }

    override fun initData() {
        mViewModel.sortdatainfo.observe(this, Observer {
            if (it != null && it.size > 0) {
                //todo  vp fragment
                val fragments = ArrayList<SortDataInfoFragment>()
                for (i in it.indices) {
                    var sortDataInfoFragment =
                        SortDataInfoFragment(it.get(i).id, it.get(i).name, it.get(i).front_name)
                    fragments.add(sortDataInfoFragment)
                }

                //创建适配器
                sort_data_info_vp!!.adapter =
                    object : FragmentStatePagerAdapter(supportFragmentManager) {
                        override fun getCount(): Int {
                            return fragments.size
                        }

                        override fun getItem(position: Int): Fragment {
                            return fragments[position]
                        }

                        override fun getPageTitle(position: Int): CharSequence? {
                            return it[position].name
                        }

                    }
                sort_data_info_tab!!.setupWithViewPager(sort_data_info_vp)

                //点那个跳那个
                for (i in it.indices) {
                    //如果获取的name == 集合的name
                    if (name == it[i].name) {
                        //被选中   select
                        sort_data_info_tab!!.getTabAt(i)!!.select()
                    }
                }
            }
        })
    }

    override fun initVariable() {
    }

    override fun showTips(tips: String) {

    }
}