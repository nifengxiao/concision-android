package com.creator.concisiondemo.network.service

import com.creator.config.data.model.base.BaseApiBean
import com.creator.config.data.model.base.BaseListBean
import com.creator.concisiondemo.data.model.bean.ArticleBean
import com.creator.config.network.CommonApiService
import com.creator.config.network.NetworkApi
import retrofit2.http.*

/**
 * @CreateDate:     2021/2/23
 * @Author:         YuanFeng
 * @Description:    文章相关网络请求配置
 */
val articleApiService: ArticleApiService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    NetworkApi.INSTANCE.getApi(ArticleApiService::class.java, CommonApiService.SERVER_URL)
}

interface ArticleApiService {
    /**
     * 获取首页文章数据
     */
    @GET("article/list/{page}/json")
    suspend fun getArticleList(@Path("page") pageNo: Int): BaseApiBean<BaseListBean<ArticleBean>>

}

