package com.example.bootcampfinalproject.presentation.home

sealed interface HomeUiState {
    data object Idle : HomeUiState
    data object Loading : HomeUiState
    data object Success : HomeUiState
    data class Error(val message: String) : HomeUiState
}