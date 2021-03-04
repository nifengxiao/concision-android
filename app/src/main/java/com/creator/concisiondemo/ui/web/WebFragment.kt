package com.creator.concisiondemo.ui.web

import android.os.Bundle
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.creator.concision.core.app.appContext
import com.creator.concision.ext.nav
import com.creator.concisiondemo.R
import com.creator.concisiondemo.data.model.bean.WebBean
import com.creator.config.app.base.BaseFragment
import com.creator.concisiondemo.databinding.FragmentGuideBinding
import com.creator.config.utils.hideSoftKeyboard
import com.creator.config.utils.initClose
import com.gyf.immersionbar.ImmersionBar
import com.just.agentweb.AgentWeb
import kotlinx.android.synthetic.main.fragment_web.*
import kotlinx.android.synthetic.main.include_toolbar.*


/**
 * @CreateDate:     2021/2/20
 * @Author:         Creator
 * @Description:    网页加载
 */
class WebFragment : BaseFragment<WebViewModel, FragmentGuideBinding>() {

    private var mAgentWeb: AgentWeb? = null

    private var preWeb: AgentWeb.PreAgentWeb? = null

    override fun layoutId(): Int {
        return R.layout.fragment_web
    }

    override fun initView(savedInstanceState: Bundle?) {
//        ImmersionBar
//            .with(this)
//            .titleBar(toolbar)
//            .statusBarDarkFont(true)
//            .init()
        initTitle()
        initWeb()
    }

    override fun isVisibleStatusBar(): Boolean {
        return true
    }

    override fun initData() {
        arguments?.run {
            getParcelable<WebBean>("web")?.let {
                mViewModel.webBean = it
            }
        }
    }

    private fun initWeb() {
        preWeb = AgentWeb.with(this)
            .setAgentWebParent(web_content, FrameLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .createAgentWeb()
            .ready()
    }

    private fun initTitle() {
        toolbar.run {
            //设置menu 关键代码
            mActivity.setSupportActionBar(this)
            initClose(mViewModel.webBean.title) {
                hideSoftKeyboard(activity)
                mAgentWeb?.let { web ->
                    if (web.webCreator.webView.canGoBack()) {
                        web.webCreator.webView.goBack()
                    } else {
                        nav().navigateUp()
                    }
                }
            }
        }
    }

    override fun lazyLoadData() {
        mAgentWeb = preWeb?.go(mViewModel.webBean.url)

        toolbar.title = mViewModel.webBean.title
    }

    override fun onPause() {
        mAgentWeb?.webLifeCycle?.onPause()
        super.onPause()
    }

    override fun onResume() {
        mAgentWeb?.webLifeCycle?.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        mAgentWeb?.webLifeCycle?.onDestroy()
        super.onDestroy()
    }

}