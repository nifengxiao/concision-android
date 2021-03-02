package com.creator.config.data.model.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @CreateDate:     2021/2/20
 * @Author:         YuanFeng
 * @Description:    用户信息
 */
@Parcelize
data class UserBean(
    var admin: Boolean = false,
    var chapterTops: List<String> = listOf(),
    var collectIds: MutableList<String> = mutableListOf(),
    var email: String = "",
    var icon: String = "",
    var id: String = "",
    var nickname: String = "",
    var password: String = "",
    var token: String = "",
    var type: Int = 0,
    var username: String = "",
): Parcelable

