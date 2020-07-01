package com.hamedrahimvand.android_boilerplate.di.module.viewmodel

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

/**
 *
 *@author Hamed.Rahimvand
 *@since 6/12/20
 */
@Module
abstract class ViewModelFactoryBinding {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}