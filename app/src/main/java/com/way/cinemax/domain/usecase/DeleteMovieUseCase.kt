package com.way.cinemax.domain.usecase

import com.way.cinemax.data.model.Movie
import com.way.cinemax.domain.repository.MovieRepository

class DeleteMovieUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(movie: Movie) {
        return movieRepository.deleteMovie(movie)
    }
}