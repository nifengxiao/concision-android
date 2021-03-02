package com.creator.concisiondemo.data.model.base

import com.creator.concision.network.BaseResponse

/**
 * @CreateDate:     2021/2/23
 * @Author:         YuanFeng
 * @Description:    服务器返回的基类
 */
data class BaseApiBean<T>(val errorCode: Int, val errorMsg: String, val data: T) :
    BaseResponse<T>() {

    // 这里是示例，wanandroid 网站返回的 错误码为 0 就代表请求成功，请你根据自己的业务需求来改变
    override fun isSuccess() = errorCode == 0

    override fun getResponseCode() = errorCode

    override fun getResponseData() = data

    override fun getResponseMsg() = errorMsg

}