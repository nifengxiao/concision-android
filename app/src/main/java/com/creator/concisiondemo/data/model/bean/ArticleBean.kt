package com.creator.concisiondemo.data.model.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import com.blankj.utilcode.util.StringUtils
import kotlinx.android.parcel.Parcelize

/**
 * @CreateDate:     2021/2/23
 * @Author:         YuanFeng
 * @Description:    文章
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class ArticleBean(
    var apkLink: String,
    var author: String,//作者
    var chapterId: Int,
    var chapterName: String,
    var collect: Boolean,//是否收藏
    var courseId: Int,
    var desc: String,
    var envelopePic: String,
    var fresh: Boolean,
    var id: Int,
    var link: String,
    var niceDate: String,
    var origin: String,
    var prefix: String,
    var projectLink: String,
    var publishTime: Long,
    var superChapterId: Int,
    var superChapterName: String,
    var shareUser: String,
    var tags: List<TagsBean>,
    var title: String,
    var type: Int,
    var userId: Int,
    var visible: Int,
    var zan: Int
) : Parcelable {

    /**
     * 用于显示的作者
     */
    fun showAuthor(): String {
        return if (StringUtils.isEmpty(author)) {
            author
        } else {
            shareUser
        }
    }


}
