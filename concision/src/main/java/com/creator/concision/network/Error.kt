package com.creator.concision.network

import com.creator.concision.R
import com.creator.concision.ext.util.getResString

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/17
 * 描述　: 错误枚举类
 */
enum class Error(private val code: Int, private val err: String) {

    /**
     * 未知错误
     */
    UNKNOWN(1000, R.string.error_request.getResString()),
    /**
     * 解析错误
     */
    PARSE_ERROR(1001,  R.string.error_parse.getResString()),
    /**
     * 网络错误
     */
    NETWORK_ERROR(1002, R.string.error_connect.getResString()),

    /**
     * 证书出错
     */
    SSL_ERROR(1004, R.string.error_certificate.getResString()),

    /**
     * 连接超时
     */
    TIMEOUT_ERROR(1006, R.string.error_timeout.getResString());

    fun getValue(): String {
        return err
    }

    fun getKey(): Int {
        return code
    }

}