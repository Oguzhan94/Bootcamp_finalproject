package com.example.bootcampfinalproject.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.bootcampfinalproject.presentation.home.component.HomeScreenErrorComponent
import com.example.bootcampfinalproject.presentation.home.component.Loading

@Composable
fun DetailScreen(navController: NavController) {

    val viewModel = hiltViewModel<DetailScreenViewModel>()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (val state = uiState.value) {
            is DetailScreenUiState.Error -> {
                HomeScreenErrorComponent()
            }

            DetailScreenUiState.Idle -> {}
            DetailScreenUiState.Loading -> {
                Loading()
            }

            is DetailScreenUiState.Success -> {
                val movie = state.data
                DetailScreenSuccessComponent(movie, viewModel)

            }
        }
    }
}
