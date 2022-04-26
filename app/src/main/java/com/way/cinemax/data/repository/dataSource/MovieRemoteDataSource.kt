package com.way.cinemax.data.repository.dataSource

import com.way.cinemax.data.model.ApiPopularResponse
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun getPopularMovie(
        language: String,
        page: Int,
        region: String
    ): Response<ApiPopularResponse>

    suspend fun getSearchMovie(
        language: String,
        searchQuery: String,
        page: Int,
        region: String
    ): Response<ApiPopularResponse>
}