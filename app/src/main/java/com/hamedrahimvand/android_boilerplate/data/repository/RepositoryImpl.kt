package com.hamedrahimvand.android_boilerplate.data.repository

import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.hamedrahimvand.android_boilerplate.AppExecutors
import com.hamedrahimvand.android_boilerplate.common.utils.NetworkHelper
import com.hamedrahimvand.android_boilerplate.data.model.Resource
import com.hamedrahimvand.android_boilerplate.data.model.response.SampleResponse
import com.hamedrahimvand.android_boilerplate.data.repository.data_source.CloudDataSource
import com.hamedrahimvand.android_boilerplate.data.repository.data_source.LocalDataSource
import javax.inject.Inject

/**
 *
 *@author Hamed.Rahimvand
 *@since 6/12/20
 */

class RepositoryImpl @Inject constructor(
    val cloudDataSource: CloudDataSource,
    val localDataSource: LocalDataSource,
    val connectivityManager: ConnectivityManager,
    val appExecutors: AppExecutors
) : Repository {

    override fun isConnectedToNetwork(): Boolean {
        return NetworkHelper.isInternetAvailable(connectivityManager)
    }

    override fun loadSample(): LiveData<Resource<SampleResponse>> {
        return object : NetworkBoundResource<SampleResponse, SampleResponse>(appExecutors) {
            override fun saveCallResult(item: SampleResponse) {
                localDataSource.insertVehicles(item.sampleList)
            }

            override fun shouldFetch(data: SampleResponse?) = isConnectedToNetwork() || data == null || data.sampleList.isEmpty()

            override fun loadFromDb() = Transformations.map(localDataSource.loadAllVehicles()) {
                SampleResponse(it)
            }

            override fun createCall() = cloudDataSource.loadAllVehiclesAsync()
        }.asLiveData()
    }

}