package com.creator.concisiondemo.weight.loadCallBack

import com.creator.concisiondemo.R
import com.kingja.loadsir.callback.Callback

/**
 * @CreateDate:     2021/2/23
 * @Author:         YuanFeng
 * @Description:
 */
class ErrorCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_error
    }

}