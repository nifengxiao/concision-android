package com.creator.config.network

import com.blankj.utilcode.util.LogUtils
import com.example.config.BuildConfig
import okhttp3.Interceptor
import okhttp3.RequestBody
import okhttp3.Response
import okio.Buffer
import java.io.IOException
import java.net.URLDecoder
import java.nio.charset.Charset

/**
 * @CreateDate:     2021/3/31
 * @Author:         Creator
 * @Description:    加密
 */
class RequestEncryptInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        var charset = Charset.forName("UTF-8")
        val method = request.method().toLowerCase().trim()
        val url = request.url()

        /*如果请求方式是Get或者Delete，此时请求数据是拼接在请求地址后面的*/
        if (method == "get" || method == "delete") {
            /*如果有请求数据 则加密*/
            if (url.encodedQuery() != null) {
                try {
                    val queryparamNames = request.url().encodedQuery()
                    val newUrl = if (BuildConfig.IS_ENCRYPT) {
                        //TODO 加密
                        val encryptqueryparamNames = queryparamNames+"asdasdasd"
                        //拼接加密后的url，参数字段自己跟后台商量，这里我用param，后台拿到数据先对param进行解密，解密后的数据就是请求的数据
                        "${url}?param=$encryptqueryparamNames"
                    } else {
                        queryparamNames
                    }
                    //构建新的请求
                    request = request.newBuilder().url(newUrl!!).build()
                } catch (e: Exception) {
                    e.printStackTrace()
                    return chain.proceed(request)
                }
            }
        } else {
            //不是Get和Delete请求时，则请求数据在请求体中
            val requestBody = request.body()

            /*判断请求体是否为空  不为空则执行以下操作*/
            if (requestBody != null) {
                val contentType = requestBody.contentType()
                if (contentType != null) {
                    charset = contentType.charset(charset)
                    /*如果是二进制上传  则不进行加密*/
                    if (contentType.type().toLowerCase().equals("multipart")) {
                        return chain.proceed(request)
                    }
                }
                /*获取请求的数据*/
                try {
                    val buffer = Buffer()
                    requestBody.writeTo(buffer)
                    val requestData = URLDecoder.decode(buffer.readString(charset).trim(), "utf-8")
                    val encryptData = if (BuildConfig.IS_ENCRYPT) {
                        //TODO 加密
                        requestData + ""
                    } else {
                        requestData
                    }
                    /*构建新的请求体*/
                    val newRequestBody = RequestBody.create(contentType, encryptData)

                    /*构建新的requestBuilder*/
                    val newRequestBuilder = request.newBuilder()
                    //根据请求方式构建相应的请求
                    when (method) {
                        "post" -> newRequestBuilder.post(newRequestBody)
                        "put" -> newRequestBuilder.put(newRequestBody)
                    }
                    request = newRequestBuilder.build()

                } catch (e: Exception) {
                    LogUtils.e("加密异常====》${e}")
                    return chain.proceed(request)
                }
            }
        }
        return chain.proceed(request)
    }
}
