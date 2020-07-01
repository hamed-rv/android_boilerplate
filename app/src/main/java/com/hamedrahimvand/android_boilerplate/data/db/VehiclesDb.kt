package com.hamedrahimvand.android_boilerplate.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hamedrahimvand.android_boilerplate.data.model.SampleModel

/**
 *
 *@author Hamed.Rahimvand
 *@since 6/13/20
 */
@Database(
    entities = [
        SampleModel::class
    ],
    version = 1,
    exportSchema = false
)
abstract class VehiclesDb : RoomDatabase() {
    abstract fun vehiclesDao(): VehiclesDao

}