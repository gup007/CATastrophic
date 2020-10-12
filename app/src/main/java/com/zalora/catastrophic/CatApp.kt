package com.zalora.catastrophic

import android.app.Activity
import android.content.Context
import android.os.StrictMode
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.zalora.catastrophic.dagger.core.CoreComponent
import com.zalora.catastrophic.dagger.core.DaggerCoreComponent


class CatApp : MultiDexApplication() {

    private lateinit var coreComponent: CoreComponent

    override fun onCreate() {
        super.onCreate()

        coreComponent = DaggerCoreComponent.builder()
            .applicationContext(this)
            .build()

        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        builder.detectFileUriExposure()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    companion object {
        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as CatApp).coreComponent
    }
}

fun Activity.coreComponent() = CatApp.coreComponent(this)