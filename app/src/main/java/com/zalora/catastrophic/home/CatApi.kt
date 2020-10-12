package com.zalora.catastrophic.home

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {

    @GET("/v1/images/search")
    fun getCatsAsync(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("mime_types") mimeTypes: String,
        @Query("order") order: String
    ): Call<List<Cat>>

}