package com.example.bootcampfinalproject.domain.usecase.auth

import com.example.bootcampfinalproject.domain.repository.AuthRepository
import com.example.bootcampfinalproject.domain.usecase.settings.ClearDarkModeUseCase
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val clearDarkModeUseCase: ClearDarkModeUseCase
) {
    suspend operator fun invoke() {
        authRepository.logout()
        clearDarkModeUseCase()
    }
}