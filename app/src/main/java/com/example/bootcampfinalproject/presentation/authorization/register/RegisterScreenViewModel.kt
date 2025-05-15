package com.example.bootcampfinalproject.presentation.authorization.register

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bootcampfinalproject.R
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

    var passwordInput by mutableStateOf("")
        private set
    var confirmPasswordInput by mutableStateOf("")
        private set

    var emailError by mutableStateOf<Int?>(null)
        private set
    var passwordError by mutableStateOf<Int?>(null)
        private set
    var confirmPasswordError by mutableStateOf<Int?>(null)
        private set

    var isFormValid by mutableStateOf(false)
        private set

    fun onEmailChange(newEmail: String) {
        emailInput = newEmail
        emailError =
            if (Patterns.EMAIL_ADDRESS.matcher(newEmail).matches()) null else R.string.email_error
        updateFormValidation()
    }

    fun onPasswordChange(newPassword: String) {
        passwordInput = newPassword
        passwordError = if (newPassword.length >= 6) null else R.string.password_error
        updateFormValidation()
    }

    fun onConfirmPasswordChange(newPassword: String) {
        confirmPasswordInput = newPassword
        confirmPasswordError =
            if (newPassword == passwordInput) null else R.string.password_do_not_match
        updateFormValidation()
    }

    private fun updateFormValidation() {
        isFormValid = emailError == null &&
                passwordError == null &&
                confirmPasswordError == null &&
                emailInput.isNotBlank() &&
                passwordInput.isNotBlank() &&
                confirmPasswordInput.isNotBlank()
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