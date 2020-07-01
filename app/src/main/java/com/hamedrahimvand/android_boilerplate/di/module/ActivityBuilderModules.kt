package com.hamedrahimvand.android_boilerplate.di.module

import com.hamedrahimvand.android_boilerplate.presentation.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 *
 *@author Hamed.Rahimvand
 *@since 6/12/20
 */
@Module
abstract class ActivityBuilderModules {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}