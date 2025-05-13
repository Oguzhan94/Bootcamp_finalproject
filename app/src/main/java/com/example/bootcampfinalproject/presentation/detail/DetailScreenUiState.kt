package com.example.bootcampfinalproject.presentation.detail

import com.example.bootcampfinalproject.domain.model.MovieDetail

sealed interface DetailScreenUiState{
    data object Idle : DetailScreenUiState
    data object Loading : DetailScreenUiState
    data class Success(val data: MovieDetail) : DetailScreenUiState
    data class Error(val message: String) : DetailScreenUiState

}