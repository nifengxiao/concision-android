package com.creator.concisiondemo.ui.entrance.splash

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.WindowManager
import com.blankj.utilcode.util.LogUtils
import com.creator.concision.ext.nav
import com.creator.concision.ext.navigateAction
import com.creator.concisiondemo.R
import com.creator.concisiondemo.app.base.BaseFragment
import com.creator.concisiondemo.app.sp.AppPreHelper
import com.creator.concisiondemo.data.isInit
import com.creator.concisiondemo.databinding.FragmentSplashBinding

/**
 * @CreateDate:     2021/2/20
 * @Author:         Creator
 * @Description:    闪屏页
 * 1.app入口
 * 2.闪屏
 * 3.广告
 * 4.引导页
 * 5.app初始化
 */
class SplashFragment : BaseFragment<SplashViewModel, FragmentSplashBinding>() {

    override fun layoutId(): Int {
        return R.layout.fragment_splash
    }

    override fun initView(savedInstanceState: Bundle?) {
        LogUtils.i("SplashFragment到guide耗时" + System.currentTimeMillis())
        //取消全屏
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        //更换背景
        activity?.window?.decorView?.background = ColorDrawable(Color.WHITE)
        isInit = true

        if (AppPreHelper.isOpenGuide) {
            nav().navigateAction(R.id.action_splash_to_main)
        } else {
            nav().navigateAction(R.id.action_splash_to_guide)
        }
    }

}