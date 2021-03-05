package com.creator.config.app.event

import androidx.lifecycle.MutableLiveData
import com.creator.concision.core.viewmodel.BaseViewModel

/**
 * @CreateDate:     2021/3/5
 * @Author:         YuanFeng
 * @Description:    全局通知
 */
class EventViewModel : BaseViewModel() {

    //这里任意定义了一个值来进行测试
    val test = MutableLiveData<String>()

}