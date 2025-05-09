package com.example.bootcampfinalproject.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.bootcampfinalproject.R
import com.example.bootcampfinalproject.domain.model.Movie
import com.example.bootcampfinalproject.presentation.HomeUiState
import com.example.bootcampfinalproject.presentation.home.component.HomeScreenErrorComponent
import com.example.bootcampfinalproject.presentation.home.component.Loading
import com.example.bootcampfinalproject.presentation.home.component.UpcomingComponent
import com.example.bootcampfinalproject.presentation.home.component.VerticalCard

@Composable
fun HomeScreen(navController: NavController, snackBarHostState: SnackbarHostState) {
    val viewModel = hiltViewModel<HomeScreenViewModel>()
    val upComingMovies: LazyPagingItems<Movie> = viewModel.upComingMovies.collectAsLazyPagingItems()
    val topRatedMovies: LazyPagingItems<Movie> = viewModel.topRatedMovies.collectAsLazyPagingItems()

    val upComingState = upComingMovies.loadState.refresh
    val topRatedState = topRatedMovies.loadState.refresh
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    val isLoading =
        upComingState is LoadState.Loading || topRatedState is LoadState.Loading || uiState.value is HomeUiState.Loading
    val isError =
        upComingState is LoadState.Error || topRatedState is LoadState.Error || uiState.value is HomeUiState.Error
    val isSuccess =
        upComingState is LoadState.NotLoading && topRatedState is LoadState.NotLoading && uiState.value is HomeUiState.Success

    val errorMessage = remember(upComingState, topRatedState, uiState.value) {
        when {
            upComingState is LoadState.Error -> upComingState.error.localizedMessage
            topRatedState is LoadState.Error -> topRatedState.error.localizedMessage
            uiState.value is HomeUiState.Error -> (uiState.value as HomeUiState.Error).message
            else -> null
        }
    }
    LaunchedEffect(isError, errorMessage) {
        if (isError && !errorMessage.isNullOrBlank()) {
            snackBarHostState.showSnackbar(
                message = errorMessage, duration = SnackbarDuration.Long
            )
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            when {
                isLoading -> {
                    item {
                        Loading()
                    }
                }

                isError -> {
                    item {
                        HomeScreenErrorComponent()
                    }
                }

                isSuccess -> {
                    item {
                        UpcomingComponent(upComingMovies)
                    }
                    item {
                        Column {
                            Spacer(Modifier.height(16.dp))
                            Text(
                                text = stringResource(R.string.top_rated_movies),
                                style = MaterialTheme.typography.titleLarge
                            )
                            Spacer(Modifier.height(5.dp))
                        }
                    }
                    items(topRatedMovies.itemCount) { index ->
                        val movie = topRatedMovies[index]
                        movie?.let {
                            VerticalCard(it)
                        }
                    }
                    item {
                        Spacer(Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}