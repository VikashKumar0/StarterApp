/*
 * Name: ErrorType.kt
 * Created by: Vikash on 20/12/2022.
 * Copyright © 2022 . All rights reserved.
 */

package com.vikash.login.data.common

enum class ErrorType(val code: Int) {
    SERVER_ERROR(0),
    GENERIC_NETWORK_ERROR(-1),
    PERMISSION_ERROR(-2);

    companion object {
        fun from(value: Int?): ErrorType? {
            return values().firstOrNull { it.code == value }
        }
    }
}
