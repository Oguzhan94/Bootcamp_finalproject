package com.example.bootcampfinalproject.presentation.bookmark

import com.example.bootcampfinalproject.domain.Bookmark

sealed interface BookmarkScreenUiState{
    data object Idle : BookmarkScreenUiState
    data object Loading : BookmarkScreenUiState
    data class Success(val data: List<Bookmark>) : BookmarkScreenUiState
    data class Error(val message: String) : BookmarkScreenUiState
}