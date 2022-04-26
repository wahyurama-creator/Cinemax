package com.way.cinemax.data.api

import com.way.cinemax.BuildConfig
import com.way.cinemax.data.model.ApiPopularResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("3/movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY,
        @Query("language")
        language: String = BuildConfig.language,
        @Query("page")
        page: Int,
        @Query("region")
        region: String = BuildConfig.region,
    ): Response<ApiPopularResponse>

    @GET("3/search/movie")
    suspend fun getSearchMovie(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY,
        @Query("language")
        language: String = BuildConfig.language,
        @Query("query")
        searchQuery: String,
        @Query("page")
        page: Int,
        @Query("region")
        region: String = BuildConfig.region,
    ): Response<ApiPopularResponse>
}