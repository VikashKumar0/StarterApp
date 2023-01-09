package com.vikash.login.ui.model

import com.vikash.login.data.models.User

object LoginState {

    var isLoggedIn = false
    var user: User? = null

    fun logIn(user: User?){
        isLoggedIn = true
        LoginState.user = user
    }

    fun logout(){
        isLoggedIn = false
        user = null
    }
}