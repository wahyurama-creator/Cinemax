package com.way.cinemax.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.way.cinemax.domain.usecase.*

@Suppress("UNCHECKED_CAST")
class MovieViewModelFactory(
    private val app: Application,
    private val getPopularMovieUseCase: GetPopularMovieUseCase,
    private val getSearchMovieUseCase: GetSearchMovieUseCase,
    private val saveMovieUseCase: SaveMovieUseCase,
    private val deleteMovieUseCase: DeleteMovieUseCase,
    private val getFavoriteMovieUseCase: GetFavoriteMovieUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                return MovieViewModel(
                    app,
                    getPopularMovieUseCase,
                    getSearchMovieUseCase,
                    saveMovieUseCase,
                    deleteMovieUseCase,
                    getFavoriteMovieUseCase
                ) as T
            }
            else -> {
                throw IllegalArgumentException("Unknown View Model Class")
            }
        }
    }
}