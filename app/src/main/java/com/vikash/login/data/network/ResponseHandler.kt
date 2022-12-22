/*
 * Name: ChpNetworkUtil.kt
 * Created by: Vikash on 20/12/2022.
 * Copyright © 2022. All rights reserved.
 */

package com.vikash.login.data.network

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.vikash.login.data.common.Error
import com.vikash.login.data.common.ErrorType
import com.vikash.login.data.common.NetworkError
import com.vikash.login.data.common.NetworkErrorCodes
import com.vikash.login.data.common.Result
import retrofit2.Response

fun <T, V> Response<T>.handleResult(
    mapSuccess: (T) -> V,
): Result<Error, V> {
    return if (isSuccessful) {
        body()?.let {
            Result.success(mapSuccess(it))
        } ?: run {
            Result.failure(
                NetworkError(
                    ErrorType.GENERIC_NETWORK_ERROR.code,
                    code(),
                    NetworkErrorCodes.EMPTY_RESPONSE.code,
                    "No response body"
                )
            )
        }
    } else {
        val errorBody = errorBody()?.string()
        val error = try {
            Gson().fromJson(errorBody, NetworkError::class.java)
        } catch (e: JsonSyntaxException) {
            NetworkError(
                ErrorType.GENERIC_NETWORK_ERROR.code,
                code(),
                NetworkErrorCodes.MALFORMED_STATUS_BODY.code,
                errorBody ?: "Malformed error body"
            )
        }
        Result.failure(error.copy(data = errorBody))
    }
}
