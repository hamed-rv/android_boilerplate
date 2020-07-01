package com.hamedrahimvand.android_boilerplate.data.model
import android.content.Context
import androidx.annotation.StringRes

/**
 *
 *@author Hamed.Rahimvand
 *@since 6/12/20
 */
data class ErrorObject constructor( var message: String? = null, @StringRes  var resId: Int? = null) {
    constructor(): this(null,null)
    fun get(context: Context): String {
        return message ?: if (resId != null) context.getString(resId!!) else "No message"
    }
    fun get(): String {
        return message ?: "No message"
    }

    companion object {}

}