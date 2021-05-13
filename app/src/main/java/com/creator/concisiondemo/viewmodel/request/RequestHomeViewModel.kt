package com.creator.concisiondemo.viewmodel.request

import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.creator.concision.core.viewmodel.BaseViewModel
import com.creator.concision.ext.request
import com.creator.config.data.model.base.BaseListBean
import com.creator.concisiondemo.data.model.bean.ArticleBean
import com.creator.concisiondemo.network.HttpRequestCoroutine
import com.creator.concisiondemo.network.apiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @CreateDate:     2021/2/23
 * @Author:         YuanFeng
 * @Description:    首页数据
 */
class RequestHomeViewModel : BaseViewModel() {

    //页码 首页数据页码从0开始
    var pageNo = 0

    //首页文章列表数据
    var articleData: MutableLiveData<BaseListBean<ArticleBean>> = MutableLiveData()

    /**
     * 获取首页文章列表数据
     * @param isRefresh 是否是刷新，即第一页
     * @param isInit 是否是初始化
     */
    fun getHomeData(isRefresh: Boolean) {
        if (isRefresh) {
            pageNo = 0
        }
        request({
            withContext(Dispatchers.IO){
                apiService.getArticleList(pageNo)
            }
//            HttpRequestCoroutine.getHomeData(pageNo)
        }, {
            //请求成功
            pageNo++

            //最后加载的数据
            var datas = arrayListOf<ArticleBean>()
            //成功
            when {
                //是第一页
                isRefresh -> {
                    datas = it.datas
                    LogUtils.i("数据加载-刷新", datas.size)
                }
                //不是第一页
                else -> {
                    datas = articleData.value?.datas ?: arrayListOf()
                    datas.addAll(it.datas)
                    LogUtils.i("数据加载-加载", datas.size)
                }
            }

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