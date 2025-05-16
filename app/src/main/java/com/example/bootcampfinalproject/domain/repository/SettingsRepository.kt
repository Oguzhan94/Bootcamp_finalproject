package com.example.bootcampfinalproject.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val isDarkModeEnabled: Flow<Boolean>
    suspend fun setDarkMode(enabled: Boolean)
    suspend fun clearDarkMode()
}
