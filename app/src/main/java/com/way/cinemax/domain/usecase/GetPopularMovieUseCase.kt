package com.way.cinemax.domain.usecase

import com.way.cinemax.data.model.ApiPopularResponse
import com.way.cinemax.data.util.Resource
import com.way.cinemax.domain.repository.MovieRepository

class GetPopularMovieUseCase(
    private val movieRepository: MovieRepository
) {
    suspend fun execute(
        language: String,
        page: Int,
        region: String
    ): Resource<ApiPopularResponse> {
        return movieRepository.getPopularMovie(language, page, region)
    }
}