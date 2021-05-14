package com.creator.config.app.base

import androidx.databinding.ViewDataBinding
import com.creator.concision.core.activity.CommonBaseActivity
import com.creator.concision.core.viewmodel.BaseViewModel
import com.creator.config.utils.dismissLoadingExt
import com.creator.config.utils.showLoading
import com.creator.config.utils.showLoadingExt
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir

/**
 * @CreateDate:     2021/2/7
 * @Author:         YuanFeng
 * @Description:    你项目中的Activity基类，在这里实现显示弹窗，吐司，还有自己的需求操作
 */
abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : CommonBaseActivity<VM, DB>() {

    //状态页
    lateinit var loadSir: LoadService<Any>

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

    /**
     * LoadSir-init
     */
    protected fun loadSirInit(target: Any, onReloadListener: Callback.OnReloadListener) {
        loadSir = LoadSir.getDefault().register(target) {
            loadSir.showLoading()
            onReloadListener.onReload(it)
        }
    }


    /**
     * 创建LiveData数据观察者
     */
    override fun createObserver() {
    }

}