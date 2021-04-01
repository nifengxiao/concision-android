package com.creator.concisiondemo.ui.entrance.guide

import android.os.Bundle
import com.blankj.utilcode.util.LogUtils
import com.creator.concision.ext.nav
import com.creator.concision.ext.navigateAction
import com.creator.concisiondemo.R
import com.creator.config.app.base.BaseFragment
import com.creator.config.app.sp.AppPreHelper
import com.creator.concisiondemo.databinding.FragmentGuideBinding

/**
 * @CreateDate:     2021/2/20
 * @Author:         Creator
 * @Description:    引导页
 */
class GuideFragment: BaseFragment<GuideViewModel, FragmentGuideBinding>(){

    override fun layoutId(): Int {
        return R.layout.fragment_guide
    }

    override fun initView(savedInstanceState: Bundle?) {
        LogUtils.i("GuideFragment进入耗时"+System.currentTimeMillis())
        mDatabind.click = ProxyClick()
    }

    inner class ProxyClick {
        fun actionGuideToMain(){
            AppPreHelper.isOpenGuide = true
            LogUtils.i("SettingBean", AppPreHelper.setting)
            nav().navigateAction(R.id.action_guide_menu)
        }
    }

}