package com.creator.config.utils

import androidx.databinding.ViewDataBinding
import com.creator.concision.core.viewmodel.BaseViewModel
import com.creator.config.app.base.BaseFragment

/**
 * @CreateDate:     2021/3/29
 * @Author:         Creator
 * @Description:
 */
fun BaseFragment<BaseViewModel, ViewDataBinding>.loading(){
    this.loadSir.showLoading()
}