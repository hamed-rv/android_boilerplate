package com.hamedrahimvand.android_boilerplate.data.model

import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.lang.Exception

data class Resource<out T>(val status: Status, val data: T?, val error: Throwable?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(error: Throwable?, data: T?): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                error
            )
        }

        fun <T> errorHttp(error: String?, data: T?): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                HttpException(Response.error<String>(400, ResponseBody.create(null, JSONObject().put("errors", JSONArray().put( error ?: "Http Error")).toString())))
            )
        }

        fun <T> emptyError(): Resource<T> {
            return Resource(
                Status.EMPTY,
                null,
                EmptyException()
            )
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }
    }

    enum class Status {
        SUCCESS,
        ERROR,
        EMPTY,
        LOADING
    }

    enum class UploadState {
        UPLOADING,
        DEFAULT,
        ERROR,
        CANCEL
    }

    class EmptyException: Exception()
}