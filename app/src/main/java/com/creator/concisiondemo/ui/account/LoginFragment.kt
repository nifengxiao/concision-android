package com.creator.concisiondemo.ui.account

import android.os.Bundle
import com.creator.concisiondemo.R
import com.creator.config.app.base.BaseFragment
import com.creator.concisiondemo.databinding.FragmentGuideBinding

/**
 * @CreateDate:     2021/2/20
 * @Author:         Creator
 * @Description:    登录
 */
class LoginFragment: BaseFragment<LoginViewModel, FragmentGuideBinding>(){

    override fun layoutId(): Int {
        return R.layout.fragment_login
    }

    override fun initView(savedInstanceState: Bundle?) {

    }
}