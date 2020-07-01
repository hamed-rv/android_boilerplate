package com.hamedrahimvand.android_boilerplate.data.remote.retrofit


import com.hamedrahimvand.android_boilerplate.BuildConfig
import com.hamedrahimvand.android_boilerplate.data.local.AccountManager
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor() : Interceptor {

    private val VERSION_KEY = "App-Version"
    private val PLATFROM_KEY = "App-Platform"
    private val PLATFROM_VALUE = "android"

    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("User-Agent", BuildConfig.APPLICATION_ID)
//            .addHeader(VERSION_KEY, BuildConfig.VERSION_NAME)
            .addHeader(PLATFROM_KEY, PLATFROM_VALUE)
            .method(original.method, original.body)

        requestBuilder.header(
            "Authorization",
            "${AccountManager.TOKEN_PREFIX} ${(
                    AccountManager.getCurrentUserToken()
                        ?: "")}"
        )


        return chain.proceed(requestBuilder.build())
    }

}