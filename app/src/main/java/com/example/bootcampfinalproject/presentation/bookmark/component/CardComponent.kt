package com.example.bootcampfinalproject.presentation.bookmark.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.example.bootcampfinalproject.domain.Bookmark

@Composable
fun BookmarkScreenCardComponent(
    movie: Bookmark,
    onNavigateToDetail: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clickable {
                onNavigateToDetail(movie.movieId)
            },
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 5.dp),
        ) {
            MoviePoster(
                posterPath = movie.posterPath,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.size(140.dp, 150.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(10.dp),
            ) {
                Text(
                    text = movie.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(Modifier.height(15.dp))
                MovieChipComponent(Icons.Default.Star, movie.releaseDate.substringBefore("-"))
                Icons.Default.DateRange

                Spacer(Modifier.height(15.dp))

                MovieChipComponent(Icons.Default.DateRange, movie.averageVote)
            }
        }
    }
}

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

@Composable
fun GenreChips(movie: Bookmark) {
    Card(
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurfaceVariant)
    ) {
        Text(
            text = movie.releaseDate.substringBefore("-"),
            overflow = TextOverflow.Clip,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp),
            fontWeight = FontWeight.SemiBold
        )
    }
    Spacer(Modifier.width(5.dp))
}
@Composable
fun MovieChipComponent(icon: ImageVector, text: String){
    Card(
        modifier = Modifier
            .padding(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inverseOnSurface
        ),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Row(
            modifier = Modifier
                .width(100.dp)
                .padding(horizontal = 10.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "",
            )
            Spacer(Modifier.width(5.dp))
            Text(
                modifier = Modifier,
                text = text
            )
        }
    }
}



@Composable
fun RatingBar(rating: Double) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(16.dp),
            imageVector = Icons.Default.Star,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.width(4.dp))
        Text(text = String.format("%.1f", rating))
    }
}