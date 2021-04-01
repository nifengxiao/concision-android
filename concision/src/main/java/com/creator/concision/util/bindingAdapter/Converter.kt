package com.creator.concision.util.bindingAdapter

import androidx.databinding.InverseMethod
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/**
 * @CreateDate:     2021/3/30
 * @Author:         Creator
 * @Description:    转换器
 * 使用:  android:text="@{Converter.dateToString(activity.curTime)}"
 * 使用场景: 输入输出成对比,比如传入的时候是时间戳，显示的是date格式，然后更换了值之后发送的格式又是时间戳
 */
object Converter {

    /**
     * 绑定方法
     */
    @InverseMethod("stringToDate")
    fun dateToString(value: Long): String? {
        val s = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return s.format(Date(value))
    }

    /**
     * 逆转换器方法
     */
    fun stringToDate(value: String?): Long {
        val s = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        var time: Long = 0
        try {
            val date: Date = s.parse(value)
            time = date.time
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return time
    }

}