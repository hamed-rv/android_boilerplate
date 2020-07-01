package com.hamedrahimvand.android_boilerplate.data.remote.retrofit

import androidx.lifecycle.MutableLiveData
import com.hamedrahimvand.android_boilerplate.data.model.Resource
import retrofit2.Call
class CallLiveData<T>(private val call: Call<T>): MutableLiveData<Resource<T>>() {

    fun cancel() {
        call.cancel()
    }
}