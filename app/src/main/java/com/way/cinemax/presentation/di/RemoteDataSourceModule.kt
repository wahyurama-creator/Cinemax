package com.way.cinemax.presentation.di

import com.way.cinemax.data.api.ApiService
import com.way.cinemax.data.repository.dataSource.MovieRemoteDataSource
import com.way.cinemax.data.repository.dataSourceImpl.MovieRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {
    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: ApiService) : MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl(apiService)
    }
}