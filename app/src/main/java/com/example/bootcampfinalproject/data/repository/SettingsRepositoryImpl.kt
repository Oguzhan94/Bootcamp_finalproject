package com.example.bootcampfinalproject.data.repository

import com.example.bootcampfinalproject.data.local.preferences.SettingsPreferences
import com.example.bootcampfinalproject.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val preferences: SettingsPreferences
) : SettingsRepository {
    override val isDarkModeEnabled: Flow<Boolean> = preferences.darkModeFlow
    override suspend fun setDarkMode(enabled: Boolean) {
        preferences.setDarkMode(enabled)
    }
}
