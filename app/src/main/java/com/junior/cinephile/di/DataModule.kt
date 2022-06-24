package com.junior.cinephile.di

import com.junior.cinephile.data.network.Api
import com.junior.cinephile.data.MoviesRepositoryImpl
import com.junior.cinephile.domain.repo.MoviesRepo
import com.junior.cinephile.domain.useСase.GetDataMovies
import com.junior.cinephile.domain.useСase.GetDataMoviesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideMoviesRepository(api: Api): MoviesRepo = MoviesRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideMoviesUseCases(repository: MoviesRepo): GetDataMoviesUseCases =
        GetDataMoviesUseCases(getMovies = GetDataMovies(repository))
}