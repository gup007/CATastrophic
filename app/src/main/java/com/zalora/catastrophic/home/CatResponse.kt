package com.zalora.catastrophic.home

import com.zalora.catastrophic.home.room.Cat

sealed class CatResponse {

    internal data class Success(val catData: List<Cat>) : CatResponse()

    internal data class Error(val error: String) : CatResponse()
}