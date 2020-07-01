package com.hamedrahimvand.android_boilerplate.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hamedrahimvand.android_boilerplate.data.error_handler.ErrorManager
import com.hamedrahimvand.android_boilerplate.data.model.ErrorObject
import com.hamedrahimvand.android_boilerplate.data.model.Resource
import com.hamedrahimvand.android_boilerplate.data.remote.retrofit.CallLiveData

/**
 *
 *@author Hamed.Rahimvand
 *@since 6/12/20
 */
abstract class BaseViewModel : ViewModel() {

    fun <T> callApi(
        source: LiveData<Resource<T>>,
        success: MediatorLiveData<T>? = null,
        error: MediatorLiveData<ErrorObject>,
        loading: MutableLiveData<Boolean>? = null
    ) {
        loading?.value = true
        success?.addSource(source) {
            if (it.status == Resource.Status.SUCCESS) {
                loading?.value = false
                success.value = it.data
            }
        }

        error.addSource(source) { exception ->
            if (exception.status == Resource.Status.ERROR) {
                val errorModel = ErrorManager.handleError(exception.error)
                if (errorModel.errorCode != ErrorManager.ErrorCodes.ERROR_EMPTY) {
                    loading?.value = false
                    error.value = ErrorObject(errorModel.errorMessage)
                }
            }
        }
    }
}