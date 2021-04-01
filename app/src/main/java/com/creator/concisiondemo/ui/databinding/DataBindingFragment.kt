package com.creator.concisiondemo.ui.databinding

import android.os.Bundle
import com.creator.concision.ext.nav
import com.creator.concisiondemo.R
import com.creator.concisiondemo.databinding.FragmentDatabindingBinding
import com.creator.config.app.base.BaseFragment
import com.creator.config.utils.initClose
import kotlinx.android.synthetic.main.include_toolbar.*

/**
 * databinding 示例
 */
class DataBindingFragment : BaseFragment<DataBindingViewModel, FragmentDatabindingBinding>() {

    override fun layoutId(): Int {
        return R.layout.fragment_databinding
    }

    override fun initView(savedInstanceState: Bundle?) {
        toolbar.initClose("databinding") {
            nav().navigateUp()
        }
    }
}