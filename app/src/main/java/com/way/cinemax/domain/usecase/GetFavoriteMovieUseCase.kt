package com.way.cinemax.domain.usecase

import com.way.cinemax.data.model.Movie
import com.way.cinemax.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteMovieUseCase(private val movieRepository: MovieRepository) {
    fun execute(): Flow<List<Movie>> {
        return movieRepository.getFavoriteMovies()
    }
}