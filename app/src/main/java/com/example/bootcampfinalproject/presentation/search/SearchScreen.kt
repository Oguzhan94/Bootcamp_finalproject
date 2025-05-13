package com.example.bootcampfinalproject.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.bootcampfinalproject.domain.model.Movie
import com.example.bootcampfinalproject.presentation.SearchScreenUiState
import com.example.bootcampfinalproject.presentation.search.components.SearchBarComponent
import com.example.bootcampfinalproject.presentation.search.components.LoadingState
import com.example.bootcampfinalproject.presentation.search.components.SearchResultColumn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController, snackBarHostState: SnackbarHostState, onNavigateToDetail: (Int) -> Unit) {
    val viewModel = hiltViewModel<SearchScreenViewModel>()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 5.dp)
    ) {
        SearchBarComponent(viewModel, searchQuery)
        Spacer(Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            when (uiState.value) {
                is SearchScreenUiState.Error -> {
                    val errorMessage = (uiState.value as SearchScreenUiState.Error).message
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
                    SearchResultColumn(uiState.value, navController, onNavigateToDetail)
                }
            }
        }
    }
}
