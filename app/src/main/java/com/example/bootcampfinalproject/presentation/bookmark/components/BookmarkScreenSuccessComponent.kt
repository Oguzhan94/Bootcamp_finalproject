package com.example.bootcampfinalproject.presentation.bookmark.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bootcampfinalproject.domain.model.Bookmark

@Composable
fun BookmarkScreenSuccessComponent(
    list: List<Bookmark>,
    onNavigateToDetail: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(15.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(list.size) { index ->
            val movie = list[index]
            BookmarkScreenCardComponent(movie, onNavigateToDetail)
        }
    }
}
