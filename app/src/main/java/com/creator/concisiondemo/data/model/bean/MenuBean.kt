package com.creator.concisiondemo.data.model.bean

import androidx.annotation.ColorInt

/**
 * @CreateDate:     2021/3/8
 * @Author:         Creator
 * @Description:
 */
data class MenuBean(
    @ColorInt
    var color: Int,
    var title: String = ""
)