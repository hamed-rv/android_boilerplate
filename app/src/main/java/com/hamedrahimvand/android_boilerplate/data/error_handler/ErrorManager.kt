package com.hamedrahimvand.android_boilerplate.data.error_handler

import android.accounts.NetworkErrorException
import com.hamedrahimvand.android_boilerplate.BuildConfig
import com.hamedrahimvand.android_boilerplate.data.model.Resource
import org.json.JSONObject
import retrofit2.HttpException
import java.net.ConnectException

/**
 *
 *@author Hamed.Rahimvand
 *@since 6/12/20
 */
class ErrorManager {

    object ErrorCodes {
        const val ERROR_EMPTY = 1
        const val ERROR_GENERAL = 400
        const val ERROR_AUTH_ERROR = 401
        const val PAYMENT_ERROR = 403
        const val ERROR_NETWORK = 404
        const val ERROR_CONFLICT = 409
        const val ERROR_INTERNAL_SERVER = 500
        const val FORCE_UPDATE_ERROR_CODE = 426
    }

    object ErrorMessage {
        const val NO_INTERNET_CONNECTION = "به اینترنت متصل نیستید"
        const val INTERNAL_SERVER_ERROR = "اشکالی در سرور رخ داده"
        const val CONNECTION_ERROR = "اشکال در اتصال"
        const val UNKNOWN_ERROR_OCCURRED = "خطای نامشخص"
        const val AUTHENTICATION = "توکن شما معتبر نیست"
        const val EMPTY_ERROR = "Fatality Error (Empty)"
        const val ERROR_CONFLICT = "اطلاعات صحیح نیستند"
    }

    companion object {
        fun handleError(e: Throwable?): ErrorModel {
            if (e == null) {
                return ErrorModel(
                    Exception(),
                    ErrorCodes.ERROR_GENERAL,
                    ErrorMessage.UNKNOWN_ERROR_OCCURRED,
                    false
                )
            }

            val errorModel: ErrorModel = when (e) {
                is HttpException -> getErrorModelOnHttpExceptionOccurred(e)
                is ConnectException -> getInternetConnectionErrorModel()
                is Resource.EmptyException -> getEmptyError(e)
                else -> getGeneralError()
            }
            e.printStackTrace()
            return errorModel
        }


        private fun getErrorModelOnHttpExceptionOccurred(
            e: HttpException
        ): ErrorModel {

            val response = e.response()
            val errorCode = response?.code()
            val errorMessage = StringBuilder()

            var errorJsonObject = JSONObject()
            try {
                errorJsonObject = JSONObject(response?.errorBody()?.string())
                val messageArray = errorJsonObject.getJSONArray("errors")
                for (i in 0 until messageArray.length()) {
                    errorMessage.append(messageArray[i])
                }
            } catch (e: Exception) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace()
                }
            }

            return getErrorModelOfErrorCode(errorCode, errorMessage.toString(), errorJsonObject)
        }

        private fun getErrorModelOfErrorCode(
            errorCode: Int?,
            errorMessage: String?,
            errorJsonObject: JSONObject
        ): ErrorModel {
            var result = errorMessage

            when (errorCode) {
                ErrorCodes.ERROR_INTERNAL_SERVER -> {
                    result = ErrorMessage.INTERNAL_SERVER_ERROR
                }
                ErrorCodes.ERROR_AUTH_ERROR -> {
                    result = ErrorMessage.AUTHENTICATION
                }
                ErrorCodes.ERROR_GENERAL -> {
                    result = errorMessage ?: ErrorMessage.CONNECTION_ERROR
                }
                ErrorCodes.ERROR_CONFLICT -> {
                    result = errorMessage ?: ErrorMessage.ERROR_CONFLICT
                }
                else -> {
                    if (result.isNullOrBlank()) {
                        result = ErrorMessage.CONNECTION_ERROR
                    }
                }
            }
            return ErrorModel(
                NetworkErrorException(),
                errorCode ?: ErrorCodes.ERROR_NETWORK,
                result,
                false,
                errorJsonObject
            )
        }

        private fun getEmptyError(e: Resource.EmptyException): ErrorModel {
            return ErrorModel(
                e,
                ErrorCodes.ERROR_EMPTY,
                ErrorMessage.EMPTY_ERROR,
                false
            )
        }

        private fun getGeneralError(): ErrorModel {
            return ErrorModel(
                Exception(),
                ErrorCodes.ERROR_GENERAL,
                ErrorMessage.CONNECTION_ERROR,
                true
            )
        }

        private fun getInternetConnectionErrorModel(): ErrorModel {
            return ErrorModel(
                Exception(),
                ErrorCodes.ERROR_GENERAL,
                ErrorMessage.NO_INTERNET_CONNECTION,
                true
            )
        }
    }

}
