package com.example.bootcampfinalproject.presentation.authorization.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.bootcampfinalproject.presentation.authorization.AuthUiState
import com.example.bootcampfinalproject.presentation.authorization.login.components.LoginForm
import com.example.bootcampfinalproject.presentation.authorization.login.components.LoginScreenEffects
import com.example.bootcampfinalproject.navigation.Screen

@Composable
fun LoginScreen(
    navController: NavController,
    snackBarHostState: SnackbarHostState
) {
    val viewModel = hiltViewModel<LoginScreenViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LoginScreenEffects(uiState, navController, snackBarHostState)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        LoginForm(
            email = viewModel.emailInput,
            onEmailChange = viewModel::onEmailChange,
            emailError = viewModel.emailError,
            password = viewModel.passwordInput,
            onPasswordChange = viewModel::onPasswordChange,
            passwordError = viewModel.passwordError,
            onLoginClick = viewModel::login,
            isFormValid = viewModel.isFormValid,
            onRegisterClick = { navController.navigate(Screen.RegisterScreen) }
        )

        if (uiState is AuthUiState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}