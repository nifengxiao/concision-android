package com.creator.concisiondemo.ui.test

import android.os.Bundle
import com.creator.concisiondemo.R
import com.creator.concisiondemo.databinding.ActivityTestBinding
import com.creator.config.app.base.BaseActivity

class TestActivity : BaseActivity<TestViewModel, ActivityTestBinding>() {

    override fun layoutId(): Int {
        return R.layout.activity_test
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

}