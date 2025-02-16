package com.creator.concision.ext

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.creator.concision.ext.navigation.NavHostFragment


/**
 * 作者　: hegaojian
 * 时间　: 2020/5/2
 * 描述　:
 */
fun Fragment.nav(): NavController {
    return NavHostFragment.findNavController(this)
}

fun nav(view: View): NavController {
    return Navigation.findNavController(view)
}

var lastNavTime = 0L

/**
 * 防止短时间内多次快速跳转Fragment出现的bug
 * @param resId 跳转的action Id
 * @param bundle 传递的参数
 * @param interval 多少毫秒内不可重复点击 默认0.5秒
 */
fun NavController.navigateAction(resId: Int, bundle: Bundle? = null, interval: Long = 500):NavController {
    val currentTime = System.currentTimeMillis()
    if (currentTime >= lastNavTime + interval) {
        lastNavTime = currentTime
        navigate(resId, bundle)
    }
    return this
}

