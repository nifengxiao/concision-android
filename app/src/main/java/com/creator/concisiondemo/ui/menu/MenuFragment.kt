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
import com.kingja.loadsir.callback.Callback
import com.luck.picture.lib.decoration.GridSpacingItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_menu.*
import java.util.*
import javax.inject.Inject
import javax.inject.Scope
import kotlin.collections.ArrayList

/**
 * @CreateDate:     2021/3/8
 * @Author:         Creator
 * @Description:    菜单
 */
@AndroidEntryPoint
class MenuFragment : BaseFragment<MenuViewModel, FragmentMenuBinding>() {

    //文章
    @Inject
    lateinit var menuAdapter: MenuAdapter

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
//            MenuBean(getColor(), R.string.menu_room.getResString()),
            MenuBean(getColor(), R.string.data_binding.getResString()),
            MenuBean(getColor(), R.string.test.getResString())
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
                R.string.data_binding.getResString()->{
                    nav().navigateAction(R.id.action_menu_data_binding)
                }

                R.string.test.getResString()->{
                    val bundle = Bundle()
                    bundle.putString("name","我是传进去的值")
                    nav().navigateAction(R.id.action_menu_fragment_to_testFragment,bundle)
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