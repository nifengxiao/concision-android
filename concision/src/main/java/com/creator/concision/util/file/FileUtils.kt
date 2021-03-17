package com.creator.concision.util.file

import android.content.res.AssetManager
import com.creator.concision.core.app.appContext
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


/**
 * @CreateDate:     2021/3/17
 * @Author:         Creator
 * @Description:
 */
object FileUtils {
    fun getJson(fileName: String): String? {
        val stringBuilder = StringBuilder()
        try {
            val assets: AssetManager = appContext.assets
            val bf = BufferedReader(
                InputStreamReader(
                    assets.open(fileName)
                )
            )
            var line: String?
            while (bf.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
            return stringBuilder.toString()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return ""
    }
}