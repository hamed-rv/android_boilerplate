package com.hamedrahimvand.android_boilerplate.di.module

import androidx.room.Room
import com.hamedrahimvand.android_boilerplate.MyApplication
import com.hamedrahimvand.android_boilerplate.data.db.VehiclesDao
import com.hamedrahimvand.android_boilerplate.data.db.VehiclesDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *
 *@author Hamed.Rahimvand
 *@since 6/13/20
 */

@Module
class DatabaseModule{
    @Singleton
    @Provides
    fun provideVehiclesDb(application: MyApplication): VehiclesDb {
        return Room
            .databaseBuilder(application, VehiclesDb::class.java, "vehicles_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideVehiclesDao(db: VehiclesDb): VehiclesDao {
        return db.vehiclesDao()
    }
}