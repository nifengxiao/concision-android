package com.creator.concisiondemo.ui.account

import android.os.Bundle
import com.blankj.utilcode.util.LogUtils
import com.creator.concision.ext.nav
import com.creator.concision.ext.navigateAction
import com.creator.concisiondemo.R
import com.creator.concisiondemo.app.base.BaseFragment
import com.creator.concisiondemo.app.sp.AppPreHelper
import com.creator.concisiondemo.databinding.FragmentGuideBinding
import com.creator.concisiondemo.ui.entrance.guide.GuideViewModel
import kotlinx.android.synthetic.main.fragment_guide.*

/**
 * @CreateDate:     2021/2/20
 * @Author:         Creator
 * @Description:    登录
 */
class LoginFragment: BaseFragment<LoginViewModel,FragmentGuideBinding>(){

    override fun layoutId(): Int {
        return R.layout.fragment_login
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

}