package com.creator.concisiondemo.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.blankj.utilcode.util.ToastUtils
import com.creator.concision.network.manager.NetState
import com.creator.concisiondemo.R
import com.creator.concisiondemo.data.isInit
import com.creator.config.app.base.BaseActivity
import com.creator.concisiondemo.databinding.ActivityMainBinding

/**
 * @CreateDate:     2021/2/7
 * @Author:         Creator
 * @Description:    首页
 */
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    //记录第一次按退出按钮的时间
    var exitTime = 0L

    //导航控制器
    lateinit var nav: NavController

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        if (fitBackHomeBug()) return
        nav = Navigation.findNavController(this@MainActivity, R.id.host_fragment)
        exitApp()
        initWindow()
    }

    //用于处理
    private fun initWindow() {
        if (isInit) {
            //取消全屏
            window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            //更换背景
            window?.decorView?.background = ColorDrawable(Color.WHITE)
        }
    }

    override fun createObserver() {

    }

    /**
     * 防止出现按Home键回到桌面时，再次点击重新进入该界面bug
     */
    private fun fitBackHomeBug(): Boolean {
        if (intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT !== 0) {
            finish()
            return true
        }
        return false
    }

    /**
     * 退出程序
     */
    private fun exitApp() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (nav.currentDestination != null && nav.currentDestination!!.id != R.id.menu_fragment) {
                    //如果当前界面不是主页，那么直接调用返回即可
                    nav.navigateUp()
                } else {
                    //是主页 在两秒内点击退出程序
                    if (System.currentTimeMillis() - exitTime > 2000) {
                        ToastUtils.showShort("再按一次退出程序")
                        exitTime = System.currentTimeMillis()
                    } else {
                        finish()
                    }
                }
            }
        })
    }


    override fun onNetworkStateChanged(netState: NetState) {
        super.onNetworkStateChanged(netState)
        if (!netState.isSuccess) {
            Toast.makeText(applicationContext, "网络已断开!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(applicationContext, "网络已连接!", Toast.LENGTH_SHORT).show()
        }
    }

}