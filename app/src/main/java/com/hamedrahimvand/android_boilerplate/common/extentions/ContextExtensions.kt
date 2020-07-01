package com.hamedrahimvand.android_boilerplate.common.extentions

import android.content.Context
import android.widget.Toast

/**
 *@author Hamed.Rahimvand
 *@since 6/12/20
 */
fun Context.showToast(
    message: String,
    length: Int = Toast.LENGTH_SHORT
) {
    Toast.makeText(applicationContext, message, length).show()
}