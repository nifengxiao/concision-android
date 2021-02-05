package com.creator.concision.core.viewmodel

import androidx.lifecycle.ViewModel
import com.kunminx.architecture.ui.callback.UnPeekLiveData

/**
 * @CreateDate: 2021/1/11
 * @Author: Creator
 * @Description: ViewModel的基类
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