package com.example.bootcampfinalproject.presentation.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import com.example.bootcampfinalproject.R
import com.example.bootcampfinalproject.domain.model.Movie

@Composable
fun UpcomingComponent(
    upComingMovies: LazyPagingItems<Movie>,
    onNavigateToDetail: (Int) -> Unit
){
    Column {
        Spacer(Modifier.height(10.dp))
        Text(
            text = stringResource(R.string.up_coming_movies),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(Modifier.height(5.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            items(upComingMovies.itemCount) { index ->
                val movie = upComingMovies[index]
                movie?.let {
                    VerticalCardComponent(it, onNavigateToDetail)
                }
            }
        }
    }
}