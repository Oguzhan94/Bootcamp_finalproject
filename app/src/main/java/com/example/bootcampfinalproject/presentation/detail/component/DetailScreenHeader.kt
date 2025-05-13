package com.example.bootcampfinalproject.presentation.detail.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bootcampfinalproject.R
import com.example.bootcampfinalproject.domain.model.MovieDetail
import com.example.bootcampfinalproject.util.extractDominantAndMutedColors

@Composable
fun DetailScreenHeader(movie: MovieDetail, dominantColor: MutableState<Color>, mutedColor: MutableState<Color>){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        contentAlignment = Alignment.Center
    ) {
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
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            error = painterResource(R.drawable.error),
            alpha = 0.8f,
            modifier = Modifier
                .fillMaxSize(),
        )
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://image.tmdb.org/t/p/original/${movie.poster_path}")
                .crossfade(true)
                .allowHardware(false)
                .build(),
            placeholder = painterResource(R.drawable.loading),
            contentDescription = "",
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