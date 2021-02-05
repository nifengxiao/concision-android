package com.creator.concision.core.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.creator.concision.R
import com.creator.concision.core.app.appContext
import com.creator.concision.core.viewmodel.BaseViewModel
import com.creator.concision.ext.getVmClass
import com.creator.concision.network.manager.NetState
import com.creator.concision.network.manager.NetworkStateManager

/**
 * @CreateDate: 2021/1/11
 * @Author: Creator
 * @Description: ViewModelActivity基类
 */
abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    lateinit var mViewModel: VM

    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun showLoading(message: String = appContext.getString(R.string.def_loading_message))

    abstract fun dismissLoading()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBind()
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        mViewModel = createViewModel()
        registerUiChange()
        initView(savedInstanceState)
        createObserver()
        //监听网络状态
        NetworkStateManager.instance.mNetworkStateCallback.observeInActivity(this) {
            onNetworkStateChanged(it)
        }
    }

    /**
     * 网络变化监听 子类重写
     */
    open fun onNetworkStateChanged(netState: NetState) {}

    /**
     * 创建viewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClass(this))
    }

    /**
     * 创建LiveData数据观察者
     */
    abstract fun createObserver()

    /**
     * 注册UI 事件
     */
    private fun registerUiChange() {
        //显示弹窗
        mViewModel.loadingChange.showDialog.observeInActivity(this) {
            showLoading(it)
        }
        //关闭弹窗
        mViewModel.loadingChange.dismissDialog.observeInActivity(this) {
            dismissLoading()
        }
    }

    /**
     * 将非该Activity绑定的ViewModel添加 loading回调 防止出现请求时不显示 loading 弹窗bug
     * @param viewModels Array<out BaseViewModel>
     */
    protected fun addLoadingObserve(vararg viewModels: BaseViewModel) {
        viewModels.forEach { viewModel ->
            //显示弹窗
            viewModel.loadingChange.showDialog.observeInActivity(this) {
                showLoading(it)
            }
            //关闭弹窗
            viewModel.loadingChange.dismissDialog.observeInActivity(this) {
                dismissLoading()
            }
        }
    }


    /**
     * 初始化DataBinding
     */
    open fun initDataBind() {}



}