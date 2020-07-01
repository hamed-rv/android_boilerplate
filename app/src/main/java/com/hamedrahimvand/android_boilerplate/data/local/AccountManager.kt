package com.hamedrahimvand.android_boilerplate.data.local

import com.orhanobut.hawk.Hawk

/**
 * Created by Hamed.Rahimvand
 */
object AccountManager {

    private var userToken: String? = null
    private var userMobile: String? = null
    private var userAuthState = UserAuthState.GUEST

    private const val VERSION = ".v1"
    private const val USER_TOKEN = "token$VERSION"
    private const val USER_STATE = "userState"
    private const val USER_MOBILE = "userMobile"
    const val TOKEN_PREFIX = "Bearer"

    /**
     * user token save and retrieve
     */
    fun saveUserToken(token: String?) {
        if (token.isNullOrBlank()) {
            deleteUserToken()
        } else {
            userToken = token
            Hawk.put(USER_TOKEN, token)
            Hawk.put(USER_STATE, UserAuthState.SIGN_IN)
        }
    }
    fun saveUserMobile(mobile: String?) {
            userMobile = mobile
            Hawk.put(USER_MOBILE, mobile)
    }

    fun getCurrentUserToken(): String? =
        if (userToken.isNullOrBlank()) {
            if (Hawk.contains(USER_TOKEN))
                Hawk.get<String>(USER_TOKEN)
            else
                null
        } else {
            userToken
        }

    fun getUserAuthState(): UserAuthState =
        if (Hawk.contains(USER_STATE))
            Hawk.get(USER_STATE)
        else
            userAuthState

    fun getUserMobile(): String =
        if (Hawk.contains(USER_MOBILE))
            Hawk.get(USER_MOBILE,"")
        else
            userMobile ?: ""

    fun signOut() {
        deleteUser()
        deleteUserToken()
        changeUserStateToGuest()
    }

    private fun deleteUser() {
        userToken = null
        Hawk.deleteAll()
    }

    private fun deleteUserToken() {
        Hawk.delete(USER_TOKEN)
        userToken = null
    }

    private fun changeUserStateToGuest() {
        Hawk.put(USER_STATE, UserAuthState.GUEST)
        userAuthState = UserAuthState.GUEST
    }


}

enum class UserAuthState {
    GUEST, SIGN_IN
}