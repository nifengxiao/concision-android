package com.creator.concisiondemo.app.event

import com.creator.concision.core.viewmodel.BaseViewModel
import com.creator.concisiondemo.data.model.bean.UserInfo
import com.kunminx.architecture.ui.callback.UnPeekLiveData


class AppViewModel : BaseViewModel() {

    //App的账户信息
    var userinfo = UnPeekLiveData<UserInfo>()
//
//    //App主题颜色 中大型项目不推荐以这种方式改变主题颜色，比较繁琐耦合，且容易有遗漏某些控件没有设置主题色
//    var appColor = UnPeekLiveData<Int>()
//
//    //App 列表动画
//    var appAnimation = UnPeekLiveData<Int>()
//
//    init {
//        //默认值保存的账户信息，没有登陆过则为null
//        userinfo.value = CacheUtil.getUser()
//        //默认值颜色
//        appColor.value = SettingUtil.getColor(appContext)
//        //初始化列表动画
//        appAnimation.value = SettingUtil.getListMode()
//    }
}