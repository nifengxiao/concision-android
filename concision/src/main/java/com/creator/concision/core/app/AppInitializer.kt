package com.creator.concision.core.app

import android.app.Application
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.startup.Initializer
import com.creator.concision.core.activity.BaseLifeCycleCallBack
import com.creator.concision.network.manager.NetworkStateReceive

/**
 * @CreateDate:     2021/3/5
 * @Author:         Creator
 * @Description:    简化启动
 */
val appContext: Application by lazy { AppInitializer.app }

class AppInitializer : Initializer<Application> {

    companion object {
        lateinit var app: Application
        private var mNetworkStateReceive: NetworkStateReceive? = null
        var watchActivityLife = true
        var watchAppLife = true
    }

    override fun create(context: Context): Application {
        install(context.applicationContext as Application)
        return app
    }

    private fun install(application: Application) {
        app = application
        mNetworkStateReceive = NetworkStateReceive()
        app.registerReceiver(
            mNetworkStateReceive,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        if (watchActivityLife) application.registerActivityLifecycleCallbacks(
            BaseLifeCycleCallBack()
        )
        if (watchAppLife) ProcessLifecycleOwner.get().lifecycle.addObserver(BaseAppLifeObserver)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return arrayListOf()
    }
}