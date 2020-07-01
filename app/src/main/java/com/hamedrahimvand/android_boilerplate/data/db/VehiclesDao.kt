package com.hamedrahimvand.android_boilerplate.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hamedrahimvand.android_boilerplate.data.model.SampleModel

/**
 *
 *@author Hamed.Rahimvand
 *@since 6/13/20
 */
@Dao
interface VehiclesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(sampleModelList: List<SampleModel>)

    @Query("SELECT * FROM vehicles")
    fun loadAllVehicles(): LiveData<List<SampleModel>>
}
