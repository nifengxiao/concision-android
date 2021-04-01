package com.creator.concisiondemo.ui.databinding

import com.creator.concision.core.viewmodel.BaseViewModel
import com.creator.concision.util.def.databind.IntObservableField
import com.creator.concision.util.def.databind.StringObservableField

/**
 * @CreateDate:     2021/2/23
 * @Author:         YuanFeng
 * @Description:
 */
class DataBindingViewModel : BaseViewModel() {

    var price = StringObservableField("100")

    var progress = IntObservableField(0)


}