package com.creator.concisiondemo.ui.entrance

import android.content.Intent
import android.os.Bundle
import com.creator.concisiondemo.R
import com.creator.concisiondemo.app.base.BaseActivity
import com.creator.concisiondemo.databinding.ActivityEntranceBinding
import com.creator.concisiondemo.ui.main.MainActivity
import com.creator.concisiondemo.viewmodel.state.EntranceViewModel

/**
 * @CreateDate:     2021/2/19
 * @Author:         YuanFeng
 * @Description:    入口
 * @Functions
 * 1.app入口
 * 2.闪屏
 * 3.广告
 * 4.引导页
 * 5.app初始化
 */
class EntranceActivity : BaseActivity<EntranceViewModel, ActivityEntranceBinding>() {

    override fun layoutId(): Int {
        return R.layout.activity_entrance
    }

    override fun initView(savedInstanceState: Bundle?) {
        if (fitBackHomeBug()) return
        startMain()
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
     * 开启Main
     */
    private fun startMain() {
        //过场动画
        startActivity(Intent(this, MainActivity::class.java))
        finish()
        //带点渐变动画
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

}