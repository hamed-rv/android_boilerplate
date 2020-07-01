package com.hamedrahimvand.android_boilerplate.di.module

import com.hamedrahimvand.android_boilerplate.data.repository.Repository
import com.hamedrahimvand.android_boilerplate.data.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 *
 *@author Hamed.Rahimvand
 *@since 6/13/20
 */
@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun providesRepository(repository: RepositoryImpl): Repository
}