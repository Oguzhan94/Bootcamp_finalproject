package com.example.bootcampfinalproject.presentation.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bootcampfinalproject.domain.model.Movie
import com.example.bootcampfinalproject.presentation.SearchScreenUiState
import com.example.bootcampfinalproject.presentation.home.component.HorizontalCardComponent

@Composable
fun SearchResultColumn(uiState: SearchScreenUiState, navController: NavController, onNavigateToDetail: (Int) -> Unit){
    val movie = (uiState as SearchScreenUiState.Success).data
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        items(movie.size) { index ->

            HorizontalCardComponent(
                movie[index], navController,
                onNavigateToDetail = onNavigateToDetail
            )
        }
    }
}