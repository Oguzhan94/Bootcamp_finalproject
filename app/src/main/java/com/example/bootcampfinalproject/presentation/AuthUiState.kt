package com.example.bootcampfinalproject.presentation

import com.google.firebase.auth.FirebaseUser

sealed interface AuthUiState {
    data object Idle : AuthUiState
    data object Loading : AuthUiState
    data class Success(val user: FirebaseUser? = null) : AuthUiState
    data class Error(val message: String) : AuthUiState
}