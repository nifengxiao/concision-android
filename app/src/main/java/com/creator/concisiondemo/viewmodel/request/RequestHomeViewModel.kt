package com.creator.concisiondemo.viewmodel.request

import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.creator.concision.core.viewmodel.BaseViewModel
import com.creator.concision.ext.request
import com.creator.config.data.model.base.BaseListBean
import com.creator.concisiondemo.data.model.bean.ArticleBean
import com.creator.concisiondemo.network.HttpRequestCoroutine

/**
 * @CreateDate:     2021/2/23
 * @Author:         YuanFeng
 * @Description:    首页数据
 */
class RequestHomeViewModel : BaseViewModel() {

    //页码 首页数据页码从0开始
    var pageNo = 0

    //是否已经加载成功
    var isLoadingSuccess = false

    //是否是初始化
    var isInit = false

    //首页文章列表数据
    var articleData: MutableLiveData<BaseListBean<ArticleBean>> = MutableLiveData()

    /**
     * 获取首页文章列表数据
     * @param isRefresh 是否是刷新，即第一页
     * @param isInit 是否是初始化
     */
    fun getHomeData(isRefresh: Boolean, isInit: Boolean = false) {
        this.isInit = isInit
        if (isRefresh) {
            if (isLoadingSuccess && isInit) {
                return
            }
            pageNo = 0
        }
        request({ HttpRequestCoroutine.getHomeData(pageNo) }, {
            //请求成功
            pageNo++

            //最后加载的数据
            var datas = arrayListOf<ArticleBean>()
            //成功
            when {
                //第一页并没有数据 显示空布局界面
                it.isFirstEmpty -> {

                }
                //是第一页
                isRefresh -> {
                    //如果已经加载成功以及初始化说明是翻转屏幕
                    if (isLoadingSuccess && isInit) {
                        datas = articleData.value?.datas ?: arrayListOf()
                        LogUtils.i("数据加载-翻转屏幕",datas.size)
                    } else {
                        datas = it.datas
                        LogUtils.i("数据加载-刷新",datas.size)
                    }
                }
                //不是第一页
                else -> {
                    datas = articleData.value?.datas ?: arrayListOf()
                    if (isLoadingSuccess && isInit) {
                        LogUtils.i("数据加载-翻转屏幕",datas.size)
                    }else{
                        datas.addAll(it.datas)
                        LogUtils.i("数据加载-加载",datas.size)
                    }
                }
            }

            isLoadingSuccess = true

            val listDataUiState =
                BaseListBean(
                    isSuccess = true,
                    isRefresh = isRefresh,
                    isEmpty = it.isEmpty,
                    hasMore = it.hasMore,
                    isFirstEmpty = isRefresh && it.isEmpty,
                    datas = datas
                )
            articleData.value = listDataUiState
        }, {

            //最后加载的数据
            val datas = articleData.value?.datas ?: arrayListOf()

            //请求失败
            val listDataUiState =
                BaseListBean(
                    isSuccess = false,
                    errMessage = it.errorMsg,
                    isRefresh = isRefresh,
                    datas = datas
                )
            articleData.value = listDataUiState
        })
    }

}