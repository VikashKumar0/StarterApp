package com.vikash.login.data

import com.vikash.login.data.models.LoginRequest
import com.vikash.login.data.models.SignupRequest
import com.vikash.login.data.models.LoginResponse
import retrofit2.Response
import retrofit2.http.*


interface RetrofitApi {

    @POST("/users/login")
    suspend fun login(
        @HeaderMap header: Map<String, String>,
        @Body body: LoginRequest
    ): Response<LoginResponse>

    @POST("users")
    suspend fun signup(
        @HeaderMap header: Map<String, String>,
        @Body body: SignupRequest
    ): Response<LoginResponse>

}