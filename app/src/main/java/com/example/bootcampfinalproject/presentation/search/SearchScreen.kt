package com.example.bootcampfinalproject.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.bootcampfinalproject.presentation.SearchScreenUiState
import com.example.bootcampfinalproject.presentation.home.component.HorizontalCardComponent
import com.example.bootcampfinalproject.presentation.search.components.SearchBarComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController, snackBarHostState: SnackbarHostState) {
    val viewModel = hiltViewModel<SearchScreenViewModel>()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            horizontalArrangement = Arrangement.Center
        ){
            SearchBarComponent(viewModel)
        }
        Spacer(Modifier.height(20.dp))
        when(uiState.value){
            is SearchScreenUiState.Error -> {

            }
            SearchScreenUiState.Idle -> {}
            SearchScreenUiState.Loading -> {}
            is SearchScreenUiState.Success -> {
                val movie =(uiState.value as SearchScreenUiState.Success).data
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    items(movie.size) { index ->

                        HorizontalCardComponent(movie[index])
                    }
                }
            }
        }


    }
}