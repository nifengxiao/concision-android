package com.creator.concisiondemo.ui.main

import android.os.Bundle
import com.blankj.utilcode.util.LogUtils
import com.creator.concisiondemo.R
import com.creator.config.app.base.BaseFragment
import com.creator.concisiondemo.databinding.FragmentMainBinding
import com.creator.concisiondemo.ui.MainViewModel
import com.creator.concisiondemo.utils.initMain
import com.creator.config.utils.init
import com.creator.config.utils.interceptLongClick
import com.gyf.immersionbar.ImmersionBar
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.include_toolbar.*

/**
 * @CreateDate:     2021/2/20
 * @Author:         Creator
 * @Description:    主页
 */
class MainFragment: BaseFragment<MainViewModel, FragmentMainBinding>(){


    override fun layoutId(): Int {
        return R.layout.fragment_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        LogUtils.i("GuideFragment进入耗时"+System.currentTimeMillis())
        //初始化viewpager2
        mainViewpager.initMain(this)
        //初始化 bottomBar
        nav_view.init{
            when (it) {
                R.id.navigation_home -> mainViewpager.setCurrentItem(0, false)
                R.id.navigation_dashboard -> mainViewpager.setCurrentItem(1, false)
                R.id.navigation_event -> mainViewpager.setCurrentItem(2, false)
            }
        }
        //禁止长按出现Toast
        nav_view.interceptLongClick(R.id.navigation_home,R.id.navigation_dashboard,R.id.navigation_event)
    }


    override fun isVisibleStatusBar(): Boolean {
        return false
    }
}