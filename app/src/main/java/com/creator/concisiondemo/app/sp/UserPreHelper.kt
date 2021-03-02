package com.creator.concisiondemo.app.sp

import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.StringUtils
import com.creator.concisiondemo.data.model.bean.UserBean

/**
 * @CreateDate:     2021/2/20
 * @Author:         YuanFeng
 * @Description:    用户相关的share，包括头像昵称token等等
 */
object UserPreHelper {
    private val userShares: SPUtils
        get() = SPUtils.getInstance(SPUtilsName.USER_TABLE)

    /**
     * 存入用户信息
     */
    var user: UserBean
        get() {
            val string = userShares.getString(SPUtilsName.USER)
            return GsonUtils.fromJson(string, UserBean::class.java) ?: return UserBean()
        }
        set(userBean) {
            userShares.put(SPUtilsName.USER, GsonUtils.toJson(userBean))
        }

    /**
     * 获取token
     *
     * @return
     */
    val token: String?
        get() {
            return user.token
        }

    /**
     * 判断是否已经登录
     */
    val isLogin: Boolean
        get() {
            return !StringUtils.isEmpty(token)
        }

    /**
     * 清空用户信息
     */
    fun clearAllData() {
        userShares.clear()
    }
}