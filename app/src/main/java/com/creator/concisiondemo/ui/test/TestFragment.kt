package com.creator.concisiondemo.ui.test

import android.os.Bundle
import com.creator.concisiondemo.R
import com.creator.concisiondemo.databinding.FragmentTestBinding
import com.creator.config.app.base.BaseFragment

class TestFragment : BaseFragment<TestViewModel, FragmentTestBinding>() {

    override fun layoutId(): Int {
        return R.layout.fragment_test
    }

    override fun initView(savedInstanceState: Bundle?) {
        mViewModel.name.set(arguments?.getString("name"))
    }

    override fun lazyLoadData() {
        super.lazyLoadData()

    }
}