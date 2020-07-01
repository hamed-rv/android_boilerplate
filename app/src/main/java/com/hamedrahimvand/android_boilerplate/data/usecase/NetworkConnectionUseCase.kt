package com.hamedrahimvand.android_boilerplate.data.usecase

import androidx.lifecycle.LiveData
import com.hamedrahimvand.android_boilerplate.data.model.response.SampleResponse
import com.hamedrahimvand.android_boilerplate.data.remote.retrofit.CallLiveData
import com.hamedrahimvand.android_boilerplate.data.repository.Repository
import javax.inject.Inject

/**
 *
 *@author Hamed.Rahimvand
 *@since 6/25/20
 */

class NetworkConnectionUseCase @Inject constructor(val repository: Repository){
    fun isConnectedToNetwork(): Boolean {
        return repository.isConnectedToNetwork()
    }
}