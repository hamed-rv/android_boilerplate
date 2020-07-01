package com.hamedrahimvand.android_boilerplate.di.component

import com.hamedrahimvand.android_boilerplate.MyApplication
import com.hamedrahimvand.android_boilerplate.di.module.*
import com.hamedrahimvand.android_boilerplate.di.module.viewmodel.ViewModelFactoryBinding
import com.hamedrahimvand.android_boilerplate.di.module.viewmodel.ViewModelsModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


/**
 *
 *@author Hamed.Rahimvand
 *@since 6/12/20
 */
@Singleton
@Component(
    modules = [ApplicationModule::class,
        ViewModelsModule::class,
        ViewModelFactoryBinding::class,
        AndroidInjectionModule::class,
        ActivityBuilderModules::class,
        FragmentBuilderModules::class,
        RepositoryModule::class,
        DatabaseModule::class]
)
interface AppComponent : AndroidInjector<MyApplication?> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: MyApplication): AppComponent
    }

}