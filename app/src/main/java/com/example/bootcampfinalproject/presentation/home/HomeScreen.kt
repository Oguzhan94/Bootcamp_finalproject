package com.example.bootcampfinalproject.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.bootcampfinalproject.presentation.home.component.Card

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel = hiltViewModel<HomeScreenViewModel>()
    val upComingMovies = viewModel.upComingMovies.collectAsLazyPagingItems()
    val topRatedMovies = viewModel.topRatedMovies.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(10.dp))
        Text("Up Coming Movies")
        Spacer(Modifier.height(5.dp))
        when (upComingMovies.loadState.refresh) {
            is LoadState.Error -> {
                Text(text = "Error")
            }

            LoadState.Loading -> {
                Text(text = "Loading")
            }

            is LoadState.NotLoading -> {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(upComingMovies.itemCount) { index ->
                        val movie = upComingMovies[index]
                        movie?.let {
                            Card(movie)
                        }
                    }
                }
            }
        }
        Spacer(Modifier.height(10.dp))
        Text("Top Rated")
        Spacer(Modifier.height(5.dp))
        when (topRatedMovies.loadState.refresh) {
            is LoadState.Error -> {
                Text(text = "Error")
            }

            LoadState.Loading -> {
                Text(text = "Loading")
            }

            is LoadState.NotLoading -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ){
                    items(topRatedMovies.itemCount) { index ->
                        val movie = topRatedMovies[index]
                        movie?.let {
                            Card(movie)
                        }
                    }
                }
            }
        }
    }
}

    @Preview
    @Composable
    fun PreviewHomeScreen() {
        HomeScreen(navController = NavController(LocalContext.current))
    }
