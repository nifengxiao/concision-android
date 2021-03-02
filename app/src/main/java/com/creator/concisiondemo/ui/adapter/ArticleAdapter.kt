package com.creator.concisiondemo.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.creator.concision.ext.util.toHtml
import com.creator.concisiondemo.R
import com.creator.concisiondemo.data.model.bean.ArticleBean

/**
 * @CreateDate:     2021/2/23
 * @Author:         Creator
 * @Description:    文章适配器
 */
class ArticleAdapter : BaseQuickAdapter<ArticleBean, BaseViewHolder>(R.layout.rc_item_ariticle) {

    override fun convert(holder: BaseViewHolder, item: ArticleBean) {


        //文章布局的赋值
        item.run {
            holder.setText(
                R.id.item_home_author,
                if (author.isNotEmpty()) author else shareUser
            )
            holder.setText(R.id.item_home_content, title.toHtml())
            holder.setText(R.id.item_home_type2, "$superChapterName·$chapterName".toHtml())
            holder.setText(R.id.item_home_date, niceDate)
//            if (showTag) {
                //展示标签
                holder.setGone(R.id.item_home_new, !fresh)
                holder.setGone(R.id.item_home_top, type != 1)
                if (tags.isNotEmpty()) {
                    holder.setGone(R.id.item_home_type1, false)
                    holder.setText(R.id.item_home_type1, tags[0].name)
                } else {
                    holder.setGone(R.id.item_home_type1, true)
                }
//            } else {
//                //隐藏所有标签
//                holder.setGone(R.id.item_home_top, true)
//                holder.setGone(R.id.item_home_type1, true)
//                holder.setGone(R.id.item_home_new, true)
//            }
        }
    }

}