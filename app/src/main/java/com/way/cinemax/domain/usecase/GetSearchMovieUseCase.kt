package com.way.cinemax.domain.usecase

import com.way.cinemax.data.model.ApiPopularResponse
import com.way.cinemax.data.util.Resource
import com.way.cinemax.domain.repository.MovieRepository

class GetSearchMovieUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(
        language: String,
        searchQuery: String,
        page: Int,
        region: String
    ): Resource<ApiPopularResponse> {
        return movieRepository.getSearchMovie(language, searchQuery, page, region)
    }
}