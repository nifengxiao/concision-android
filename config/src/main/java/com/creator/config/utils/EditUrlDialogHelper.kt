package com.creator.config.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import com.example.config.R
import kotlinx.android.synthetic.main.dialog_edit_url.view.*

/**
 * @CreateDate:     2021/3/31
 * @Author:         Creator
 * @Description:    编辑url
 */
class EditUrlDialogHelper private constructor() {
    var dialog: AlertDialog? = null


    companion object {
        val instance: EditUrlDialogHelper by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            EditUrlDialogHelper()
        }
    }

    /**
     * 修改昵称
     */
    fun showChangeNameDialog(
        context: Context?,
        sure: (name:String,url: String) -> Unit,
        dismiss:()->Unit
    ) {
        if (context == null) {
            return
        }
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_edit_url, null)
        val dialog = AlertDialog.Builder(context)
        dialog.setView(view)
        this.dialog = dialog.show()
        view.tv_confirm.setOnClickListener {
            //新增url
            sure(view.et_name.text.toString(),view.et_url.text.toString())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            this.dialog?.setOnDismissListener {
                dismiss()
            }
        }
    }


}