package com.example.bootcampfinalproject.presentation.bookmark

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.bootcampfinalproject.presentation.bookmark.component.BookmarkScreenSuccessComponent
import com.example.bootcampfinalproject.presentation.detail.DetailScreenSuccessComponent
import com.example.bootcampfinalproject.presentation.detail.DetailScreenUiState
import com.example.bootcampfinalproject.presentation.home.component.HomeScreenErrorComponent
import com.example.bootcampfinalproject.presentation.home.component.Loading

@Composable
fun BookmarkScreen(
    navController: NavController,
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
                HomeScreenErrorComponent()
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