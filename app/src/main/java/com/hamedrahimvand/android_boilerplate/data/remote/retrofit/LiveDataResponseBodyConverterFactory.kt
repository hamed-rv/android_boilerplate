package com.hamedrahimvand.android_boilerplate.data.remote.retrofit

import androidx.annotation.Nullable
import com.bumptech.glide.load.engine.Resource
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


class LiveDataResponseBodyConverterFactory private constructor() : Converter.Factory() {


    @Nullable
    override fun responseBodyConverter(
        type: Type, annotations: Array<Annotation?>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        if (type is ParameterizedType) {
            var parameterizedType: ParameterizedType = type
            if (parameterizedType.rawType === Response::class.java) {
                val subType: Type = parameterizedType.actualTypeArguments[0]
                if (subType is ParameterizedType) {
                    parameterizedType =
                        parameterizedType.actualTypeArguments[0] as ParameterizedType
                }
            }
            if (parameterizedType.rawType === Resource::class.java) {
                val realType: Type = parameterizedType.actualTypeArguments[0]
                return retrofit.nextResponseBodyConverter<Any>(this, realType, annotations)
            }
        }
        return retrofit.nextResponseBodyConverter<Any>(this, type, annotations)
    }

    companion object {

        @JvmStatic @JvmName("create")
        operator fun invoke() = LiveDataResponseBodyConverterFactory()

    }
}