package com.example.bootcampfinalproject.di

import com.example.bootcampfinalproject.data.repository.FirebaseAuthImpl
import com.example.bootcampfinalproject.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule{

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: FirebaseAuthImpl
    ): AuthRepository

}