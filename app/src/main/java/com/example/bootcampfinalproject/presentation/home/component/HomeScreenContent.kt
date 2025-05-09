package com.example.bootcampfinalproject.presentation.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.bootcampfinalproject.R
import com.example.bootcampfinalproject.domain.model.Movie

@Composable
fun HomeScreenContent(
    upComingMovies: LazyPagingItems<Movie>,
    topRatedMovies: LazyPagingItems<Movie>,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            UpcomingComponent(
                upComingMovies = upComingMovies,
            )
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
                HorizontalCardComponent(
                    movie = it,
                )
            }
        }

        item {
            Spacer(Modifier.height(16.dp))
        }
    }
}