package com.creator.concisiondemo.ui.main.appviewmodel

import com.creator.concision.core.viewmodel.BaseViewModel
import com.creator.concision.util.def.databind.StringObservableField

/**
 * @CreateDate:     2021/2/23
 * @Author:         YuanFeng
 * @Description:
 */
class AppViewModelViewModel : BaseViewModel() {

//    var text = StringObservableField(AppPreHelper.setting.test)
    var text = StringObservableField()
}