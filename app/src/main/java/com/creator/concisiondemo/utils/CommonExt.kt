package com.creator.concisiondemo.utils

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.creator.concisiondemo.ui.main.dashboard.DashboardFragment
import com.creator.concisiondemo.ui.main.home.HomeFragment
import com.creator.concisiondemo.ui.main.notifications.NotificationsFragment

/**
 * @CreateDate:     2021/2/23
 * @Author:         Creator
 * @Description:    公共函数
 */



/**
 * 初始化首页的viewpager
 */
fun ViewPager2.initMain(fragment: Fragment): ViewPager2 {
    //是否可滑动
    this.isUserInputEnabled = false
    this.offscreenPageLimit = 3
    //设置适配器
    adapter = object : FragmentStateAdapter(fragment) {
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> {
                    HomeFragment()
                }
                1 -> {
                    DashboardFragment()
                }
                2 -> {
                    NotificationsFragment()
                }
                else -> {
                    HomeFragment()
                }
            }
        }
        override fun getItemCount() = 3
    }
    return this
}