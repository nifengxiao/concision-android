package com.creator.config.data.model.bean

import com.contrarywind.interfaces.IPickerViewData

/**
 * @CreateDate:     2021/3/17
 * @Author:         Creator
 * @Description:    url配置
 */
data class UrlBean(
    val name: String = "",
    val url: String = "",
    val ext_url: List<ExtUrl> = listOf()
) : IPickerViewData {
    override fun getPickerViewText(): String {
        return "$name:$url"
    }

    data class ExtUrl(
        val domain_name: String = "",
        val url: String = ""
    )

}