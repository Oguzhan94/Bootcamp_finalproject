package com.example.bootcampfinalproject.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.bootcampfinalproject.domain.model.Movie
import com.example.bootcampfinalproject.presentation.home.component.HomeScreenContent
import com.example.bootcampfinalproject.presentation.home.component.HomeScreenErrorComponent
import com.example.bootcampfinalproject.presentation.home.component.Loading
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun HomeScreen(navController: NavController, snackBarHostState: SnackbarHostState, onNavigateToDetail: (Int) -> Unit) {
    val viewModel = hiltViewModel<HomeScreenViewModel>()
    val upComingMovies: LazyPagingItems<Movie> = viewModel.upComingMovies.collectAsLazyPagingItems()
    val topRatedMovies: LazyPagingItems<Movie> = viewModel.topRatedMovies.collectAsLazyPagingItems()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val isRefreshing = uiState.value is HomeUiState.Loading
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)

    val errorMessage = when {
        upComingMovies.loadState.refresh is LoadState.Error ->
            (upComingMovies.loadState.refresh as LoadState.Error).error.localizedMessage

        topRatedMovies.loadState.refresh is LoadState.Error ->
            (topRatedMovies.loadState.refresh as LoadState.Error).error.localizedMessage

        uiState.value is HomeUiState.Error -> (uiState.value as HomeUiState.Error).message

        else -> null
    }
    LaunchedEffect(errorMessage) {
        if (errorMessage != null) {
            snackBarHostState.showSnackbar(
                message = errorMessage,
                duration = SnackbarDuration.Long
            )
        }
    }
    LaunchedEffect(upComingMovies.loadState, topRatedMovies.loadState) {
        when {
            upComingMovies.loadState.refresh is LoadState.Error -> {
                viewModel.onLoadStateChanged(upComingMovies.loadState.refresh)
            }

            topRatedMovies.loadState.refresh is LoadState.Error -> {
                viewModel.onLoadStateChanged(topRatedMovies.loadState.refresh)
            }

            upComingMovies.loadState.refresh is LoadState.Loading ||
                    topRatedMovies.loadState.refresh is LoadState.Loading -> {
                viewModel.onLoadStateChanged(LoadState.Loading)
            }

            else -> {
                viewModel.onLoadStateChanged(LoadState.NotLoading(false))
            }
        }
    }

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = { viewModel.refreshData() }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            when (uiState.value) {
                is HomeUiState.Error -> {
                    HomeScreenErrorComponent()
                }

                HomeUiState.Loading -> {
                    Loading()
                }

                else -> {
                    HomeScreenContent(upComingMovies, topRatedMovies, navController, onNavigateToDetail)
                }
            }
        }
    }
}