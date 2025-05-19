package com.example.bootcampfinalproject.presentation.search

import com.example.bootcampfinalproject.domain.model.Movie

sealed interface SearchScreenUiState{
    data object Idle : SearchScreenUiState
    data object Loading : SearchScreenUiState
    data class Success(val data: List<Movie>) : SearchScreenUiState
    data class Error(val message: String) : SearchScreenUiState

}