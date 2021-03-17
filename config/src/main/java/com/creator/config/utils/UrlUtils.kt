package com.creator.config.utils

import android.content.Context
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.creator.concision.util.file.FileUtils
import com.creator.config.data.model.bean.UrlBean
import com.example.config.BuildConfig
import me.jessyan.retrofiturlmanager.RetrofitUrlManager

/**
 * @CreateDate:     2021/3/17
 * @Author:         Creator
 * @Description:    服务器配置
 */
object UrlUtils {

    /**
     * 初始化服务器地址
     * @param context 当前上下文
     * @param next 下一步
     */
    fun initUrl(context: Context, next: () -> Unit) {
        //获取json
        val urlList = getUrlList()
        //如果是release 就直接取第一个
        if (BuildConfig.DEBUG) {//如果是debug，就遍历所有的地址，并加载到选择器里面，进行选择切换
            PickerUtils.createOptionsPicker(
                context,
                "请选择服务器",
                urlList,
                { position, options1 ->
                    RetrofitUrlManager.getInstance().setGlobalDomain(options1.url);
                    //新增额外的url
                    for (i in options1.ext_url) {
                        RetrofitUrlManager.getInstance().putDomain(i.domain_name, i.url);
                    }
                    next()
                },
                {
                    next()
                }
            )
        } else {
            val urlBean = urlList[0]
            RetrofitUrlManager.getInstance().setGlobalDomain(urlBean.url);
            //新增额外的url
            for (i in urlBean.ext_url) {
                RetrofitUrlManager.getInstance().putDomain(i.domain_name, i.url);
            }
            next()
        }
    }

    /**
     * 获取url
     */
    fun getUrlList(): ArrayList<UrlBean> {
        val jsonStr = FileUtils.getJson("urls.json")
        val urlList = GsonUtils.fromJson<ArrayList<UrlBean>>(
            jsonStr,
            GsonUtils.getListType(UrlBean::class.java)
        )
        LogUtils.i("listFilesInDir", urlList)
        return urlList
    }

}