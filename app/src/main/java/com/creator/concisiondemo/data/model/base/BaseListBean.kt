package com.creator.concisiondemo.data.model.base

/**
 * @CreateDate:     2021/2/23
 * @Author:         YuanFeng
 * @Description:    列表数据封装
 */
data class BaseListBean<T>(
    //是否请求成功
    val isSuccess: Boolean,
    //错误消息 isSuccess为false才会有
    val errMessage: String = "",
    //是否为刷新
    val isRefresh: Boolean = false,
    //是否为空
    val isEmpty: Boolean = false,
    //是否还有更多
    val hasMore: Boolean = false,
    //是第一页且没有数据
    val isFirstEmpty: Boolean = false,
    //列表数据
    val datas: ArrayList<T> = arrayListOf()
)