package com.example.bootcampfinalproject.presentation.authorization.login.components

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.example.bootcampfinalproject.presentation.authorization.AuthUiState
import com.example.bootcampfinalproject.navigation.Screen

@Composable
fun LoginScreenEffects(
    uiState: AuthUiState,
    navController: NavController,
    snackBarHostState: SnackbarHostState
) {
    LaunchedEffect(uiState) {
        when (uiState) {
            is AuthUiState.Success -> {
                navController.navigate(Screen.HomeScreen) {
                    popUpTo(Screen.LoginScreen) { inclusive = true }
                }
            }

            is AuthUiState.Error -> {
                val errorMessage = uiState.message
                snackBarHostState.showSnackbar(
                    message = errorMessage, duration = SnackbarDuration.Long
                )
            }

            else -> Unit
        }
    }
}