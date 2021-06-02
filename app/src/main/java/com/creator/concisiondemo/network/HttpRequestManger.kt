package com.creator.concisiondemo.network

import com.creator.config.data.model.base.BaseApiBean
import com.creator.config.data.model.base.BaseListBean
import com.creator.concisiondemo.data.model.bean.ArticleBean
import kotlinx.coroutines.Dispatchers
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

//    /**
//     * 获取首页文章数据
//     */
//    suspend fun getHomeData(pageNo: Int): ApiResponse<ApiPagerResponse<ArrayList<AriticleResponse>>> {
//        //同时异步请求2个接口，请求完成后合并数据
//        return withContext(Dispatchers.IO) {
//            val listData = async { apiService.getAritrilList(pageNo) }
//            //如果App配置打开了首页请求置顶文章，且是第一页
//            if (CacheUtil.isNeedTop() && pageNo == 0) {
//                val topData = async { apiService.getTopAritrilList() }
//                listData.await().data.datas.addAll(0, topData.await().data)
//                listData.await()
//            } else {
//                listData.await()
//            }
//        }
//    }
//

    /**
     * 获取首页文章数据
     */
    //suspend 挂起函数修饰符 必须加
    suspend fun getHomeData(pageNo: Int): BaseApiBean<BaseListBean<ArticleBean>> {
        //withContext在切到IO之后，执行完毕会自动回到主线程
        return withContext(Dispatchers.IO) {
            apiService.getArticleList(pageNo)
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