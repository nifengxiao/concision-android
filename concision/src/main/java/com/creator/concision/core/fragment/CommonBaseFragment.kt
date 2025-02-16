package com.creator.concision.core.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.NetworkUtils
import com.creator.concision.R
import com.creator.concision.BR
import com.creator.concision.core.app.AppInitializer.Companion.app
import com.creator.concision.core.viewmodel.BaseViewModel
import com.creator.concision.ext.getVmClass

/**
 * @CreateDate: 2021/1/11
 * @Author: hegaojian
 * @Description: ViewModelFragment基类
 */
abstract class CommonBaseFragment<VM : BaseViewModel, B : ViewDataBinding> : Fragment() {

    //是否第一次加载
    private var isFirst: Boolean = true

    lateinit var mViewModel: VM

    lateinit var mActivity: AppCompatActivity

    /**
     * 当前Fragment绑定的视图布局
     */
    abstract fun layoutId(): Int

    abstract fun initStatusBar()

    //该类绑定的ViewDataBinding
    lateinit var mDatabind: B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mDatabind = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        mDatabind.lifecycleOwner = this
        return mDatabind.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isFirst = true
        mViewModel = createViewModel()
        mDatabind.setVariable(BR.vm, mViewModel)
        initView(savedInstanceState)
        createObserver()
        registerDefUIChange()
        initData()
        initStatusBar()
    }

    /**
     * 刷新
     */
    open fun refresh() {}

    /**
     * 初始化load
     */
    open fun initLoadSir(){}

    /**
     * 初始化loadSir
     * 传入View即可开启loadSir
     */
    open fun openLoadSir():View?{
        return null
    }

    /**
     * 是否开启ImmersionBar
     * 如需自定义 请传入false
     * @return 默认开启
     */
    open fun openDefaultImmersionBar(): Boolean {
        return true
    }


    /**
     * 网络变化监听 子类重写
     */
    open fun onNetworkStateChanged() {}

    /**
     * 创建viewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClass(this))
    }

    /**
     * 初始化view
     */
    abstract fun initView(savedInstanceState: Bundle?)

    /**
     * 懒加载
     */
    abstract fun lazyLoadData()

    /**
     * 创建观察者
     */
    abstract fun createObserver()

    override fun onResume() {
        super.onResume()
        onVisible()
    }

    /**
     * 是否需要懒加载
     */
    private fun onVisible() {
        if (lifecycle.currentState == Lifecycle.State.STARTED && isFirst) {
            //等待view加载后触发懒加载
            view?.post {
                lazyLoadData()
                //在Fragment中，只有懒加载过了才能开启网络变化监听
                NetworkUtils.registerNetworkStatusChangedListener(object :
                    NetworkUtils.OnNetworkStatusChangedListener {
                    override fun onConnected(networkType: NetworkUtils.NetworkType) {
                        //不是首次订阅时调用方法，防止数据第一次监听错误
                        if (!isFirst) {
                            onNetworkStateChanged()
                        }
                    }

                    override fun onDisconnected() {

                    }
                })
                isFirst = false
            }
        }
    }

    /**
     * Fragment执行onCreate后触发的方法
     */
    open fun initData() {}

    abstract fun showLoading(message: String = app.getString(R.string.def_loading_message))

    abstract fun dismissLoading()

    /**
     * 注册 UI 事件
     */
    private fun registerDefUIChange() {
        mViewModel.loadingChange.showDialog.observeInFragment(this) {
            showLoading(it)
        }
        mViewModel.loadingChange.dismissDialog.observeInFragment(this) {
            dismissLoading()
        }
    }

    /**
     * 将非该Fragment绑定的ViewModel添加 loading回调 防止出现请求时不显示 loading 弹窗bug
     * @param viewModels Array<out BaseViewModel>
     */
    protected fun addLoadingObserve(vararg viewModels: BaseViewModel) {
        viewModels.forEach { viewModel ->
            //显示弹窗
            viewModel.loadingChange.showDialog.observeInFragment(this) {
                showLoading(it)
            }
            //关闭弹窗
            viewModel.loadingChange.dismissDialog.observeInFragment(this) {
                dismissLoading()
            }
        }
    }



}