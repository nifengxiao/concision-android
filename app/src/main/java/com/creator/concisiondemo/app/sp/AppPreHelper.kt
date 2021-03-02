package com.creator.concisiondemo.app.sp

import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.GsonUtils.fromJson
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SPUtils
import com.creator.concisiondemo.data.model.bean.SettingBean

/**
 * @CreateDate:   2021/2/20
 * @Author:       YuanFeng
 * @Description:  App相关share
 * TODO 这个方式不是特别高效、且数据存取较复杂 有时间可以换成Room配合liveData进行数据存储
 */
object AppPreHelper {
    private val appPreShares: SPUtils
        get() = SPUtils.getInstance(SPUtilsName.APP_TABLE)

    //存入基本信息
    var setting: SettingBean
        get() {
            val string = appPreShares.getString(SPUtilsName.SETTING)
            val setting = fromJson(string, SettingBean::class.java)
            LogUtils.i("SettingBean-->存入基本信息", setting)
            return setting ?: return SettingBean()
        }
        set(settingBean) {  //注意 这个方法最好仅限于更新网络数据的时候使用
            //TODO 为保证全局使用一个表结构，这个地方需要对与网络不相关的数据进行赋值
            settingBean.isOpenGuide = setting.isOpenGuide
            appPreShares.put(SPUtilsName.SETTING, GsonUtils.toJson(settingBean))
        }

    //是否已经打开过引导页
    var isOpenGuide: Boolean
        get() = setting.isOpenGuide
        set(value) {
            val settingReplace = setting
            settingReplace.isOpenGuide = value
            LogUtils.i("SettingBean-->更改是否打开过引导页", settingReplace)
            appPreShares.put(SPUtilsName.SETTING, GsonUtils.toJson(settingReplace))
        }

    /**
     * 清空App所有信息
     */
    fun clearAllData() {
        appPreShares.clear()
    }

}