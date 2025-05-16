package com.example.bootcampfinalproject.presentation.detail.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.IconToggleButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bootcampfinalproject.R
import com.example.bootcampfinalproject.domain.model.MovieDetail
import com.example.bootcampfinalproject.presentation.detail.DetailScreenViewModel
import com.example.bootcampfinalproject.util.extractDominantAndMutedColors

@Composable
fun DetailScreenHeader(
    movie: MovieDetail,
    dominantColor: MutableState<Color>,
    mutedColor: MutableState<Color>,
    viewModel: DetailScreenViewModel
) {
    val isBookmarked = viewModel.isBookmarked.collectAsStateWithLifecycle()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        contentAlignment = Alignment.Center
    ) {
        IconToggleButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .zIndex(1f),
            checked = isBookmarked.value,
            onCheckedChange = {
                viewModel.onBookmarkedChange(it)
            },
            colors = IconToggleButtonColors(
                checkedContentColor = if (dominantColor.value.luminance() > 0.5f) Color.Black else Color.White,
                contentColor = if (dominantColor.value.luminance() > 0.5f) Color.Black else Color.White,
                containerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = Color.Black,
                checkedContainerColor = Color.Transparent,
            ),
            interactionSource = remember { MutableInteractionSource() }
        ) {
            Icon(
                imageVector = if (isBookmarked.value) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                contentDescription = null,
                modifier = Modifier.size(48.dp),
            )
        }
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://image.tmdb.org/t/p/original/${movie.backdrop_path}")
                .crossfade(true)
                .allowHardware(false)
                .listener(
                    onSuccess = { _, result ->
                        result.drawable.toBitmap()
                            .extractDominantAndMutedColors { colors ->
                                dominantColor.value = colors.first
                                mutedColor.value = colors.second
                            }
                    }
                )
                .build(),
            placeholder = painterResource(R.drawable.loading),
            contentDescription = stringResource(R.string.movie_poster),
            contentScale = ContentScale.FillBounds,
            error = painterResource(R.drawable.error),
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.8f),
        )
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://image.tmdb.org/t/p/original/${movie.poster_path}")
                .crossfade(true)
                .allowHardware(false)
                .build(),
            placeholder = painterResource(R.drawable.loading),
            contentDescription = stringResource(R.string.movie_poster),
            contentScale = ContentScale.FillBounds,
            error = painterResource(R.drawable.error),
            modifier = Modifier
                .size(150.dp, 200.dp)
                .clip(
                    RoundedCornerShape(
                        6.dp
                    )
                ),
        )
        Text(
            text = movie.title,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 5.dp),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 25.sp,
            color = Color.White
        )
    }
}