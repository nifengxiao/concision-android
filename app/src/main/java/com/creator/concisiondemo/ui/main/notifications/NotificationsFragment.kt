package com.creator.concisiondemo.ui.main.notifications

import android.os.Bundle
import androidx.lifecycle.Observer
import com.creator.concisiondemo.R
import com.creator.concisiondemo.databinding.FragmentNotificationsBinding
import com.creator.config.app.base.BaseFragment

class NotificationsFragment : BaseFragment<NotificationsViewModel, FragmentNotificationsBinding>() {

    override fun layoutId(): Int {
        return R.layout.fragment_notifications
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

    inner class ProxyClick {
        fun change() {
            eventViewModel.test.value = "12345"
        }

    }


}