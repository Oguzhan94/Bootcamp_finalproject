package com.example.bootcampfinalproject.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel = hiltViewModel<HomeScreenViewModel>()
    val movies = viewModel.movies.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when (movies.loadState.refresh) {
            is LoadState.Error -> {
                Text(text = "Error")
            }
            LoadState.Loading -> {
                Text(text = "Loading")
            }
            is LoadState.NotLoading -> {
                LazyColumn {
                    items(movies.itemCount) { index ->
                        val movie = movies[index]
                        movie?.let {
                            Text(text = it.title ?: "No title available")
                        }
                    }
                }
            }
        }
    }
}
