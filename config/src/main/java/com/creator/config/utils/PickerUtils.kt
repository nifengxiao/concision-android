package com.creator.config.utils

import android.content.Context
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.OptionsPickerView
import com.bigkoo.pickerview.view.TimePickerView
import java.util.*

/**
 * @CreateDate:     2021/3/17
 * @Author:         Creator
 * @Description:    选择器
 */
object PickerUtils {

    /**
     * 时间选择器
     * @param context 当前上下文
     * @param result 返回结果回调
     * @return 创建的时间选择器
     */
    fun createTimePicker(context: Context, result: (date: Date) -> Unit): TimePickerView {
        return TimePickerBuilder(context,
            OnTimeSelectListener { date, v ->
                result(date)
            }).build()
    }

    /**
     * 单项条件选择器
     * @param context 当前上下文
     * @param title 标题
     * @param list 列表
     * @param result 返回结果回调
     * @return 创建的单项条件选择器
     */
    fun <T> createOptionsPicker(
        context: Context,
        title: String,
        list: List<T>,
        result: (position: Int, options1: T) -> Unit,
        dismiss: () -> Unit
    ): OptionsPickerView<T> {
        val pvOptions: OptionsPickerView<T> =
            OptionsPickerBuilder(context,
                OnOptionsSelectListener { options1, option2, options3, v -> //返回的分别是三个级别的选中位置
                    result(options1, list[options1])
                }).build<T>()
        pvOptions.setOnDismissListener {
            dismiss()
        }
        pvOptions.setPicker(list)
        pvOptions.setTitleText(title)
        pvOptions.show()
        return pvOptions
    }


}