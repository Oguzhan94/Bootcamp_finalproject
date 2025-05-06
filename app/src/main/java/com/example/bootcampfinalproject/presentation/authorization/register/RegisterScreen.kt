package com.example.bootcampfinalproject.presentation.authorization.register

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.input.KeyboardType
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState) {
        when (uiState) {
            is AuthUiState.Success -> {
                navController.navigate(Screen.HomeScreen) {
                    popUpTo(Screen.RegisterScreen) { inclusive = true }
                }
                snackBarHostState.showSnackbar(
                    message = "Kayıt başarılı!", duration = SnackbarDuration.Short
                )
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
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(Modifier.height(100.dp))
            Text(
                text = "Register", style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(40.dp))

            EmailTextField(
                value = viewModel.emailInput,
                onValueChange = { viewModel.onEmailChange(it) }
            )

            Spacer(Modifier.height(20.dp))

            OutlinedTextField(
                value = viewModel.fullNameInput,
                onValueChange = { viewModel.onFullNameChange(it) },
                label = { Text("Full Name") },
                modifier = Modifier.fillMaxWidth(),
                enabled = uiState != AuthUiState.Loading,
                textStyle = TextStyle(color = Color.White),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Spacer(Modifier.height(20.dp))
            PasswordTextField(
                value = viewModel.passwordInput,
                onValueChange = { viewModel.onPasswordChange(it) },
                label = "Password"
            )
            Spacer(Modifier.height(20.dp))
            PasswordTextField(
                value = viewModel.confirmPasswordInput,
                onValueChange = { viewModel.onConfirmPasswordChange(it) },
                label = "Confirm Password"
            )
            Spacer(Modifier.height(30.dp))
            Button(
                onClick = { viewModel.registerUser() },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                enabled = uiState != AuthUiState.Loading
            ) {
                Text(text = "Register")
            }
            Spacer(Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Have an account?", style = MaterialTheme.typography.labelSmall
                )
                TextButton(
                    onClick = {
                        if (uiState != AuthUiState.Loading) {
                            navController.navigate(Screen.LoginScreen)
                        }
                    }, enabled = uiState != AuthUiState.Loading
                ) {
                    Text("Log in!")
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
fun RegisterScreenPreview() {
    BootcampFinalProjectTheme {
        Surface(Modifier.fillMaxSize()) { RegisterScreen(rememberNavController()) }

    }
}