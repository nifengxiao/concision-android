package com.creator.config.utils

import android.content.Context
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.RegexUtils
import com.blankj.utilcode.util.ToastUtils
import com.creator.concision.util.file.FileUtils
import com.creator.config.app.cache.AppPreHelper
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
        val urlList = if (AppPreHelper.urls.size == 0) {
            getUrlList()
        } else {
            AppPreHelper.urls
        }
        //如果是release 就直接取第一个
        if (BuildConfig.DEBUG) {//如果是debug，就遍历所有的地址，并加载到选择器里面，进行选择切换
            PickerUtils.createOptionsPicker(
                context,
                "请选择服务器",
                urlList,
                { position, options1 ->
                    //新增域名
                    if (position == urlList.size - 1) {
                        EditUrlDialogHelper.instance.showChangeNameDialog(
                            context,
                            sure = { name, url ->
                                if (!RegexUtils.isURL(url)) {
                                    ToastUtils.showShort("请输入正确的url地址")
                                    return@showChangeNameDialog
                                }
                                //更换url
                                RetrofitUrlManager.getInstance().setGlobalDomain(url);
                                //把新的域名存储到缓存中
                                urlList.add(0, UrlBean(name, url))
                                AppPreHelper.urls = urlList
                                //消失
                                EditUrlDialogHelper.instance.dialog?.dismiss()
                            },
                            dismiss = {
                                next()
                            })
                        return@createOptionsPicker
                    }
                    RetrofitUrlManager.getInstance().setGlobalDomain(options1.url);
                    //新增额外的url
                    for (i in options1.ext_url) {
                        RetrofitUrlManager.getInstance().putDomain(i.domain_name, i.url);
                    }
                },
                {
                    if (EditUrlDialogHelper.instance.dialog == null) {
                        next()
                    }
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
        urlList.add(UrlBean("没有想要的?添一个咯"))
        LogUtils.i("listFilesInDir", urlList)
        return urlList
    }

}