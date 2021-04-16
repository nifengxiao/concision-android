package com.creator.concisiondemo.ui.deeplink

import android.os.Bundle
import com.creator.concisiondemo.R
import com.creator.concisiondemo.databinding.FragmentDeeplinkBinding
import com.creator.config.app.base.BaseFragment


class DeepLinkFragment: BaseFragment<DeepLinkViewModel, FragmentDeeplinkBinding>(){

    override fun layoutId(): Int {
        return R.layout.fragment_deeplink
    }

    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.click = ProxyClick()
    }

    override fun lazyLoadData() {
        super.lazyLoadData()
    }


    inner class ProxyClick {

    }
}