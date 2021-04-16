package com.creator.concisiondemo.ui.test

import com.creator.concision.core.viewmodel.BaseViewModel
import com.creator.concision.util.def.databind.StringObservableField

class TestViewModel :BaseViewModel(){
    var name = StringObservableField()
}