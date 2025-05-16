package com.example.bootcampfinalproject.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bootcampfinalproject.domain.usecase.auth.CurrentUserUseCase
import com.example.bootcampfinalproject.domain.usecase.auth.LogoutUseCase
import com.example.bootcampfinalproject.domain.usecase.settings.GetDarkModeUseCase
import com.example.bootcampfinalproject.domain.usecase.settings.SetDarkModeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
    private val getDarkModeUseCase: GetDarkModeUseCase,
    private val setDarkModeUseCase: SetDarkModeUseCase,
    private val lockOutUseCase: LogoutUseCase,
) : ViewModel() {

    private val _isDarkMode = MutableStateFlow<Boolean>(false)
    val isDarkMode: StateFlow<Boolean> = _isDarkMode.asStateFlow()

    private val _logoutSuccess = MutableStateFlow(false)
    val logoutSuccess: StateFlow<Boolean> = _logoutSuccess

    init {
        isDarkMode()
    }

    private fun isDarkMode() {
        viewModelScope.launch {
            getDarkModeUseCase().collect {
                _isDarkMode.value = it
            }
        }
    }

    fun setDarkMode(isDarkMode: Boolean) {
        viewModelScope.launch {
            setDarkModeUseCase(isDarkMode)
        }
    }

    fun logout() {
        viewModelScope.launch {
            lockOutUseCase()
            _logoutSuccess.value = true
        }
    }
}
