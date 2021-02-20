package com.creator.concision.core.viewmodel

import androidx.lifecycle.ViewModel
import com.kunminx.architecture.ui.callback.UnPeekLiveData

/**
 * @CreateDate: 2021/1/11
 * @Author: Creator
 * @Description: ViewModel的基类
 * 作用
 * 用于管理页面状态
 * 用法
 * 基于View 每个Activity/Fragment都需要继承这个
 */
open class BaseViewModel : ViewModel() {

    val loadingChange: UiLoadingChange by lazy { UiLoadingChange() }

    /**
     * 显示隐藏加载框
     */
    inner class UiLoadingChange {
        //显示加载框
        val showDialog by lazy { UnPeekLiveData<String>() }

        //隐藏
        val dismissDialog by lazy { UnPeekLiveData<Boolean>() }
    }

}