package com.example.bootcampfinalproject.presentation.authorization.register

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bootcampfinalproject.domain.usecase.auth.RegisterUseCase
import com.example.bootcampfinalproject.presentation.AuthUiState
import com.example.bootcampfinalproject.presentation.AuthUiState.Error
import com.example.bootcampfinalproject.presentation.AuthUiState.Success
import com.example.bootcampfinalproject.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    var emailInput by mutableStateOf("")
        private set
    var fullNameInput by mutableStateOf("")
        private set
    var passwordInput by mutableStateOf("")
        private set
    var confirmPasswordInput by mutableStateOf("")
        private set

    var emailError by mutableStateOf<String?>(null)
        private set
    var passwordError by mutableStateOf<String?>(null)
        private set
    var confirmPasswordError by mutableStateOf<String?>(null)
        private set
    var fullNameError by mutableStateOf<String?>(null)
        private set
    var isFormValid by mutableStateOf(false)
        private set

    fun onEmailChange(newEmail: String) {
        emailInput = newEmail
        emailError = if (Patterns.EMAIL_ADDRESS.matcher(newEmail).matches()) null else "Geçerli bir email girin"
        updateFormValidation()
    }

    fun onPasswordChange(newPassword: String) {
        passwordInput = newPassword
        passwordError = if (newPassword.length >= 6) null else "Şifre en az 6 karakter olmalı"
        updateFormValidation()
    }

    fun onConfirmPasswordChange(newPassword: String) {
        confirmPasswordInput = newPassword
        confirmPasswordError = if (newPassword == passwordInput) null else "Şifreler eşleşmiyor"
        updateFormValidation()
    }

    fun onFullNameChange(newFullName: String) {
        fullNameInput = newFullName
        fullNameError = if (newFullName.isNotBlank()) null else "Adınızı girin"
        updateFormValidation()
    }
    private fun updateFormValidation() {
        isFormValid = emailError == null &&
                passwordError == null &&
                confirmPasswordError == null &&
                fullNameError == null &&
                emailInput.isNotBlank() &&
                passwordInput.isNotBlank() &&
                confirmPasswordInput.isNotBlank() &&
                fullNameInput.isNotBlank()
    }

    fun registerUser() {
            viewModelScope.launch {
                _uiState.value = AuthUiState.Loading
                when (val result = registerUseCase(emailInput, passwordInput)) {
                    is ResponseState.Success -> {
                        _uiState.value = Success(result.data)
                    }

                    is ResponseState.Error -> {
                        _uiState.value = Error(result.message)
                    }

                    ResponseState.Loading -> {
                        _uiState.value = AuthUiState.Loading
                    }
            }
        }
    }
}