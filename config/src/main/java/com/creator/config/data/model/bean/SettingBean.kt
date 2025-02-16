package com.creator.config.data.model.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @CreateDate:     2021/2/20
 * @Author:         YuanFeng
 * @Description:    基本配置
 */
@Parcelize
data class SettingBean(
    var isOpenGuide: Boolean = false,
    var test:String = "初始值"
) : Parcelable