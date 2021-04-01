package com.creator.config.data.model.base

import com.blankj.utilcode.util.LogUtils
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * @CreateDate:     2021/3/19
 * @Author:         Creator
 * @Description:
 */
data class BaseBean<T>(
    var status: String = "",
    var data: T,
    var errorCode: Int = 0,
    var msg: String = ""
)

data class ListData(
    val list: List<Item> = listOf(),
    val totalnum: Int = 0
) {
    data class Item(
        val warehousing_id: Int = 0
    )
}

fun jx() {
    val type: Type = object : TypeToken<BaseBean<ListData>>() {}.type


    val result = GsonBuilder().create().fromJson<BaseBean<ListData>>("{\n" +
            "\t\"status\": \"success\",\n" +
            "\t\"errorCode\": \"0\",\n" +
            "\t\"data\": {\n" +
            "\t\t\"list\": [\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"warehousing_id\": 30\n" +
            "\t\t\t\t\n" +
            "}\n" +
            "\t\t],\n" +
            "\t\t\"totalnum\": 2\n" +
            "\t},\n" +
            "\t\"msg\": \"ok\"\n" +
            "}\n", type)
    //泛型传
    LogUtils.i(result)
}
