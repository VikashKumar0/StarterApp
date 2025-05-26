/*
 * Name: ChpNetworkError.kt
 * Created by: Vikash on 20/12/2022.
 * Copyright © 2022 . All rights reserved.
 */

package com.vikash.login.data.common

import com.google.gson.annotations.SerializedName

/**
 * CHP error responses.
 */
data class NetworkError(
    override val type: Int,
    override val status: Int,
    @SerializedName("errorCode")
    override val code: Int,
    override val message: String,
    override var data: String? = null,
) : Error
