package com.way.cinemax.presentation.di

import android.app.Application
import com.way.cinemax.domain.usecase.*
import com.way.cinemax.presentation.viewmodel.MovieViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Provides
    @Singleton
    fun provideFactoryMovieViewModelFactory(
        app: Application,
        getPopularMovieUseCase: GetPopularMovieUseCase,
        getSearchMovieUseCase: GetSearchMovieUseCase,
        saveMovieUseCase: SaveMovieUseCase,
        deleteMovieUseCase: DeleteMovieUseCase,
        getFavoriteMovieUseCase: GetFavoriteMovieUseCase
    ): MovieViewModelFactory {
        return MovieViewModelFactory(
            app,
            getPopularMovieUseCase,
            getSearchMovieUseCase,
            saveMovieUseCase,
            deleteMovieUseCase,
            getFavoriteMovieUseCase
        )
    }
}