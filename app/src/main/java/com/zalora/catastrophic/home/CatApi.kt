package com.zalora.catastrophic.home

import com.zalora.catastrophic.home.room.Cat
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {

    @GET("/v1/images/search")
    suspend fun getCatsAsync(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("mime_types") mimeTypes: String
    ): List<Cat>

}