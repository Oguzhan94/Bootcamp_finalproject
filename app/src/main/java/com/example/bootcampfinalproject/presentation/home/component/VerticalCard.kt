package com.example.bootcampfinalproject.presentation.home.component

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
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bootcampfinalproject.R
import com.example.bootcampfinalproject.domain.model.Movie
import kotlinx.coroutines.Job

@Composable
fun VerticalCard(movie: Movie) {
//    val movie2 = Movie(
//        title = "Movie 2 Movie 2 Movie 2Movie 2 Movie 2 Movie 2Movie 2 Movie 2 Movie 2Movie 2 Movie 2 M",
//        releaseDate = "2023-02-01",
//        adult = true,
//        backdropPath = "/path/to/backdrop2.jpg",
//        genreIds = listOf(35, 18),
//        id = 2,
//        originalLanguage = "en",
//        originalTitle = "Original Movie 2",
//        overview = "This is a brief overview of Movie 2.",
//        popularity = 8.5,
//        posterPath = "/path/to/poster2.jpg",
//        video = true,
//        voteAverage = 8.3,
//        voteCount = 1500
//    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clickable { },
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 5.dp),
        ) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://image.tmdb.org/t/p/original/${movie.posterPath}")
                        .crossfade(true)
                        .allowHardware(false)
                        .build(),
                    placeholder = painterResource(R.drawable.loading),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
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
                Row(
                    Modifier.fillMaxWidth(),
                ) {
                    movie.genreNames.forEach { it ->
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                        ) {
                            Text(
                                text = it,
                                maxLines = 1,
                                overflow = TextOverflow.Clip,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Spacer(Modifier.width(5.dp))
                    }
                }

                Spacer(Modifier.height(15.dp))
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

            }
        }
    }

}

//@Preview
//@Composable
//fun CardPrev() {
//    VerticalCard(
//        Movie(
//            title = "Movie 2",
//            releaseDate = "2023-02-01",
//            adult = true,
//            backdropPath = "/path/to/backdrop2.jpg",
//            genreIds = listOf(35, 18),
//            id = 2,
//            originalLanguage = "en",
//            originalTitle = "Original Movie 2",
//            overview = "This is a brief overview of Movie 2.",
//            popularity = 8.5,
//            posterPath = "/path/to/poster2.jpg",
//            video = true,
//            voteAverage = 8.3,
//            voteCount = 1500
//        )
//    )
//}