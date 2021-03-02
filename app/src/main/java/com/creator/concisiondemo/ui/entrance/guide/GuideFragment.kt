package com.creator.concisiondemo.ui.entrance.guide

import android.os.Bundle
import com.blankj.utilcode.util.LogUtils
import com.creator.concision.ext.nav
import com.creator.concision.ext.navigateAction
import com.creator.concisiondemo.R
import com.creator.concisiondemo.app.base.BaseFragment
import com.creator.concisiondemo.app.sp.AppPreHelper
import com.creator.concisiondemo.databinding.FragmentGuideBinding
import kotlinx.android.synthetic.main.fragment_guide.*

/**
 * @CreateDate:     2021/2/20
 * @Author:         Creator
 * @Description:    引导页
 */
class GuideFragment: BaseFragment<GuideViewModel,FragmentGuideBinding>(){

    override fun layoutId(): Int {
        return R.layout.fragment_guide
    }

    override fun initView(savedInstanceState: Bundle?) {
        LogUtils.i("GuideFragment进入耗时"+System.currentTimeMillis())
        tv_skip.setOnClickListener {
            AppPreHelper.isOpenGuide = true
            LogUtils.i("SettingBean", AppPreHelper.setting)
            nav().navigateAction(R.id.action_guide_main)
        }
    }

}