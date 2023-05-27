package com.gostev.news.data.api

import com.gostev.news.data.api.response.NewsResponse
import com.gostev.news.utils.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("/v2/everything")
    suspend fun getEverything(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey :String = API_KEY
    ): NewsResponse

    @GET("/v2/top-headlines")
    suspend fun getHeadLines(
        @Query("country") country: String = "ru",
        @Query("page") page: Int,
        @Query("apiKey") apiKey :String = API_KEY
    ) : NewsResponse
}