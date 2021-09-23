package me.yifeiyuan.hf.fragment

import android.app.Application
import me.yifeiyuan.adh.DebugConfig
import me.yifeiyuan.adh.DebugHelper

/**
 * Created by 程序亦非猿 on 2021/1/15.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        DebugConfig().apply {
            application = this@App
            enableStrictMode = false
            detectActivityLifecycle = false
            detectFragmentLifecycle = false
            logLevel = DebugConfig.LogLevel.E
        }.also {
            DebugHelper.setup(it)
        }
    }

}