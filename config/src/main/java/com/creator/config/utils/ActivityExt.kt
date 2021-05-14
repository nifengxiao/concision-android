package com.creator.config.utils

import com.creator.config.app.base.BaseActivity

/**
 * @CreateDate:     2021/3/29
 * @Author:         Creator
 * @Description:    BaseActivity相关方法
 */
fun BaseActivity<*, *>.loadSirLoading() {
    this.loadSir.showLoading()
}

fun BaseActivity<*, *>.loadSirShowError(error: String = "") {
    this.loadSir.showError(error)
}

fun BaseActivity<*, *>.loadSirShowSuccess() {
    this.loadSir.showSuccess()
}