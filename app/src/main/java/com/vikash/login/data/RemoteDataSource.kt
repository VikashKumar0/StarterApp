package com.vikash.login.data

import com.vikash.login.data.common.Error
import com.vikash.login.data.common.Result
import com.vikash.login.data.models.LoginRequest
import com.vikash.login.data.models.LoginUser
import com.vikash.login.data.models.SignupRequest
import com.vikash.login.data.models.LoginResponse
import com.vikash.login.data.network.RetrofitUtil
import com.vikash.login.data.network.handleResult
import com.vikash.login.data.network.tryWithErrorWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class RemoteDataSource @Inject constructor(){
    private val api = RetrofitUtil.createRetrofitApi(Companion.BASE_URL)

    companion object {
        private const val BASE_URL = "https://conduit.productionready.io/api/"
    }


    private fun getHeaderMap():  Map<String, String> {
        val headerMap = mutableMapOf<String, String>()
        headerMap["Content-Type"] = "application/json"
        headerMap["X-Requested-With"] = "XMLHttpRequest"
        return headerMap
    }


    suspend fun login(email: String, password: String): Result<Error, LoginResponse> {
        return withContext(Dispatchers.IO) {
            tryWithErrorWrapper {
                api.login(
                    getHeaderMap(),
                    body = LoginRequest(LoginUser( email, password))
                ).handleResult {
                    it
                }
            }
        }
    }

    suspend fun signUp(email: String, password: String, userName: String): Result<Error, LoginResponse> {
        return withContext(Dispatchers.IO) {
            tryWithErrorWrapper {
                api.signup(
                    getHeaderMap(),
                    body = SignupRequest(email, password, userName)
                ).handleResult {
                    it
                }
            }
        }
    }


}