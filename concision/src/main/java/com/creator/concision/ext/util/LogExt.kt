package com.creator.concision.ext.util

import android.util.Log
import com.creator.concision.R

/**
 * @CreateDate:     2021/1/6 17:03
 * @Author:         hegaojian
 * @Description:    日志扩展函数
 */
val TAG = R.string.lib_name.getResString()

/**
 * 控制网络请求日志打印
 */
var isOpenLog = true

private enum class LEVEL {
    V, D, I, W, E
}

fun String.logv(tag: String = TAG) =
    log(LEVEL.V, tag, this)
fun String.logd(tag: String = TAG) =
    log(LEVEL.D, tag, this)
fun String.logi(tag: String = TAG) =
    log(LEVEL.I, tag, this)
fun String.logw(tag: String = TAG) =
    log(LEVEL.W, tag, this)
fun String.loge(tag: String = TAG) =
    log(LEVEL.E, tag, this)


private fun log(level: LEVEL, tag: String, message: String) {
    if (!isOpenLog) return
    when (level) {
        LEVEL.V -> Log.v(tag, message)
        LEVEL.D -> Log.d(tag, message)
        LEVEL.I -> Log.i(tag, message)
        LEVEL.W -> Log.w(tag, message)
        LEVEL.E -> Log.e(tag, message)
    }
}