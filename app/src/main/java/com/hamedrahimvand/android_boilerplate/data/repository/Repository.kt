package com.hamedrahimvand.android_boilerplate.data.repository

import androidx.lifecycle.LiveData
import com.hamedrahimvand.android_boilerplate.data.model.Resource
import com.hamedrahimvand.android_boilerplate.data.model.response.SampleResponse

/**
 *
 *@author Hamed.Rahimvand
 *@since 6/12/20
 */
interface Repository{
    fun isConnectedToNetwork(): Boolean
    fun loadSample(): LiveData<Resource<SampleResponse>>
}