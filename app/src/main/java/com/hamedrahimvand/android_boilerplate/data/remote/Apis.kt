package com.hamedrahimvand.android_boilerplate.data.remote

import com.hamedrahimvand.android_boilerplate.data.model.response.SampleResponse
import com.hamedrahimvand.android_boilerplate.data.remote.retrofit.CallLiveData
import retrofit2.http.GET

/**
 *
 *@author Hamed.Rahimvand
 *@since 6/12/20
 */
interface Apis {
    @GET("assets/test/document.json")
    fun loadVehiclesAsync(): CallLiveData<SampleResponse>
}