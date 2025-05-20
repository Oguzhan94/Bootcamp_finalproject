package com.example.bootcampfinalproject.presentation.bookmark.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bootcampfinalproject.R

@Composable
fun MoviePoster(
    posterPath: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    cornerShape: RoundedCornerShape = RoundedCornerShape(
        topStart = 6.dp,
        topEnd = 6.dp,
        bottomStart = 0.dp,
        bottomEnd = 0.dp
    )
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://image.tmdb.org/t/p/original/$posterPath")
            .crossfade(true)
            .allowHardware(false)
            .build(),
        placeholder = painterResource(R.drawable.loading),
        contentDescription = null,
        contentScale = contentScale,
        error = painterResource(R.drawable.error),
        modifier = modifier.clip(cornerShape),
    )
}