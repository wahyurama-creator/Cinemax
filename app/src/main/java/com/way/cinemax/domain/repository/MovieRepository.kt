package com.way.cinemax.domain.repository

import com.way.cinemax.data.model.ApiPopularResponse
import com.way.cinemax.data.model.Movie
import com.way.cinemax.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPopularMovie(language: String, page: Int, region: String) : Resource<ApiPopularResponse>
    suspend fun getSearchMovie(language: String, searchQuery: String, page: Int,  region: String) : Resource<ApiPopularResponse>
    suspend fun saveMovie(movie: Movie)
    suspend fun deleteMovie(movie: Movie)
    fun getFavoriteMovies(): Flow<List<Movie>>
}