package com.vikash.login.data.models

data class LoginRequest(val user: LoginUser)
data class LoginUser(val email: String, val password: String)
