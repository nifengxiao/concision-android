package com.creator.concisiondemo.ui.main

import android.os.Bundle
import com.creator.concisiondemo.R
import com.creator.concisiondemo.app.base.BaseActivity
import com.creator.concisiondemo.databinding.ActivityMainBinding
import com.creator.concisiondemo.viewmodel.state.MainViewModel

/**
 * @CreateDate:     2021/2/7
 * @Author:         Creator
 * @Description:    首页
 */
class MainActivity : BaseActivity<MainViewModel,ActivityMainBinding>() {

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun createObserver() {

    }


}