package com.creator.config.app.sp

import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.GsonUtils.fromJson
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.StringUtils
import com.creator.config.data.model.bean.SettingBean
import com.creator.config.data.model.bean.UrlBean
import com.tencent.mmkv.MMKV

/**
 * @CreateDate:   2021/2/20
 * @Author:       YuanFeng
 * @Description:  App相关share
 */
object AppPreHelper {

    private val appPreShares: MMKV
        get() = MMkvUtil.getMMKv(SPUtilsName.APP_TABLE)

    //url
    var urls: ArrayList<UrlBean>
        get() {
            val urlsStr = appPreShares.getString(SPUtilsName.URLS, "")
            val urlBeans =
                fromJson<ArrayList<UrlBean>>(urlsStr, GsonUtils.getListType(UrlBean::class.java))
            return urlBeans ?: return arrayListOf()
        }
        set(value) {
            appPreShares.encode(SPUtilsName.URLS, GsonUtils.toJson(value))
        }


    //存入基本信息
    var setting: SettingBean
        get() {
            val string = appPreShares.getString(SPUtilsName.SETTING, "")
            if (StringUtils.isEmpty(string)) {
                return SettingBean()
            }
            val setting = fromJson(string, SettingBean::class.java)
            LogUtils.i("SettingBean-->存入基本信息", setting)
            return setting ?: return SettingBean()
        }
        set(settingBean) {  //注意 这个方法最好仅限于更新网络数据的时候使用
            settingBean.isOpenGuide = setting.isOpenGuide
            appPreShares.encode(SPUtilsName.SETTING, GsonUtils.toJson(settingBean))
        }

    //是否已经打开过引导页
    var isOpenGuide: Boolean
        get() = setting.isOpenGuide
        set(value) {
            val settingReplace = setting
            settingReplace.isOpenGuide = value
            LogUtils.i("SettingBean-->更改是否打开过引导页", settingReplace)
            appPreShares.encode(SPUtilsName.SETTING, GsonUtils.toJson(settingReplace))
        }

    /**
     * 清空App所有信息
     */
    fun clearAllData() {
        appPreShares.clear()
    }

}