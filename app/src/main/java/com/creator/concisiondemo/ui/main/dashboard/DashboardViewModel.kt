package com.creator.concisiondemo.ui.main.dashboard

import com.creator.concision.core.viewmodel.BaseViewModel
import com.creator.concision.util.def.databind.StringObservableField

/**
 * @CreateDate:     2021/2/23
 * @Author:         YuanFeng
 * @Description:
 */
class DashboardViewModel : BaseViewModel() {

    var text = StringObservableField("初始值")
}