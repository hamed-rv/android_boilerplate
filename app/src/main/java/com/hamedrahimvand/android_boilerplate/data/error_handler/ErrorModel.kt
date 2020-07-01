package com.hamedrahimvand.android_boilerplate.data.error_handler

import android.accounts.NetworkErrorException
import org.json.JSONObject
import retrofit2.HttpException


/**
 *
 *@author Hamed.Rahimvand
 *@since 6/12/20
 */
class ErrorModel {
    val e: Exception?
    val errorCode: Int
    var errorMessage: String = ""
    var errorMessageId: Int? = null
    val isConnectionError: Boolean
    var rawJsonResponse: JSONObject? = null

    constructor(
        e: Exception?,
        errorCode: Int,
        errorMessage: String,
        isConnectionError: Boolean,
        rawJsonResponse: JSONObject? = null
    ) {
        this.e = e
        this.errorCode = errorCode
        this.errorMessage = errorMessage
        this.isConnectionError = isConnectionError
        this.rawJsonResponse = rawJsonResponse
    }

    constructor(
        e: Exception?,
        errorCode: Int,
        errorMessageId: Int,
        isConnectionError: Boolean,
        rawJsonResponse: JSONObject? = null
    ) {
        this.e = e
        this.errorCode = errorCode
        this.errorMessageId = errorMessageId
        this.isConnectionError = isConnectionError
        this.rawJsonResponse = rawJsonResponse
    }

    fun hasId(): Boolean = errorMessage.isNullOrEmpty() && errorMessageId != 0

    fun isHttpException() = e is HttpException || e is NetworkErrorException
}

fun ErrorModel.getUnitIdFromError() = runCatching {
    if (rawJsonResponse == null) {
        return@runCatching null
    }
    if (rawJsonResponse!!.has("unit_id")) {
        rawJsonResponse!!.getString("unit_id")
    } else {
        return@runCatching null
    }
}.getOrNull()