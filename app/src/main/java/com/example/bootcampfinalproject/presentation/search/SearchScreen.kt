package com.example.bootcampfinalproject.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bootcampfinalproject.presentation.search.components.SearchBarComponent
import com.example.bootcampfinalproject.presentation.search.components.SearchResultComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    snackBarHostState: SnackbarHostState,
    onNavigateToDetail: (Int) -> Unit
) {
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
        SearchResultComponent(uiState.value, snackBarHostState, onNavigateToDetail)
    }
}
