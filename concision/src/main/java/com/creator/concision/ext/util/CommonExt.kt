package com.creator.concision.ext.util

import android.content.ClipData
import android.content.Context
import com.creator.concision.R

/**
 * @CreateDate:     2021/1/6 17:09
 * @Author:         hegaojian
 * @Description:    公共扩展不分类 用于存放不知如何分类的函数
 * 1.复制文本到粘贴板
 */

/**
 * 复制文本到粘贴板
 */
fun Context.copyToClipboard(text: String, label: String = R.string.app_name.getResString()) {
    val clipData = ClipData.newPlainText(label, text)
    clipboardManager?.setPrimaryClip(clipData)
}
