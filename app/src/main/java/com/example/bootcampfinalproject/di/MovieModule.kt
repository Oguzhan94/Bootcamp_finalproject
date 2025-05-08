package com.example.bootcampfinalproject.di

import com.example.bootcampfinalproject.data.repository.MovieRepositoryImpl
import com.example.bootcampfinalproject.domain.repository.TmdbRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MovieModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): TmdbRepository
}