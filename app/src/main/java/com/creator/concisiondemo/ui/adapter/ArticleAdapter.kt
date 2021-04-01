package com.creator.concisiondemo.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.creator.concisiondemo.R
import com.creator.concisiondemo.data.model.bean.ArticleBean
import com.creator.concisiondemo.databinding.RcItemAriticleBinding
import javax.inject.Inject

/**
 * @CreateDate:     2021/2/23
 * @Author:         Creator
 * @Description:    文章适配器
 * 参考 https://github.com/CymChad/BaseRecyclerViewAdapterHelper/blob/master/app/src/main/java/com/chad/baserecyclerviewadapterhelper/adapter/DataBindingAdapter.java
 */
class ArticleAdapter @Inject constructor() :
    BaseQuickAdapter<ArticleBean, BaseDataBindingHolder<RcItemAriticleBinding>>(R.layout.rc_item_ariticle) {

    override fun convert(holder: BaseDataBindingHolder<RcItemAriticleBinding>, item: ArticleBean) {
        val binding = holder.dataBinding
        binding?.articleBean = item
        binding?.executePendingBindings()
    }


}