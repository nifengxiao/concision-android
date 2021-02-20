package com.creator.concisiondemo.app

import androidx.multidex.MultiDex
import com.creator.concision.core.app.BaseApp

/**
 * @CreateDate:     2021/2/19
 * @Author:         YuanFeng
 * @Description:    Application
 */
class App : BaseApp() {

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        MultiDex.install(this)
        //界面加载管理 初始化
//        LoadSir.beginBuilder()
//            .addCallback(LoadingCallback())//加载
//            .addCallback(ErrorCallback())//错误
//            .addCallback(EmptyCallback())//空
//            .setDefaultCallback(SuccessCallback::class.java)//设置默认加载状态页
//            .commit()



    }

}
