package com.creator.concisiondemo.ui.main.event

import com.creator.concision.core.viewmodel.BaseViewModel
import com.creator.concision.util.def.databind.StringObservableField

/**
 * @CreateDate:     2021/2/23
 * @Author:         YuanFeng
 * @Description:
 */
class EventViewModel : BaseViewModel() {

    var text = StringObservableField("点击改变值")
}