package com.zalora.catastrophic.rest.exception

interface ApiErrorCode {

    companion object {
        val UNAUTHORIZE_ACCESS = 401
        val USER_SESSION_EXPIRE = 421
        val CONFLICT = 409
        val ERROR_CODE_409 = 409
    }

}
