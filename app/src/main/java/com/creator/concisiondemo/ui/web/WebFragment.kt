package com.creator.concisiondemo.ui.web

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.FrameLayout
import com.creator.concision.ext.nav
import com.creator.concisiondemo.R
import com.creator.concisiondemo.app.App
import com.creator.concisiondemo.app.base.BaseFragment
import com.creator.concisiondemo.data.model.bean.WebBean
import com.creator.concisiondemo.databinding.FragmentGuideBinding
import com.creator.concisiondemo.ui.MainActivity
import com.creator.concisiondemo.utils.hideSoftKeyboard
import com.creator.concisiondemo.utils.initClose
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
        initTitle()
        initWeb()
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