package com.hamedrahimvand.android_boilerplate.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.hamedrahimvand.android_boilerplate.common.base.BaseViewModel
import com.hamedrahimvand.android_boilerplate.data.model.ErrorObject
import com.hamedrahimvand.android_boilerplate.data.model.response.SampleResponse
import com.hamedrahimvand.android_boilerplate.data.repository.Repository
import com.hamedrahimvand.android_boilerplate.data.usecase.LoadSampleUseCase
import com.hamedrahimvand.android_boilerplate.data.usecase.NetworkConnectionUseCase
import javax.inject.Inject

/**
 *
 *@author Hamed.Rahimvand
 *@since 6/12/20
 */

class MainViewModel @Inject constructor(val loadSampleUseCase: LoadSampleUseCase, val networkConnectionUseCase: NetworkConnectionUseCase) :
    BaseViewModel() {
    private val loadSampleSuccessLiveData = MediatorLiveData<SampleResponse>()
    private val loadSampleErrorLiveData = MediatorLiveData<ErrorObject>()
    private val loadSampleLoadingLiveData = MediatorLiveData<Boolean>()
    fun getSampleSuccessLiveData() = loadSampleSuccessLiveData as LiveData<SampleResponse>
    fun getSampleErrorLiveData() = loadSampleErrorLiveData as LiveData<ErrorObject>
    fun getSampleLoadingLiveData() = loadSampleLoadingLiveData as LiveData<Boolean>

    fun loadSample() {
        callApi(
            loadSampleUseCase.loadSample(),
            loadSampleSuccessLiveData,
            loadSampleErrorLiveData,
            loadSampleLoadingLiveData
        )
    }

    fun isConnectedToNetwork() = networkConnectionUseCase.isConnectedToNetwork()
}
