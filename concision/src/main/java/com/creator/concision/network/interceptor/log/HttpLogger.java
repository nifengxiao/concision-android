package com.creator.concision.network.interceptor.log;

import com.blankj.utilcode.util.LogUtils;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @CreateDate: 2021/3/8
 * @Author: Creator
 * @Description: 网络日志
 */
public class HttpLogger implements HttpLoggingInterceptor.Logger {
    private StringBuilder mMessage = new StringBuilder();

    @Override
    public void log(String message) {
        // 请求或者响应开始
        if (message.startsWith("--> POST")) {
            mMessage.setLength(0);
        }
        // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
        if ((message.startsWith("{") && message.endsWith("}"))
                || (message.startsWith("[") && message.endsWith("]"))) {
            message = JsonUtil.formatJson(message);
        }
        mMessage.append(message.concat("\n"));
        // 请求或者响应结束，打印整条日志
        if (message.startsWith("<-- END HTTP")) {
            LogUtils.d(mMessage.toString());
        }
    }
}
