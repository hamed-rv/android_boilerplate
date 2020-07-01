package com.hamedrahimvand.android_boilerplate.di.module.viewmodel

import androidx.lifecycle.ViewModel
import com.hamedrahimvand.android_boilerplate.presentation.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 *
 *@author Hamed.Rahimvand
 *@since 6/12/20
 */
@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

}