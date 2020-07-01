package com.hamedrahimvand.android_boilerplate.data.repository.data_source

import com.hamedrahimvand.android_boilerplate.data.db.VehiclesDao
import com.hamedrahimvand.android_boilerplate.data.db.VehiclesDb
import com.hamedrahimvand.android_boilerplate.data.model.SampleModel
import javax.inject.Inject

/**
 *
 *@author Hamed.Rahimvand
 *@since 6/12/20
 */
class LocalDataSource @Inject constructor(
    private val vehiclesDb: VehiclesDb,
    private val vehiclesDao: VehiclesDao
){

    fun loadAllVehicles() = vehiclesDao.loadAllVehicles()

    fun insertVehicles(sampleModelList: List<SampleModel>) = vehiclesDao.insert(sampleModelList)
}