package com.creator.config.network


/**
 * @CreateDate:     2021/2/23
 * @Author:         YuanFeng
 * @Description:    公共网络请求配置
 */
//双重校验锁式-单例 封装NetApiService 方便直接快速调用简单的接口
val commonApiService: CommonApiService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    NetworkApi.INSTANCE.getApi(CommonApiService::class.java, CommonApiService.SERVER_URL)
}

interface CommonApiService {

    companion object {
        const val SERVER_URL = "https://wanandroid.com/"
    }

}