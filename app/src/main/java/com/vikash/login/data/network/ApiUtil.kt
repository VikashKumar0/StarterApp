/*
 * Name: ApiUtil.kt
 * Created by: Vikash on 20/12/2022.
 * Copyright © 2022. All rights reserved.
 */

package com.vikash.login.data.network

import com.google.gson.JsonSyntaxException
import com.vikash.login.data.common.Error
import com.vikash.login.data.common.ErrorType
import com.vikash.login.data.common.NetworkError
import com.vikash.login.data.common.NetworkErrorCodes
import com.vikash.login.data.common.Result
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException
import javax.net.ssl.SSLHandshakeException

suspend fun <R> tryWithErrorWrapper(body: suspend () -> Result<Error, R>): Result<Error, R> {
    return try {
        body()
    } catch (e: UnknownHostException) {
        Result.failure(
            NetworkError(
                ErrorType.GENERIC_NETWORK_ERROR.code, 0, NetworkErrorCodes.UNKNOWN_HOST_EXCEPTION.code,
                e.message
                    ?: e.javaClass.simpleName
            )
        )
    } catch (e: IllegalArgumentException) {
        Result.failure(
            NetworkError(
                ErrorType.GENERIC_NETWORK_ERROR.code, 0, NetworkErrorCodes.ILLEGAL_ARGUMENT_EXCEPTION.code,
                e.message
                    ?: e.javaClass.simpleName
            )
        )
    } catch (e: SocketTimeoutException) {
        Result.failure(
            NetworkError(
                ErrorType.GENERIC_NETWORK_ERROR.code, 0, NetworkErrorCodes.SOCKET_TIMEOUT_EXCEPTION.code,
                e.message
                    ?: e.javaClass.simpleName
            )
        )
    } catch (e: SSLException) {
        Result.failure(
            NetworkError(
                ErrorType.GENERIC_NETWORK_ERROR.code, 0,
                NetworkErrorCodes.SSL_EXCEPTION.code,
                e.message
                    ?: e.javaClass.simpleName
            )
        )
    } catch (e: SSLHandshakeException) {
        Result.failure(
            NetworkError(
                ErrorType.GENERIC_NETWORK_ERROR.code, 0,
                NetworkErrorCodes.SSL_HANDSHAKE_EXCEPTION.code,
                e.message
                    ?: e.javaClass.simpleName
            )
        )
    } catch (e: NullPointerException) {
        Result.failure(
            NetworkError(
                ErrorType.GENERIC_NETWORK_ERROR.code, 0,
                NetworkErrorCodes.NULL_POINTER_EXCEPTION.code,
                e.message
                    ?: e.javaClass.simpleName
            )
        )
    } catch (e: JsonSyntaxException) {
        Result.failure(
            NetworkError(
                ErrorType.GENERIC_NETWORK_ERROR.code, 0,
                NetworkErrorCodes.JSON_SYNTAX_EXCEPTION.code,
                e.message
                    ?: e.javaClass.simpleName
            )
        )
    } catch (e: Exception) {
        Result.failure(
            NetworkError(
                ErrorType.GENERIC_NETWORK_ERROR.code, 0, NetworkErrorCodes.UNKNOWN_ERROR.code,
                e.message
                    ?: e.javaClass.simpleName
            )
        )
    }
}
