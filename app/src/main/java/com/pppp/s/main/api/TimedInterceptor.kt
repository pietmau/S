package com.pppp.s.main.api

import okhttp3.Interceptor
import okhttp3.Response

/** Very nice, cleanest solution */
class TimedInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response? {
        val originalResponse = chain?.proceed(chain.request())
        return originalResponse?.newBuilder()
            ?.header("Cache-Control", "public, max-age=" + 60 * 10)
            ?.build()
    }
}