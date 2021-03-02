package com.creator.concisiondemo.weight.loadCallBack

import android.content.Context
import android.view.View
import com.creator.concisiondemo.R
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