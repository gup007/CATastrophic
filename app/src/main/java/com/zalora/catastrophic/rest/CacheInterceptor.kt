package com.zalora.catastrophic.rest

import android.content.Context
import com.zalora.catastrophic.common.NetworkUtil
import okhttp3.Interceptor
import okhttp3.Response

class CacheInterceptor(val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = if (NetworkUtil.hasNetwork(context))
            request.newBuilder().header("Cache-Control", "public, max-age=" + 60 * 60 * 2).build()
        else
            request.newBuilder().header(
                "Cache-Control",
                "public, only-if-cached, max-stale=" + 60 * 60 * 2
            ).build()
        return chain.proceed(request)
    }
}