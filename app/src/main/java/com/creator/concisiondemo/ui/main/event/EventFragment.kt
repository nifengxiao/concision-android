package com.creator.concisiondemo.ui.main.event

import android.os.Bundle
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.LogUtils
import com.creator.concisiondemo.R
import com.creator.concisiondemo.databinding.FragmentEventBinding
import com.creator.concisiondemo.utils.openStatusBar
import com.creator.config.app.base.BaseFragment
import com.creator.config.app.eventViewModel

class EventFragment : BaseFragment<EventViewModel, FragmentEventBinding>() {

    override fun layoutId(): Int {
        return R.layout.fragment_event
    }

    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.click = ProxyClick()
//        eventViewModel.test.observe(viewLifecycleOwner,
//            Observer<String> { t ->
//                mViewModel.text.set(t)
//                mViewModel.text.notifyChange()
//                LogUtils.i("test1")
//            })
        eventViewModel.test.observeInFragment(this) { t ->
            mViewModel.text.set(t)
            mViewModel.text.notifyChange()
            LogUtils.i("test1")
        }
    }

    inner class ProxyClick {
        fun change() {
            eventViewModel.test.value = "我已更改"
        }
    }
}