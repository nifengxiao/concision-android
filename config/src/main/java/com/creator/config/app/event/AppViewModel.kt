package com.creator.config.app.event

import com.creator.concision.core.viewmodel.BaseViewModel
import com.creator.config.data.model.bean.SettingBean
import com.creator.config.data.model.bean.UserBean
import com.kunminx.architecture.ui.callback.UnPeekLiveData

/**
 * @CreateDate:     2021/3/2
 * @Author:         YuanFeng
 * @Description:    AppViewModel 全局相关可在此设置
 */
class AppViewModel : BaseViewModel() {

    //App的账户信息
    var userinfo = UnPeekLiveData<UserBean>()

    //App基本设置信息
    var setting = UnPeekLiveData<SettingBean>()



//    init {
//        //默认值保存的账户信息，没有登陆过则为null
//        userinfo.value = CacheUtil.getUser()
//        //默认值颜色
//        appColor.value = SettingUtil.getColor(appContext)
//        //初始化列表动画
//        appAnimation.value = SettingUtil.getListMode()
//    }

}