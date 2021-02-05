package com.creator.concision.ext.util

import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.annotation.RequiresApi
import com.creator.concision.core.app.appContext

/**
 * @CreateDate:     2021/1/6 17:02
 * @Author:         Creator
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
@RequiresApi(Build.VERSION_CODES.N)
fun String.toHtml(flag: Int = Html.FROM_HTML_MODE_LEGACY): Spanned {
    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        Html.fromHtml(this, flag)
    } else {
        Html.fromHtml(this)
    }
}

