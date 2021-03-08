package com.creator.concisiondemo.ui.main.event

import android.os.Bundle
import androidx.lifecycle.Observer
import com.creator.concisiondemo.R
import com.creator.concisiondemo.databinding.FragmentEventBinding
import com.creator.config.app.base.BaseFragment

class EventFragment : BaseFragment<EventViewModel, FragmentEventBinding>() {

    override fun layoutId(): Int {
        return R.layout.fragment_event
    }

    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.click = ProxyClick()
        mDatabind.vm = mViewModel
        eventViewModel.test.observe(viewLifecycleOwner,
            Observer<String> { t ->
                mViewModel.text.set(t)
                mViewModel.text.notifyChange()
            })
    }

    override fun openDefaultImmersionBar(): Boolean {
        return false
    }

    inner class ProxyClick {
        fun change() {
            eventViewModel.test.value = "我已更改"
        }
    }

}