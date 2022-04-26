package com.way.cinemax.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.way.cinemax.data.model.ApiPopularResponse
import com.way.cinemax.data.util.Resource
import com.way.cinemax.domain.usecase.*
import com.way.cinemax.presentation.util.CheckPermission
import com.way.cinemax.presentation.util.CheckPermission.isNetworkAvailable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(
    private val app: Application,
    private val getPopularMovieUseCase: GetPopularMovieUseCase,
    private val getSearchMovieUseCase: GetSearchMovieUseCase,
    private val saveMovieUseCase: SaveMovieUseCase,
    private val deleteMovieUseCase: DeleteMovieUseCase,
    private val getFavoriteMovieUseCase: GetFavoriteMovieUseCase
) : AndroidViewModel(app) {
    val popularMovie: MutableLiveData<Resource<ApiPopularResponse>> = MutableLiveData()

    fun getPopularMovie(language: String, page: Int, region: String) {
        viewModelScope.launch(Dispatchers.IO) {
            popularMovie.postValue(Resource.Loading())
            try {
                if (isNetworkAvailable(app.applicationContext)) {
                    val apiResult = getPopularMovieUseCase.execute(language, page, region)
                    popularMovie.postValue(apiResult)
                } else {
                    popularMovie.postValue(Resource.Error("Network is not available!"))
                }
            } catch (e: Exception) {
                popularMovie.postValue(Resource.Error(e.message.toString()))
            }
        }
    }
}