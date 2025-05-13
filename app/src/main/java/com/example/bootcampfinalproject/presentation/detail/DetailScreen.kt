package com.example.bootcampfinalproject.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bootcampfinalproject.R
import com.example.bootcampfinalproject.presentation.home.component.HomeScreenErrorComponent
import com.example.bootcampfinalproject.presentation.home.component.Loading
import com.example.bootcampfinalproject.util.extractDominantAndMutedColors

@Composable
fun DetailScreen(navController: NavController) {

    val viewModel = hiltViewModel<DetailScreenViewModel>()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (val state = uiState.value) {
            is DetailScreenUiState.Error -> {
                HomeScreenErrorComponent()
            }

            DetailScreenUiState.Idle -> {}
            DetailScreenUiState.Loading -> {
                Loading()
            }

            is DetailScreenUiState.Success -> {
                val movie = state.data
                DetailScreenSuccessComponent(movie)

            }
        }
    }
}
