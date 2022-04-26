package com.way.cinemax.presentation.di

import com.way.cinemax.data.repository.MovieRepositoryImpl
import com.way.cinemax.data.repository.dataSource.MovieRemoteDataSource
import com.way.cinemax.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(movieRemoteDataSource)
    }
}