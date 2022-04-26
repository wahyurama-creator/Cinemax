package com.way.cinemax.presentation.di

import com.way.cinemax.domain.repository.MovieRepository
import com.way.cinemax.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideGetPopularMovieUseCase(movieRepository: MovieRepository): GetPopularMovieUseCase {
        return GetPopularMovieUseCase(movieRepository)
    }

    @Provides
    @Singleton
    fun provideGetSearchMovieUseCase(movieRepository: MovieRepository): GetSearchMovieUseCase {
        return GetSearchMovieUseCase(movieRepository)
    }

    @Provides
    @Singleton
    fun provideGetFavoriteMovieUseCase(movieRepository: MovieRepository): GetFavoriteMovieUseCase {
        return GetFavoriteMovieUseCase(movieRepository)
    }

    @Provides
    @Singleton
    fun provideSaveMovieUseCase(movieRepository: MovieRepository): SaveMovieUseCase {
        return SaveMovieUseCase(movieRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteMovieUseCase(movieRepository: MovieRepository): DeleteMovieUseCase {
        return DeleteMovieUseCase(movieRepository)
    }
}

