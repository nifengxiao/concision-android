package com.creator.concisiondemo.data.model.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @CreateDate:     2021/2/20
 * @Author:         YuanFeng
 * @Description:    网页信息
 */
@Parcelize
data class WebBean(
    var title: String = "",
    var url: String = ""
) : Parcelable