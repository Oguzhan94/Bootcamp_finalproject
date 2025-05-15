package com.example.bootcampfinalproject.presentation.search.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.bootcampfinalproject.presentation.SearchScreenUiState

@Composable
fun SearchResultComponent(
    uiState: SearchScreenUiState,
    snackBarHostState: SnackbarHostState,
    onNavigateToDetail: (Int) -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (uiState) {
            is SearchScreenUiState.Error -> {
                val errorMessage = uiState.message
                LaunchedEffect(errorMessage) {
                    snackBarHostState.showSnackbar(
                        message = errorMessage,
                        duration = SnackbarDuration.Short
                    )
                }
            }

            SearchScreenUiState.Idle -> {}
            SearchScreenUiState.Loading -> {
                LoadingState()
            }

            is SearchScreenUiState.Success -> {
                SearchResultList(uiState, onNavigateToDetail)
            }
        }
    }
}