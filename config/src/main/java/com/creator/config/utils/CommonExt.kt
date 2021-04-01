package com.creator.config.utils

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.Toolbar
import com.chad.library.adapter.base.BaseQuickAdapter
import com.creator.config.data.model.base.BaseListBean
import com.example.config.R
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import com.kingja.loadsir.core.LoadService
import com.scwang.smart.refresh.layout.SmartRefreshLayout

/**
 * @CreateDate:     2021/2/23
 * @Author:         Creator
 * @Description:    公共函数
 */

/**
 * 加载列表数据
 * 这里负责列表数据的所有逻辑
 */
fun <T> loadListData(
    data: BaseListBean<T>,
    baseQuickAdapter: BaseQuickAdapter<T, *>,
    loadService: LoadService<*>,
    refresh: SmartRefreshLayout
) {
    refresh.finishLoadMore()
    refresh.finishRefresh()
    if (data.isSuccess) {
        //成功
        when {
            //第一页并没有数据 显示空布局界面
            data.isFirstEmpty -> {
                loadService.showEmpty()
            }
            //加载数据成功
            else -> {
                baseQuickAdapter.setList(data.datas)
                loadService.showSuccess()
            }
        }
    } else {
        //失败
        if (data.isRefresh) {
            //如果是第一页，则显示错误界面，并提示错误信息
            loadService.showError(data.errMessage)
        } else {
//            recyclerView.loadMoreError(0, data.errMessage)
        }
    }
}

/**
 * 初始化导航
 */
fun BottomNavigationViewEx.init(navigationItemSelectedAction: (Int) -> Unit): BottomNavigationViewEx {
    enableAnimation(false)
//    enableShiftingMode(false)
//    enableItemShiftingMode(true)
    setTextSize(12F)
    setOnNavigationItemSelectedListener {
        navigationItemSelectedAction.invoke(it.itemId)
        true
    }
    return this
}

/**
 * 拦截BottomNavigation长按事件 防止长按时出现Toast
 * @receiver BottomNavigationViewEx
 * @param ids IntArray
 */
fun BottomNavigationViewEx.interceptLongClick(vararg ids: Int) {
    val bottomNavigationMenuView: ViewGroup = (this.getChildAt(0) as ViewGroup)
    for (index in ids.indices) {
        bottomNavigationMenuView.getChildAt(index).findViewById<View>(ids[index])
            .setOnLongClickListener {
                true
            }
    }
}


/**
 * 初始化普通的toolbar 只设置标题
 */
fun Toolbar.init(titleStr: String = ""): Toolbar {
//    setBackgroundColor(SettingUtil.getColor(appContext))
    title = titleStr
    return this
}

/**
 * 初始化有返回键的toolbar
 */
fun Toolbar.initClose(
    titleStr: String = "",
    backImg: Int = R.mipmap.ic_back,
    onBack: (toolbar: Toolbar) -> Unit
): Toolbar {
//    setBackgroundColor(SettingUtil.getColor(appContext))
    title = titleStr
    setNavigationIcon(backImg)
    setNavigationOnClickListener { onBack.invoke(this) }
    return this
}

/**
 * 隐藏软键盘
 */
fun hideSoftKeyboard(activity: Activity?) {
    activity?.let { act ->
        val view = act.currentFocus
        view?.let {
            val inputMethodManager =
                act.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(
                view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
}


