package com.way.cinemax.data.util

import com.way.cinemax.data.model.ApiPopularResponse
import retrofit2.Response

object Converters {
    fun fromResponseToResource(
        response: Response<ApiPopularResponse>
    ): Resource<ApiPopularResponse> {
        if (response.isSuccessful) {
            response.body()?.apply {
                return Resource.Success(this)
            }
        }
        return Resource.Error(response.message())
    }
}