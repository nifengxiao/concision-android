package com.creator.concision.ext.util

import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.creator.concision.core.app.appContext

/**
 * @CreateDate:     2021/1/6 17:02
 * @Author:         hegaojian
 * @Description:    字符串扩展函数
 * @Functions:
 * 1.获取string.xml中字符串
 * 2.字符串转html
 */

/**
 * 获取string.xml中字符串
 */
fun Int.getResString() =
    appContext.getString(this)

/**
 * 字符串转html
 */
fun String.toHtml(): Spanned {
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY);
}

