package com.vikash.login.ui.model

import com.google.firebase.auth.FirebaseUser
import com.vikash.login.data.network.User

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