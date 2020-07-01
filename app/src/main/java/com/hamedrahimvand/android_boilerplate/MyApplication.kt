package com.hamedrahimvand.android_boilerplate

import com.hamedrahimvand.android_boilerplate.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 *
 *@author Hamed.Rahimvand
 *@since 6/12/20
 */
class MyApplication : DaggerApplication(), HasAndroidInjector {

    override fun applicationInjector() = DaggerAppComponent.factory().create(this)

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>


    override fun androidInjector(): AndroidInjector<Any> = androidInjector


}