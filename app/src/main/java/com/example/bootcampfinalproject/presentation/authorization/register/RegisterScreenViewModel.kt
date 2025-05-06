package com.example.bootcampfinalproject.presentation.authorization.register

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

    fun onEmailChange(newEmail: String) {
        emailInput = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        passwordInput = newPassword
    }

    fun onConfirmPasswordChange(newPassword: String) {
        confirmPasswordInput = newPassword
    }

    fun onFullNameChange(newFullName: String) {
        fullNameInput = newFullName
    }

    fun registerUser() {
        if (emailInput.isBlank() || passwordInput.isBlank() || confirmPasswordInput.isBlank() || fullNameInput.isBlank()) {
            _uiState.value = Error("Please fill in all fields")
        } else if (passwordInput != confirmPasswordInput) {
            _uiState.value = Error("Passwords do not match")
        } else {
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
}