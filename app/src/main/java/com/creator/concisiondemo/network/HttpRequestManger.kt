package com.creator.concisiondemo.network

import com.creator.concisiondemo.data.model.base.BaseApiBean
import com.creator.concisiondemo.data.model.base.BaseListBean
import com.creator.concisiondemo.data.model.bean.ArticleBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

/**
 * @CreateDate:     2021/2/23
 * @Author:         YuanFeng
 * @Description:    处理协程的请求类
 */
val HttpRequestCoroutine: HttpRequestManger by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    HttpRequestManger()
}

class HttpRequestManger {

    /**
     * 获取首页文章数据
     */
    suspend fun getHomeData(pageNo: Int): BaseApiBean<BaseListBean<ArticleBean>> {
        return withContext(Dispatchers.IO) {
            val data = async { apiService.getArticleList(pageNo) }
            data.await()
        }
    }

//    /**
//     * 注册并登陆
//     */
//    suspend fun register(username: String, password: String): BaseApiBean<UserBean> {
//        val registerData = apiService.register(username, password, password)
//        //判断注册结果 注册成功，调用登录接口
//        if (registerData.isSucces()) {
//            return apiService.login(username, password)
//        } else {
//            //抛出错误异常
//            throw AppException(registerData.errorCode, registerData.errorMsg)
//        }
//    }


}