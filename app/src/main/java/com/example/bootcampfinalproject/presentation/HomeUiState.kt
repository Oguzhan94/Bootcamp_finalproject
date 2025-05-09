package com.example.bootcampfinalproject.presentation

import com.google.firebase.auth.FirebaseUser

sealed interface HomeUiState {
    data object Idle : HomeUiState
    data object Loading : HomeUiState
    data object Success : HomeUiState
    data class Error(val message: String) : HomeUiState
}