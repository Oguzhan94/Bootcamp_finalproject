package com.example.bootcampfinalproject.domain.usecase.settings

import com.example.bootcampfinalproject.domain.repository.SettingsRepository
import javax.inject.Inject

class SetDarkModeUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(enabled: Boolean) {
        repository.setDarkMode(enabled)
    }
}