package com.creator.config.app.event

import androidx.lifecycle.MutableLiveData
import com.creator.concision.core.viewmodel.BaseViewModel
import com.creator.config.app.sp.AppPreHelper
import com.creator.config.data.model.bean.SettingBean
import com.creator.config.data.model.bean.UserBean

/**
 * @CreateDate:     2021/3/2
 * @Author:         YuanFeng
 * @Description:    AppViewModel 全局相关可在此设置
 */
class AppViewModel : BaseViewModel() {

    //App的账户信息
    var userinfo = MutableLiveData<UserBean>()

    //App基本设置信息
//    var setting = UnPeekLiveData<SettingBean>()
    var setting = MutableLiveData<SettingBean>()

    init {
        setting.value = AppPreHelper.setting
    }

}