package com.creator.config.utils

import com.creator.config.app.base.BaseFragment

/**
 * @CreateDate:     2021/3/29
 * @Author:         Creator
 * @Description:    BaseFragment相关方法
 */
fun BaseFragment<*, *>.loadSirLoading() {
    this.loadSir.showLoading()
}

fun BaseFragment<*, *>.loadSirShowError(error: String="") {
    this.loadSir.showError(error)
}

fun BaseFragment<*, *>.loadSirShowSuccess() {
    this.loadSir.showSuccess()
}