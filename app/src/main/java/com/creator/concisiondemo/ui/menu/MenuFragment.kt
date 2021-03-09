package com.creator.concisiondemo.ui.menu

import android.os.Bundle
import com.blankj.utilcode.util.ConvertUtils
import com.creator.concision.ext.nav
import com.creator.concision.ext.navigateAction
import com.creator.concision.ext.util.getResString
import com.creator.concisiondemo.R
import com.creator.concisiondemo.data.model.bean.MenuBean
import com.creator.concisiondemo.databinding.FragmentMenuBinding
import com.creator.concisiondemo.ui.adapter.MenuAdapter
import com.creator.concisiondemo.utils.openStatusBar
import com.creator.config.app.base.BaseFragment
import com.luck.picture.lib.decoration.GridSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_menu.*
import java.util.*

/**
 * @CreateDate:     2021/3/8
 * @Author:         Creator
 * @Description:    菜单
 */
class MenuFragment : BaseFragment<MenuViewModel, FragmentMenuBinding>() {

    //文章
    private val menuAdapter: MenuAdapter by lazy { MenuAdapter() }

    override fun layoutId(): Int {
        return R.layout.fragment_menu
    }

    override fun initView(savedInstanceState: Bundle?) {
        //配置Recyclerview
        rc_menu.let {
            it.adapter = menuAdapter
            it.addItemDecoration(GridSpacingItemDecoration(2, ConvertUtils.dp2px(8f), true))
        }
        rc_menu.adapter = menuAdapter
        menuAdapter.data = arrayListOf(
            MenuBean(getColor(), R.string.menu_main.getResString()),
            MenuBean(getColor(), R.string.menu_room.getResString())
        )
        //点击事件
        menuAdapter.setOnItemClickListener { adapter, _, position ->
            val data = adapter.data[position] as MenuBean
            when (data.title) {
                R.string.menu_main.getResString() -> {
                    nav().navigateAction(R.id.action_menu_main)
                }

                R.string.menu_room.getResString() -> {

                }
            }
        }
    }

    private fun getColor(): Int {
        val random = Random()
        return -0x1000000 or random.nextInt(0x00ffffff)
    }

    override fun openDefaultImmersionBar(): Boolean {
        this.openStatusBar()
        return false
    }

}