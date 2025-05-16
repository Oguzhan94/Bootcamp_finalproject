package com.example.bootcampfinalproject.domain.usecase.settings

import com.example.bootcampfinalproject.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDarkModeUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    operator fun invoke(): Flow<Boolean> = repository.isDarkModeEnabled
}