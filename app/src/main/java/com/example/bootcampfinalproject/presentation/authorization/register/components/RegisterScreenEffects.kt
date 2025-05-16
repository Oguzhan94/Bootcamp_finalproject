package com.example.bootcampfinalproject.presentation.authorization.register.components

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.example.bootcampfinalproject.presentation.AuthUiState
import com.example.bootcampfinalproject.presentation.navigation.Screen

@Composable
fun RegisterScreenEffects(
    uiState: AuthUiState,
    navController: NavController,
    snackBarHostState: SnackbarHostState
) {
    LaunchedEffect(uiState) {
        when (uiState) {
            is AuthUiState.Success -> {
                navController.navigate(Screen.HomeScreen) {
                    popUpTo(0)
                }
            }

            is AuthUiState.Error -> {
                snackBarHostState.showSnackbar(
                    message = uiState.message,
                    duration = SnackbarDuration.Long
                )
            }

            else -> Unit
        }
    }
}
