/*
 * Name: NetworkErrorCodes.kt
 * Created by: Vikash on 20/12/2022.
 * Copyright © 2022 . All rights reserved.
 */

package com.vikash.login.data.common

// refer to https://confluence.netgear.com/pages/viewpage.action?pageId=94349882
enum class NetworkErrorCodes(val code: Int) {
    UNKNOWN_ERROR(-1),
    NULL_POINTER_EXCEPTION(9914),
    EMPTY_RESPONSE(9915),
    NO_INTERNET_CONNECTION(9916),
    API_WORKER_CANCELLED(9919),
    API_WORKER_UNKNOWN_STATE(9920),
    MALFORMED_STATUS_BODY(-303),
    JSON_SYNTAX_EXCEPTION(-304),
    SSL_EXCEPTION(-305),
    SSL_HANDSHAKE_EXCEPTION(-306),
    ILLEGAL_ARGUMENT_EXCEPTION(-307),
    SOCKET_TIMEOUT_EXCEPTION(-308),
    UNKNOWN_HOST_EXCEPTION(-309),
    HTTP_CODE_NOT_FOUND(404);

    companion object {
        fun from(value: Int?): NetworkErrorCodes? {
            return values().firstOrNull { it.code == value }
        }
    }
}
