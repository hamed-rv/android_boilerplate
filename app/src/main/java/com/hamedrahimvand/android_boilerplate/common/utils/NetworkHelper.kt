package com.hamedrahimvand.android_boilerplate.common.utils

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

/**
 *
 *@author Hamed.Rahimvand
 *@since 6/12/20
 */
object NetworkHelper {

    @Suppress("DEPRECATION")
    fun isInternetAvailable(connectivityManager: ConnectivityManager): Boolean {
        var result = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        ConnectivityManager.TYPE_VPN -> true
                        else -> false
                    }

                }
            }
        }

        return result
    }

}
