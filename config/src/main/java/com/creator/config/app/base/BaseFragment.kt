package com.creator.config.app.base

import androidx.appcompat.widget.Toolbar
import androidx.databinding.ViewDataBinding
import com.creator.concision.core.fragment.CommonBaseFragment
import com.creator.concision.core.viewmodel.BaseViewModel
import com.creator.config.utils.*
import com.example.config.R
import com.gyf.immersionbar.ImmersionBar
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir


/**
 * @CreateDate:     2021/2/7
 * @Author:         YuanFeng
 * @Description:    你项目中的Fragment基类，在这里实现显示弹窗，吐司，还有自己的需求操作
 */
abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> :
    CommonBaseFragment<VM, DB> {

    //状态页
    lateinit var loadSir: LoadService<Any>

    constructor() : super()

    /**
     * 懒加载 只有当前fragment视图显示时才会触发该方法
     */
    override fun lazyLoadData() {
        initLoadSir()
    }

    /**
     * 创建LiveData观察者 Fragment执行onViewCreated后触发
     */
    override fun createObserver() {}

    /**
     * Fragment执行onViewCreated后触发
     */
    override fun initData() {}

    override fun initLoadSir() {
        super.initLoadSir()
        val view = openLoadSir()
        if (view != null) {
            loadSirInit(view, onReloadListener = Callback.OnReloadListener {
                loadSir.showLoading()
                refresh()
            })
            loadSir.showLoading()
        }
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
     * 初始化状态栏
     * tips:
     * 初始是有状态栏就显示状态栏，没有标题栏默认上移
     */
    override fun initStatusBar() {
        if (!openDefaultImmersionBar()) {
            return
        }
        val view = view?.findViewById<Toolbar>(R.id.toolbar)
        ImmersionBar
            .with(this)
            .navigationBarEnable(false)
            .titleBar(view)
            .statusBarDarkFont(true)
            .init()
    }

    override fun onDestroy() {
        super.onDestroy()
        ImmersionBar.destroy(this)
    }


}