package com.creator.concisiondemo.network

import com.creator.concisiondemo.data.model.bean.ArticleBean
import com.creator.config.data.model.base.BaseApiBean
import com.creator.config.data.model.base.BaseListBean
import com.creator.config.network.NetworkApi
import com.creator.config.utils.UrlUtils
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import java.util.*

/**
 * @CreateDate:     2021/2/23
 * @Author:         YuanFeng
 * @Description:    公共网络请求配置
 */
//双重校验锁式-单例 封装NetApiService 方便直接快速调用简单的接口
val apiService: CommonApiService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    NetworkApi.INSTANCE.getApi(
        CommonApiService::class.java,
        UrlUtils.getUrlList()[0].url
    )
}

interface CommonApiService {

    /**
     * 获取首页文章数据
     */
    @GET("article/list/{page}/json")
    suspend fun getArticleList(@Path("page") pageNo: Int): BaseApiBean<BaseListBean<ArticleBean>>


    /**
     * 替换地址
     * 用法 初始化的时候请添上
     *
     */
    @Headers("Domain-Name: douban") // Add the Domain-Name header
    @GET("xxx")
    suspend fun xxx(): Observable

}