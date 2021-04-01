package com.creator.config.network

import com.blankj.utilcode.util.LogUtils
import com.example.config.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException
import java.nio.charset.Charset

/**
 * @CreateDate:     2021/3/31
 * @Author:         Creator
 * @Description:    解密
 */
class ResponseDecryptInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        var response = chain.proceed(request)

        if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                /*开始解密*/
                try {
                    val source = responseBody.source()
                    source.request(java.lang.Long.MAX_VALUE)
                    val buffer = source.buffer()
                    var charset = Charset.forName("UTF-8")
                    val contentType = responseBody.contentType()
                    if (contentType != null) {
                        charset = contentType.charset(charset)
                    }
                    val bodyString = buffer.clone().readString(charset)
                    val responseData = if (BuildConfig.IS_ENCRYPT) {
                        //TODO 解密
                        bodyString
                    } else {
                        bodyString
                    }
                    /*将解密后的明文返回*/
                    val newResponseBody = ResponseBody.create(contentType, responseData.trim())
                    response = response.newBuilder().body(newResponseBody).build()
                } catch (e: Exception) {
                    /*异常说明解密失败 信息被篡改 直接返回即可 */
                    LogUtils.e("解密异常====》${e}")
                    return response
                }
            } else {
                LogUtils.i("响应体为空")
            }
        }
        return response

    }
}
