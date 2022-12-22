/*
 * Name: Error.kt
 * Created by: Vikash on 20/12/2022.
 * Copyright © 2022 . All rights reserved.
 */

package com.vikash.login.data.common

/**
 * A generic holder for cloud error responses.
 */
interface Error {
    val type: Int
    val status: Int
    val code: Int
    val message: String

    // raw data which consumer needs to be parse
    val data: String?
}
