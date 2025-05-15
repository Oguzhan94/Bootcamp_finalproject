package com.example.bootcampfinalproject.presentation.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bootcampfinalproject.R
import com.example.bootcampfinalproject.domain.model.Movie
import com.example.bootcampfinalproject.presentation.navigation.Screen

@Composable
fun VerticalCardComponent(movie: Movie, onNavigateToDetail: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .width(130.dp)
            .height(250.dp)
            .clickable {
                onNavigateToDetail(movie.id)
            },
        shape = RoundedCornerShape(8.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://image.tmdb.org/t/p/original/${movie.posterPath}")
                .crossfade(true)
                .allowHardware(false)
                .build(),
            placeholder = painterResource(R.drawable.loading),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.error),
            modifier = Modifier
                .size(130.dp, 140.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 6.dp,
                        topEnd = 6.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                ),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 8.dp),
        ) {
            Spacer(Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = Icons.Default.Star,
                    contentDescription = ""
                )
                Text(text = String.format("%.1f", movie.voteAverage))
            }
            Spacer(Modifier.height(5.dp))
            Text(
                modifier = Modifier.weight(1f),
                text = movie.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = movie.genreNames.first(),
                maxLines = 1,
                overflow = TextOverflow.Clip,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(5.dp))
        }

    }
}
