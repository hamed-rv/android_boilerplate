package com.hamedrahimvand.android_boilerplate.di.module

import android.content.Context
import android.net.ConnectivityManager
import com.hamedrahimvand.android_boilerplate.BuildConfig
import com.hamedrahimvand.android_boilerplate.MyApplication
import com.hamedrahimvand.android_boilerplate.data.remote.Apis
import com.hamedrahimvand.android_boilerplate.data.remote.retrofit.LiveDataCallAdapterFactory
import com.hamedrahimvand.android_boilerplate.data.remote.retrofit.LiveDataResponseBodyConverterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 *
 *@author Hamed.Rahimvand
 *@since 6/12/20
 */
@Module
class ApplicationModule {

    @Provides
    fun providesConnectivityManager(application: MyApplication): ConnectivityManager =
        application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun provideOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient
            .Builder()
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(httpLoggingInterceptor)
                }
                connectTimeout(45, TimeUnit.SECONDS)
                readTimeout(30, TimeUnit.SECONDS)
                writeTimeout(30, TimeUnit.SECONDS)
            }
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addConverterFactory(LiveDataResponseBodyConverterFactory())
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
    }

    @Provides
    fun provideApiService(retroFit: Retrofit): Apis {
        return retroFit.create(Apis::class.java)
    }


}