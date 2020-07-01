package com.hamedrahimvand.android_boilerplate.data.remote.retrofit

import androidx.lifecycle.LiveData
import com.hamedrahimvand.android_boilerplate.data.model.Resource
import retrofit2.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * A [CallAdapter.Factory] for use with Kotlin coroutines.
 *
 * Adding this class to [Retrofit] allows you to return [Deferred] from
 * service methods.
 *
 *     interface MyService {
 *       &#64;GET("user/me")
 *       Deferred&lt;User&gt; getUser()
 *     }
 *
 * There are two configurations supported for the [Deferred] type parameter:
 *
 * * Direct body (e.g., `Deferred<User>`) returns the deserialized body for 2XX responses, throws
 * [HttpException] errors for non-2XX responses, and throws [IOException][java.io.IOException] for
 * network errors.
 * * Response wrapped body (e.g., `Deferred<Response<User>>`) returns a [Response] object for all
 * HTTP responses and throws [IOException][java.io.IOException] for network errors
 */
class LiveDataCallAdapterFactory private constructor() : CallAdapter.Factory() {
    companion object {
        @JvmStatic @JvmName("create")
        operator fun invoke() = LiveDataCallAdapterFactory()
    }

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (CallLiveData::class.java != getRawType(returnType)) {
            return null
        }
        check(returnType is ParameterizedType) { "Deferred return type must be parameterized as Deferred<Foo> or Deferred<out Foo>" }
        val responseType = getParameterUpperBound(0, returnType)

        val rawDeferredType = getRawType(responseType)
        return if (rawDeferredType == Response::class.java) {
            if (responseType !is ParameterizedType) {
                throw IllegalStateException(
                    "Response must be parameterized as Response<Foo> or Response<out Foo>")
            }
            ResponseCallAdapter<Any>(getParameterUpperBound(0, responseType))
        } else {
            BodyCallAdapter<Any>(responseType)
        }
    }

    private class BodyCallAdapter<T>(
        private val responseType: Type
    ) : CallAdapter<T, LiveData<Resource<T>>> {

        override fun responseType() = responseType

        override fun adapt(call: Call<T>): CallLiveData<T> {
            val liveData = CallLiveData(call)

            if (call.isCanceled) { 
                return liveData
            }

            call.enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    if (call.isCanceled) return
                    liveData.postValue(Resource.error(t, null))
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    if (call.isCanceled) return

                    if (response.isSuccessful) {
                        liveData.postValue(Resource.success(response.body()))
                    } else if(response.body() == null || response.code() == 204) {
                        liveData.postValue(Resource.emptyError())
                    }else {
                        liveData.postValue(Resource.error(HttpException(response), null))
                    }
                }
            })

            return liveData
        }
    }

    private class ResponseCallAdapter<T>(
        private val responseType: Type
    ) : CallAdapter<T, LiveData<Resource<T>>> {

        override fun responseType() = responseType

        override fun adapt(call: Call<T>): CallLiveData<T> {
            val liveData = CallLiveData(call)

            if (call.isCanceled) {
                return liveData
            }

            call.enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    if (call.isCanceled) return
                    liveData.postValue(Resource.error(t, null))
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    if (call.isCanceled) return
                    liveData.postValue(Resource.success(response.body()))
                }
            })

            return liveData
        }
    }
}
