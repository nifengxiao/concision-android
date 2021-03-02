package com.creator.concisiondemo.network

import com.creator.concisiondemo.data.model.base.BaseApiBean
import com.creator.concisiondemo.data.model.base.BaseListBean
import com.creator.concisiondemo.data.model.bean.ArticleBean
import retrofit2.http.*

/**
 * @CreateDate:     2021/2/23
 * @Author:         YuanFeng
 * @Description:    网络请求Api
 */
interface ApiService {

    companion object {
        const val SERVER_URL = "https://wanandroid.com/"
    }

    /**
     * 获取首页文章数据
     */
    @GET("article/list/{page}/json")
    suspend fun getArticleList(@Path("page") pageNo: Int): BaseApiBean<BaseListBean<ArticleBean>>


}