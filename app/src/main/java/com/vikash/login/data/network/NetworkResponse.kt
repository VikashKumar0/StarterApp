/*
 * Name: NetworkResponse.kt
 * Created by: Vikash on 20/12/2022.
 * Copyright © 2022. All rights reserved.
 */

package com.vikash.login.data.network

import com.vikash.login.data.common.Error

/**
 * A mapping of the server's error pattern to a success or failure.
 */
sealed class NetworkResponse<out T> {
    data class Success<out T>(val value: T) : NetworkResponse<T>()
    data class Failure<T>(val error: Error) : NetworkResponse<T>()
}
