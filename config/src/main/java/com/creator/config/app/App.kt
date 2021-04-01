package com.creator.config.app

import android.content.Context
import androidx.multidex.MultiDex
import com.creator.concision.core.app.BaseApp
import com.creator.config.weight.loadCallBack.EmptyCallback
import com.creator.config.weight.loadCallBack.ErrorCallback
import com.creator.config.weight.loadCallBack.LoadingCallback
import com.example.config.R
import com.kingja.loadsir.callback.SuccessCallback
import com.kingja.loadsir.core.LoadSir
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout

/**
 * @CreateDate:     2021/2/19
 * @Author:         YuanFeng
 * @Description:    Application
 */
abstract class App : BaseApp() {

    companion object {
        lateinit var instance: App
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base);
    }

    override fun onCreate() {
        super.onCreate()
        //分包
        instance = this
        //界面加载管理 初始化
        LoadSir.beginBuilder()
            .addCallback(LoadingCallback())//加载
            .addCallback(ErrorCallback())//错误
            .addCallback(EmptyCallback())//空
            .setDefaultCallback(SuccessCallback::class.java)//设置默认加载状态页
            .commit()
    }

    init {
        //全局设置默认的 Header
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout -> //开始设置全局的基本参数（这里设置的属性只跟下面的MaterialHeader绑定，其他Header不会生效，能覆盖DefaultRefreshInitializer的属性和Xml设置的属性）
            layout.setEnableHeaderTranslationContent(true)
            MaterialHeader(context).setColorSchemeResources(
                R.color.primary)
        }

        //全局设置默认的footer
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ ->
            ClassicsFooter(context)
        }
    }

}
