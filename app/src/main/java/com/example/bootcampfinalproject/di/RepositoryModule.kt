package com.example.bootcampfinalproject.di

import com.example.bootcampfinalproject.data.repository.BookmarkRepositoryImpl
import com.example.bootcampfinalproject.domain.repository.BookmarkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindBookmarkRepository(
        contactDaoRepositoryImpl: BookmarkRepositoryImpl
    ): BookmarkRepository
}