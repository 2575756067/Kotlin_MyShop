package com.example.myshop.tongpao

import android.content.Context
import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import com.example.basemvvm.BR
import com.example.basemvvm.model.bean.tongpao.TongPaoBean
import com.example.myshop.R
import com.example.myshop.base.BaseAdapter
import com.example.myshop.base.IItemClick


//todo 同袍多布局适配器
class TongPaoAdapter(
    context: Context,
    list: List<TongPaoBean.FilePathList>,
    layouts: SparseArray<Int>,
    var clicks: IItemClick<TongPaoBean.FilePathList>

) : BaseAdapter<TongPaoBean.FilePathList>(context, list, layouts,clicks) {

    override fun layoutId(position: Int): Int {

        //todo  多布局判断当前使用有图 无图 多张图
        val imgs = list.get(position).filePathList
        when (imgs.size) {
            0 -> return R.layout.layout_item_noimg
            1 -> return R.layout.layout_item_img
            else -> return R.layout.layout_item_threeimg
        }
    }

//
//    override fun bindData(binding: ViewDataBinding, data: TongPaoBean.FilePathList) {
////        val url = data.filePathList
////        for (i in url.indices) {
////            val length = url.size
////            if (length == 3) {
////                Log.e("111", data.filePathList[0].filePath)
////                Glide.with(context).load(data.filePathList[0].filePath)
////                    .into(binding.root.findViewById(R.id.iv_threeimg_one))
////                Glide.with(context).load(url[1].filePath)
////                    .into(binding.root.findViewById(R.id.iv_threeimg_two))
////                Glide.with(context).load(url[2].filePath)
////                    .into(binding.root.findViewById(R.id.iv_threeimg_three))
////            } else if (length == 1) {
////                Log.e("111", data.filePathList[0].filePath)
////                Glide.with(context).load(url[0].filePath)
////                    .into(binding.root.findViewById(R.id.iv_img_one))
////            }
////        }
//
//    }

    override fun bindData(binding: ViewDataBinding, data: TongPaoBean.FilePathList, layId: Int) {
        when(layId){
            R.layout.layout_item_img -> {
                binding.setVariable(BR.tongpao_item_img_click,itemClick)
            }
            R.layout.layout_item_threeimg -> {
                binding.setVariable(BR.tongpao_item_threeimg_click,clicks)
            }
        }
    }

    //刷新条目的数据
    fun refreshData(lt: List<TongPaoBean.FilePathList>) {
        list = lt
        notifyDataSetChanged()
    }

}