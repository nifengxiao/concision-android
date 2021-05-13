package com.creator.concisiondemo.ui.main.home

import androidx.databinding.ObservableField
import com.creator.concision.core.viewmodel.BaseViewModel
import com.creator.concisiondemo.data.model.bean.ArticleBean
import com.creator.config.data.model.base.BaseListBean

/**
 * @CreateDate:     2021/2/23
 * @Author:         YuanFeng
 * @Description:    首页
 */
class HomeViewModel : BaseViewModel() {

    var data = ObservableField<BaseListBean<ArticleBean>>()
}