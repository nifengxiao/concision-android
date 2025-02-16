package com.creator.config.network

import com.creator.config.app.cache.UserPreHelper
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import kotlin.jvm.Throws

/**
 *  公共请求参数配置
 */
class ConfigInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("token", "token123456").build()
        builder.addHeader("device", "Android").build()
        builder.addHeader("isLogin", UserPreHelper.isLogin.toString()).build()
        return chain.proceed(builder.build())
    }

}