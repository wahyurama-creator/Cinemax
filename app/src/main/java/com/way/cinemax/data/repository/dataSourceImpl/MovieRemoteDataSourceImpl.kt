package com.way.cinemax.data.repository.dataSourceImpl

import com.way.cinemax.data.api.ApiService
import com.way.cinemax.data.model.ApiPopularResponse
import com.way.cinemax.data.repository.dataSource.MovieRemoteDataSource
import retrofit2.Response

class MovieRemoteDataSourceImpl(
    private val apiService: ApiService
) : MovieRemoteDataSource {
    override suspend fun getPopularMovie(
        language: String,
        page: Int,
        region: String
    ): Response<ApiPopularResponse> {
        return apiService.getPopularMovie(language = language, page = page, region = region)
    }

    override suspend fun getSearchMovie(
        language: String,
        searchQuery: String,
        page: Int,
        region: String
    ): Response<ApiPopularResponse> {
        return apiService.getSearchMovie(
            language = language,
            searchQuery = searchQuery,
            page = page,
            region = region
        )
    }
}