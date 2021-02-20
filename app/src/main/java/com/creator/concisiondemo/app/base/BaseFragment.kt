package com.creator.concisiondemo.app.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.creator.concision.core.fragment.CommonBaseFragment
import com.creator.concision.core.viewmodel.BaseViewModel
import com.creator.concision.ext.dismissLoadingExt
import com.creator.concision.ext.getAppViewModel
import com.creator.concision.ext.showLoadingExt
import com.creator.concisiondemo.app.event.AppViewModel


/**
 * @CreateDate:     2021/2/7
 * @Author:         YuanFeng
 * @Description:    你项目中的Fragment基类，在这里实现显示弹窗，吐司，还有自己的需求操作
 */
abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> : CommonBaseFragment<VM, DB>() {

    //Application全局的ViewModel，里面存放了一些账户信息，基本配置信息等
    val appViewModel: AppViewModel by lazy { getAppViewModel<AppViewModel>() }


//    //Application全局的ViewModel，用于发送全局通知操作
//    val eventViewModel: EventViewModel by lazy { getAppViewModel<EventViewModel>() }

    /**
     * 懒加载 只有当前fragment视图显示时才会触发该方法
     */
    override fun lazyLoadData() {}

    /**
     * 创建LiveData观察者 Fragment执行onViewCreated后触发
     */
    override fun createObserver() {}

    /**
     * Fragment执行onViewCreated后触发
     */
    override fun initData() {

    }

    /**
     * 打开等待框
     */
    override fun showLoading(message: String) {
        showLoadingExt(message)
    }

    /**
     * 关闭等待框
     */
    override fun dismissLoading() {
        dismissLoadingExt()
    }

//    override fun onPause() {
//        super.onPause()
//        hideSoftKeyboard(activity)
//    }
}