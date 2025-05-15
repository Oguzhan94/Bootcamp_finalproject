package com.example.bootcampfinalproject.presentation.authorization.register

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.bootcampfinalproject.presentation.AuthUiState
import com.example.bootcampfinalproject.presentation.authorization.register.components.RegisterForm
import com.example.bootcampfinalproject.presentation.authorization.register.components.RegisterScreenEffects

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavController,
    snackBarHostState: SnackbarHostState
) {
    val viewModel = hiltViewModel<RegisterScreenViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    RegisterScreenEffects(uiState, navController, snackBarHostState)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        RegisterForm(
            viewModel = viewModel,
            isLoading = uiState is AuthUiState.Loading,
            onRegisterClick = { viewModel.registerUser() },
            navController = navController
        )

        if (uiState is AuthUiState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
