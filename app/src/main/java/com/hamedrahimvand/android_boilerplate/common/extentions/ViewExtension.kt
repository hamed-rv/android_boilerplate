package com.hamedrahimvand.android_boilerplate.common.extentions

import android.view.View

/**
 *
 *@author Hamed.Rahimvand
 *@since 6/13/20
 */
fun View.hide(keepSpace: Boolean = true) {
    if (visibility == View.VISIBLE) {
        visibility = if (keepSpace) {
            View.INVISIBLE
        } else {
            View.GONE
        }
    }
}

fun View.show(): View {
    if (visibility != View.VISIBLE)
        visibility = View.VISIBLE
    return this
}