package com.creator.concisiondemo.ui.entrance.splash

import android.os.Bundle
import com.blankj.utilcode.util.LogUtils
import com.creator.concision.ext.nav
import com.creator.concision.ext.navigateAction
import com.creator.concisiondemo.R
import com.creator.concisiondemo.data.isInit
import com.creator.concisiondemo.databinding.FragmentSplashBinding
import com.creator.concisiondemo.utils.closeSplashTheme
import com.creator.config.app.base.BaseFragment
import com.creator.config.app.sp.AppPreHelper

/**
 * @CreateDate:     2021/2/20
 * @Author:         Creator
 * @Description:    闪屏页
 */
class SplashFragment : BaseFragment<SplashViewModel, FragmentSplashBinding>() {

    override fun layoutId(): Int {
        return R.layout.fragment_splash
    }

    override fun initView(savedInstanceState: Bundle?) {
        LogUtils.i("SplashFragment到guide耗时" + System.currentTimeMillis())
        closeSplashTheme()
        isInit = true

        if (AppPreHelper.isOpenGuide) {
            nav().navigateAction(R.id.action_splash_to_menu)
        } else {
            nav().navigateAction(R.id.action_splash_to_guide)
        }
    }

}