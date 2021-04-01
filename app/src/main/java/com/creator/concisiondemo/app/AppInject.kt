package com.creator.concisiondemo.app

import com.creator.config.app.App
import dagger.hilt.android.HiltAndroidApp

/**
 * @CreateDate:     2021/3/18
 * @Author:         Creator
 * @Description:    由于跨模块使用hilt存在bug，所以这里单独继承使用只是为了注入application，不处理逻辑
 */
@HiltAndroidApp
class AppInject : App() {

}