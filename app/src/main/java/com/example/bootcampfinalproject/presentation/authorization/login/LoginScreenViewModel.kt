package com.example.bootcampfinalproject.presentation.authorization.login

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bootcampfinalproject.R
import com.example.bootcampfinalproject.domain.usecase.auth.CurrentUserUseCase
import com.example.bootcampfinalproject.domain.usecase.auth.LoginUseCase
import com.example.bootcampfinalproject.presentation.AuthUiState
import com.example.bootcampfinalproject.presentation.AuthUiState.Success
import com.example.bootcampfinalproject.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase, private val currentUserUseCase: CurrentUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    var emailInput by mutableStateOf("")
        private set
    var passwordInput by mutableStateOf("")
        private set
    var emailError by mutableStateOf<Int?>(null)
        private set
    var passwordError by mutableStateOf<Int?>(null)
        private set
    var isFormValid by mutableStateOf(false)
        private set

    fun onEmailChange(newEmail: String) {
        emailInput = newEmail
        emailError = if (Patterns.EMAIL_ADDRESS.matcher(newEmail).matches()) null else R.string.email_error
        updateFormValidation()
    }

    fun onPasswordChange(newPassword: String) {
        passwordInput = newPassword
        passwordError = if (newPassword.length >= 6) null else R.string.password_error
        updateFormValidation()
    }

    init {
        viewModelScope.launch {
            currentUserUseCase().collect { uiState ->
                when (uiState) {
                    is ResponseState.Success -> {
                        _uiState.value = Success(uiState.data)
                    }

                    is ResponseState.Error -> Unit
                    ResponseState.Loading -> Unit
                }
            }
        }
    }
    private fun updateFormValidation() {
        isFormValid = emailError == null && passwordError == null
    }

    fun login() {
            viewModelScope.launch {
                _uiState.value = AuthUiState.Loading
                when (val result = loginUseCase(emailInput, passwordInput)) {
                    is ResponseState.Success -> {
                        _uiState.value = Success(result.data)
                    }

                    is ResponseState.Error -> {
                        _uiState.value = AuthUiState.Error(result.message)
                    }

                    ResponseState.Loading -> {
                        _uiState.value = AuthUiState.Loading
                    }
                }
        }
    }
}
