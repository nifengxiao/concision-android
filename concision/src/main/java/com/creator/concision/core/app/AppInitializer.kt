package com.creator.concision.core.app

import android.app.Application
import android.content.Context
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.startup.Initializer
import com.creator.concision.core.activity.BaseLifeCycleCallBack

/**
 * @CreateDate:     2021/3/5
 * @Author:         Creator
 * @Description:    简化启动
 */
//全局上下文
val appContext: Application by lazy { AppInitializer.app }

class AppInitializer : Initializer<Application> {

    companion object {
        lateinit var app: Application
        var watchActivityLife = true
        var watchAppLife = true
    }

    override fun create(context: Context): Application {
        install(context.applicationContext as Application)
        return app
    }

    private fun install(application: Application) {
        app = application
        if (watchActivityLife) application.registerActivityLifecycleCallbacks(
            BaseLifeCycleCallBack()
        )
        if (watchAppLife) ProcessLifecycleOwner.get().lifecycle.addObserver(BaseAppLifeObserver)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return arrayListOf()
    }
}