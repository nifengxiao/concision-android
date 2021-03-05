package com.creator.concisiondemo.ui.main.dashboard

import android.os.Bundle
import androidx.lifecycle.Observer
import com.creator.concisiondemo.R
import com.creator.concisiondemo.databinding.FragmentDashboardBinding
import com.creator.config.app.base.BaseFragment

class DashboardFragment : BaseFragment<DashboardViewModel, FragmentDashboardBinding>() {
    override fun layoutId(): Int {
        return R.layout.fragment_dashboard
    }

    override fun initView(savedInstanceState: Bundle?) {
//        eventViewModel.test.observeInFragment(
//            this
//        ) { t ->
//            mViewModel.text.value = t
//        }
        mDatabind.vm = mViewModel
        mViewModel.text.set("123123214")
        eventViewModel.test.observe(viewLifecycleOwner,
            Observer<String> { t ->
                mViewModel.text.set(t)
                mViewModel.text.notifyChange()
            })
    }

}