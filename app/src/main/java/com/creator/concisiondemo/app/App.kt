package com.creator.concisiondemo.app

import androidx.multidex.MultiDex
import com.creator.concision.core.app.BaseApp
import com.creator.concisiondemo.R
import com.creator.concisiondemo.app.sp.AppPreHelper
import com.creator.concisiondemo.data.model.bean.SettingBean
import com.creator.concisiondemo.weight.loadCallBack.EmptyCallback
import com.creator.concisiondemo.weight.loadCallBack.ErrorCallback
import com.creator.concisiondemo.weight.loadCallBack.LoadingCallback
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
class App : BaseApp() {

    companion object {
        lateinit var instance: App
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        //分包
        MultiDex.install(this)
        //初始化基本配置
        AppPreHelper.setting = SettingBean()
        //界面加载管理 初始化
        LoadSir.beginBuilder()
            .addCallback(LoadingCallback())//加载
            .addCallback(ErrorCallback())//错误
            .addCallback(EmptyCallback())//空
            .setDefaultCallback(SuccessCallback::class.java)//设置默认加载状态页
            .commit()
    }

    init {
//        //设置全局默认配置（优先级最低，会被其他设置覆盖）
//        SmartRefreshLayout.setDefaultRefreshInitializer { context, layout -> //开始设置全局的基本参数（可以被下面的DefaultRefreshHeaderCreator覆盖）
//            layout.setReboundDuration(1000)
//            //                layout.setReboundInterpolator(new DropBounceInterpolator());
//            layout.setFooterHeight(100f)
//            layout.setDisableContentWhenLoading(false)
//            layout.setPrimaryColorsId(R.color.colorPrimary)
//        }

        //全局设置默认的 Header
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout -> //开始设置全局的基本参数（这里设置的属性只跟下面的MaterialHeader绑定，其他Header不会生效，能覆盖DefaultRefreshInitializer的属性和Xml设置的属性）
            layout.setEnableHeaderTranslationContent(true)
            MaterialHeader(context).setColorSchemeResources(
                R.color.colorPrimary)
        }

        //全局设置默认的footer
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
            ClassicsFooter(context)
        }
    }

}
