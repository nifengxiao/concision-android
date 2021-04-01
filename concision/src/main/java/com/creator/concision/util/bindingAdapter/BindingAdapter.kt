package com.creator.concision.util.bindingAdapter

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.blankj.utilcode.util.StringUtils
import com.creator.concision.util.money.AmountUtils
import com.xw.repo.BubbleSeekBar


/**
 * @CreateDate:     2021/3/30
 * @Author:         Creator
 * @Description:    databinding 适配器
 */
object BindingAdapter {

    /**
     * Boolean值控制显隐
     */
    @BindingAdapter("android:visibility")
    @JvmStatic
    fun setVisibility(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    //这只是个示例  如果在界面上有什么需要对数据进行转换的 可以如下 比较灵活 比如说时间转换、增加单位、添个默认值等等
    /**
     * 显示文字并具有默认值
     * @param str 需要显示的文字
     */
    @BindingAdapter("android:text_visible")
    @JvmStatic
    fun setTextDefault(tv: TextView, str: String = "") {
        if (StringUtils.isEmpty(str)) {
            tv.text = "--"
        } else {
            tv.text = str
        }
    }

    /**
     * 显示文字并具有显隐能力
     * @param str 需要显示的文字
     */
    @BindingAdapter("android:text_default")
    @JvmStatic
    fun setTextVisibility(tv: TextView, str: String = "") {
        if (StringUtils.isEmpty(str)) {
            tv.visibility = View.GONE
        } else {
            tv.text = str
            tv.visibility = View.VISIBLE
        }
    }

    /**
     * 显示价格
     * @param fen   分
     * @param yuan  元
     * @param price 价格
     */
    @SuppressLint("SetTextI18n")
    @BindingAdapter("android:fen", "android:yuan", "android:price", requireAll = false)
    @JvmStatic
    fun setPrice(tv: TextView, fen: Long = 0L, yuan: Double = 0.0, price: String = "") {
        if (fen != 0L) {
            tv.text = "¥ ${AmountUtils.changeF2Y(fen)}"
        } else if (yuan != 0.0) {
            tv.text = "¥ $yuan"
        } else if (!StringUtils.isEmpty(price)) {
            tv.text = "¥ $price"
        } else {
            tv.text = "¥ --"
        }
    }


    //这只是个双向绑定的示例 、拖动进度条要更改数据、比如说选择更改数据等
    @BindingAdapter("app:progress")
    @JvmStatic
    fun setProgress(seekBar: BubbleSeekBar, progress: Int) {
        if (seekBar.progress != progress) {
            seekBar.setProgress(progress.toFloat())
        }
    }

    @InverseBindingAdapter(attribute = "app:progress", event = "app:progressChanged")
    @JvmStatic
    fun getProgress(seekBar: BubbleSeekBar): Int {
        return seekBar.progress
    }

    @BindingAdapter("app:progressChanged")
    @JvmStatic
    fun setProgressListener(
        seekBar: BubbleSeekBar,
        listener: InverseBindingListener
    ) {
        seekBar.onProgressChangedListener = object : BubbleSeekBar.OnProgressChangedListener {
            override fun onProgressChanged(
                bubbleSeekBar: BubbleSeekBar?, progress: Int,
                progressFloat: Float, fromUser: Boolean
            ) {
                listener.onChange()
            }

            override fun getProgressOnActionUp(
                bubbleSeekBar: BubbleSeekBar?, progress: Int,
                progressFloat: Float
            ) {
            }

            override fun getProgressOnFinally(
                bubbleSeekBar: BubbleSeekBar?, progress: Int,
                progressFloat: Float, fromUser: Boolean
            ) {
            }
        }
    }
}