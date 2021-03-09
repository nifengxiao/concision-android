package com.creator.concisiondemo.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.creator.concisiondemo.R
import com.creator.concisiondemo.data.model.bean.MenuBean
import com.creator.concisiondemo.databinding.RcItemMenuBinding

/**
 * @CreateDate:     2021/3/8
 * @Author:         Creator
 * @Description:
 */
class MenuAdapter :
    BaseQuickAdapter<MenuBean, BaseDataBindingHolder<RcItemMenuBinding>>(R.layout.rc_item_menu) {

    override fun convert(holder: BaseDataBindingHolder<RcItemMenuBinding>, item: MenuBean) {
        val binding = holder.dataBinding
        binding?.menu = item
        binding?.card?.setCardBackgroundColor(item.color)
        binding?.executePendingBindings()
    }

}