package com.example.bootcampfinalproject.domain.usecase.auth

import com.example.bootcampfinalproject.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke() {
        authRepository.logout()
    }
}