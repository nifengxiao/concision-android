package com.creator.concisiondemo.utils

import android.widget.TextView
import com.creator.concisiondemo.weight.loadCallBack.EmptyCallback
import com.creator.concisiondemo.weight.loadCallBack.ErrorCallback
import com.creator.concisiondemo.weight.loadCallBack.LoadingCallback
import com.creator.concisiondemo.R
import com.kingja.loadsir.core.LoadService

/**
 * @CreateDate:     2021/2/23
 * @Author:         Creator
 * @Description:
 */


/**
 * 设置错误布局的内容
 */
fun LoadService<*>.setErrorText(message: String) {
    if (message.isNotEmpty()) {
        this.setCallBack(ErrorCallback::class.java) { _, view ->
            view.findViewById<TextView>(R.id.error_text).text = message
        }
    }
}

/**
 * 设置错误布局
 * @param message 错误布局显示的提示内容
 */
fun LoadService<*>.showError(message: String = "") {
    this.setErrorText(message)
    this.showCallback(ErrorCallback::class.java)
}

/**
 * 设置空布局
 */
fun LoadService<*>.showEmpty() {
    this.showCallback(EmptyCallback::class.java)
}

/**
 * 设置加载中
 */
fun LoadService<*>.showLoading() {
    this.showCallback(LoadingCallback::class.java)
}