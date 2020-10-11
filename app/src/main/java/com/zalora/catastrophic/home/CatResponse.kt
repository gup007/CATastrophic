package com.zalora.catastrophic.home

sealed class CatResponse {

    internal data class Success(val newsData: List<Cat>) : CatResponse()

    internal data class Error(val error: String) : CatResponse()
}