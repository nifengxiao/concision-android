package com.creator.config.weight.loadCallBack

import android.content.Context
import android.view.View
import com.example.config.R
import com.kingja.loadsir.callback.Callback

/**
 * @CreateDate:     2021/2/23
 * @Author:         YuanFeng
 * @Description:
 */
class LoadingCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_loading
    }

    override fun onReloadEvent(context: Context?, view: View?): Boolean {
        return true
    }
}