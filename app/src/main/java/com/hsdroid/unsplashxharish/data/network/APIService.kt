package com.hsdroid.unsplashxharish.data.network

import com.hsdroid.unsplashxharish.data.models.ServerResponseItem
import com.hsdroid.unsplashxharish.utils.Helper
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface APIService {

    @GET("/photos")
    suspend fun getImages(
        @Query("client_id") clientId: String,
        @Query("page") pageNo: Int,
        @Query("per_page") perPage : Int
    ): List<ServerResponseItem>
}