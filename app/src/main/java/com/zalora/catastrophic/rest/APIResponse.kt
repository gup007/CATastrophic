package com.zalora.catastrophic.rest

abstract class APIResponse {

    companion object {
        val VIEW_TYPE_NORMAL = 1
        val VIEW_TYPE_LOADING = 2
    }

    fun isValid(): Boolean {
        return true
    }
}