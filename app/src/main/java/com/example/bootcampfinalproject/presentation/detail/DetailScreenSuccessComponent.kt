package com.example.bootcampfinalproject.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bootcampfinalproject.domain.model.MovieDetail
import com.example.bootcampfinalproject.presentation.detail.component.DetailScreenHeader
import com.example.bootcampfinalproject.presentation.detail.component.MovieChipComponent
import com.example.bootcampfinalproject.util.formatWithSuffix

@Composable
fun DetailScreenSuccessComponent(movie: MovieDetail, viewModel: DetailScreenViewModel) {
    var dominantColor = remember { mutableStateOf(Color.Gray) }
    var mutedColor = remember { mutableStateOf(Color.Gray) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(dominantColor.value, MaterialTheme.colorScheme.background)
                )
            )
    ) {
        item {
            DetailScreenHeader(movie, dominantColor, mutedColor, viewModel)
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .clip(RoundedCornerShape(5.dp)),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                MovieChipComponent(
                    icon = Icons.Default.Star,
                    text = String.format("%.1f", movie.vote_average)
                )
                MovieChipComponent(
                    icon = Icons.Default.DateRange,
                    text = movie.release_date.substringBefore("-")
                )
                MovieChipComponent(
                    icon = Icons.Default.AttachMoney,
                    text = movie.budget.formatWithSuffix()
                )


            }

        }
        item {
            Text(
                modifier = Modifier
                    .padding(start = 15.dp, bottom = 15.dp, top = 10.dp),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 25.sp,
                text = "Overview"
            )
        }
        item {
            Text(
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp),
                fontSize = 20.sp,
                text = movie.overview
            )
        }
    }
}
