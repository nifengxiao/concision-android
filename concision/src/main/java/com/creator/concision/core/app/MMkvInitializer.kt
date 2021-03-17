package com.creator.concision.core.app

import android.content.Context
import androidx.startup.Initializer
import com.tencent.mmkv.MMKV

/**
 * @CreateDate:     2021/3/5
 * @Author:         Creator
 * @Description:    mmkv初始化
 */
class MMkvInitializer : Initializer<String> {
    override fun create(context: Context): String {
        return MMKV.initialize(context)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return arrayListOf()
    }
}