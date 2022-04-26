package com.way.cinemax.data.repository

import com.way.cinemax.data.model.ApiPopularResponse
import com.way.cinemax.data.model.Movie
import com.way.cinemax.data.repository.dataSource.MovieRemoteDataSource
import com.way.cinemax.data.util.Converters
import com.way.cinemax.data.util.Converters.fromResponseToResource
import com.way.cinemax.data.util.Resource
import com.way.cinemax.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {
    override suspend fun getPopularMovie(
        language: String,
        page: Int,
        region: String
    ): Resource<ApiPopularResponse> {
        return fromResponseToResource(
            movieRemoteDataSource.getPopularMovie(
               language, page, region
            )
        )
    }

    override suspend fun getSearchMovie(
        language: String,
        searchQuery: String,
        page: Int,
        region: String
    ): Resource<ApiPopularResponse> {
        return fromResponseToResource(
            movieRemoteDataSource.getSearchMovie(
                searchQuery,
                language,
                page,
                region
            )
        )
    }

    override suspend fun saveMovie(movie: Movie) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMovie(movie: Movie) {
        TODO("Not yet implemented")
    }

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        TODO("Not yet implemented")
    }
}