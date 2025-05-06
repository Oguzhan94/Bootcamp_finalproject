package com.example.bootcampfinalproject.presentation.authorization.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bootcampfinalproject.presentation.AuthUiState
import com.example.bootcampfinalproject.presentation.authorization.components.EmailTextField
import com.example.bootcampfinalproject.presentation.authorization.components.PasswordTextField
import com.example.bootcampfinalproject.presentation.navigation.Screen
import com.example.bootcampfinalproject.presentation.theme.BootcampFinalProjectTheme


@Composable
fun LoginScreen(navController: NavController){
    val viewModel = hiltViewModel<LoginScreenViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState) {
        when (uiState) {
            is AuthUiState.Success -> {
                navController.navigate(Screen.HomeScreen) {
                    popUpTo(Screen.LoginScreen) { inclusive = true }
                }
            }

            is AuthUiState.Error -> {
                val errorMessage = (uiState as AuthUiState.Error).message
                snackBarHostState.showSnackbar(
                    message = errorMessage, duration = SnackbarDuration.Long
                )
            }

            else -> Unit
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)

    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            Spacer(Modifier.height(200.dp))
            Text(
                text = "Login",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(70.dp))
            EmailTextField(
                value = viewModel.emailInput,
                onValueChange = {viewModel.onEmailChange(it)}
            )
            Spacer(Modifier.height(20.dp))
            PasswordTextField(
                value = viewModel.passwordInput,
                onValueChange = { viewModel.onPasswordChange(it) },
                label = "Password",
            )
            Spacer(Modifier.height(30.dp))
            Button(
                onClick = { viewModel.login() },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Login")
            }
            Spacer(Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Don't have an account?",
                    style = MaterialTheme.typography.labelSmall
                )
                TextButton(
                    onClick = { navController.navigate(Screen.RegisterScreen) },
                ) {
                    Text("Sign Up!")
                }
            }

        }
        if (uiState is AuthUiState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        SnackbarHost(
            hostState = snackBarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        )
    }
}

@Preview()
@Composable
fun LoginScreenPreview() {
    BootcampFinalProjectTheme {
        Surface(Modifier.fillMaxSize()) {LoginScreen(rememberNavController())  }

    }
}