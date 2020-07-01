package com.hamedrahimvand.android_boilerplate.data.repository.data_source

import com.hamedrahimvand.android_boilerplate.data.remote.Apis
import javax.inject.Inject

/**
 *
 *@author Hamed.Rahimvand
 *@since 6/12/20
 */
class CloudDataSource @Inject constructor(private val apis: Apis){

    fun loadAllVehiclesAsync() = apis.loadVehiclesAsync()
}