package com.example.bootcampfinalproject.presentation.bookmark

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bootcampfinalproject.presentation.bookmark.component.BookmarkScreenSuccessComponent
import com.example.bootcampfinalproject.presentation.component.ErrorComponent
import com.example.bootcampfinalproject.presentation.component.Loading

@Composable
fun BookmarkScreen(
    onNavigateToDetail: (Int) -> Unit
) {
    val viewModel = hiltViewModel<BookmarkScreenViewModel>()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (val state = uiState.value) {
            is BookmarkScreenUiState.Error -> {
                ErrorComponent()
            }

            BookmarkScreenUiState.Loading -> {
                Loading()
            }

            is BookmarkScreenUiState.Success -> {
                BookmarkScreenSuccessComponent(state.data, onNavigateToDetail)
            }

            else -> Unit
        }
    }
}