package com.creator.concisiondemo.ui.main.appviewmodel

import android.os.Bundle
import androidx.lifecycle.Observer
import com.creator.concisiondemo.R
import com.creator.concisiondemo.databinding.FragmentAppviewmodelBinding
import com.creator.config.app.appViewModel
import com.creator.config.app.base.BaseFragment
import com.creator.config.app.cache.AppPreHelper
import com.creator.config.data.model.bean.SettingBean

class AppViewModelFragment : BaseFragment<AppViewModelViewModel, FragmentAppviewmodelBinding>() {

    override fun layoutId(): Int {
        return R.layout.fragment_appviewmodel
    }

    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.click = ProxyClick()
        //去除了粘性
//        appViewModel.setting.observeInFragment(this
//        ) { t ->
//            mViewModel.text.set(t.test)
//            mViewModel.text.notifyChange()
//        }
        //有粘性
        appViewModel.setting.observe(viewLifecycleOwner,
            Observer<SettingBean> { t ->
                mViewModel.text.set(t.test)
                mViewModel.text.notifyChange()
            })
    }

    inner class ProxyClick {
        fun change() {
            val setting = AppPreHelper.setting
            setting.test = "更改后"
            AppPreHelper.setting = setting
        }
    }
}