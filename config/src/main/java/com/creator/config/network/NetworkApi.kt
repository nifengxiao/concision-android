package com.creator.config.network

import android.util.Log
import com.creator.concision.core.app.appContext
import com.creator.concision.network.BaseNetworkApi
import com.creator.concision.network.interceptor.CacheInterceptor

import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.util.concurrent.TimeUnit


/**
 * 作者　: hegaojian
 * 时间　: 2019/12/23
 * 描述　: 网络请求构建器，继承BasenetworkApi 并实现setHttpClientBuilder/setRetrofitBuilder方法，
 * 在这里可以添加拦截器，设置构造器可以对Builder做任意操作
 */
class NetworkApi : BaseNetworkApi() {

    companion object {
        val INSTANCE: NetworkApi by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkApi()
        }
    }

    /**
     * 实现重写父类的setHttpClientBuilder方法，
     * 在这里可以添加拦截器，可以对 OkHttpClient.Builder 做任意操作
     */
    override fun setHttpClientBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        builder.apply {
            //设置缓存配置 缓存最大10M
            cache(Cache(File(appContext.cacheDir, "concision"), 10 * 1024 * 1024))
            //示例：添加公共heads 注意要设置在日志拦截器之前，不然Log中会不显示head信息
            addInterceptor(MyHeadInterceptor())
            //添加缓存拦截器 可传入缓存天数，不传默认7天
            addInterceptor(CacheInterceptor())
            // 日志拦截器
            val interceptor =
                HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
                    try {
                        val text: String = URLDecoder.decode(message, "utf-8")
                        Log.e("OKHttp-----", text)
                    } catch (e: UnsupportedEncodingException) {
                        e.printStackTrace()
                        Log.e("OKHttp-----", message)
                    }
                })
            addInterceptor(interceptor)
//            这行必须加 不然默认不打印
            interceptor.level = HttpLoggingInterceptor.Level.BODY;
            //超时时间 连接、读、写
            connectTimeout(10, TimeUnit.SECONDS)
            readTimeout(5, TimeUnit.SECONDS)
            writeTimeout(5, TimeUnit.SECONDS)
        }
        return builder
    }

    /**
     * 实现重写父类的setRetrofitBuilder方法，
     * 在这里可以对Retrofit.Builder做任意操作，比如添加GSON解析器，protobuf等
     */
    override fun setRetrofitBuilder(builder: Retrofit.Builder): Retrofit.Builder {
        return builder.apply {
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        }
    }


}



