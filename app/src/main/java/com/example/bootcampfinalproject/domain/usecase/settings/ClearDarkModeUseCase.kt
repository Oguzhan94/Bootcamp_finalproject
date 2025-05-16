package com.example.bootcampfinalproject.domain.usecase.settings

import com.example.bootcampfinalproject.domain.repository.SettingsRepository
import javax.inject.Inject

class ClearDarkModeUseCase @Inject constructor(
private val repository: SettingsRepository
) {
    suspend operator fun invoke() {
        repository.clearDarkMode()
    }
}

