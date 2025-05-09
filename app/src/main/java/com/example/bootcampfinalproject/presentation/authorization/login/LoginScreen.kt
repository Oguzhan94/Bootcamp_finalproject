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
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.bootcampfinalproject.presentation.AuthUiState
import com.example.bootcampfinalproject.presentation.authorization.components.EmailTextField
import com.example.bootcampfinalproject.presentation.authorization.components.PasswordTextField
import com.example.bootcampfinalproject.presentation.navigation.Screen
import com.example.bootcampfinalproject.R

@Composable
fun LoginScreen(navController: NavController, snackBarHostState: SnackbarHostState) {
    val viewModel = hiltViewModel<LoginScreenViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

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
        ) {
            Spacer(Modifier.height(200.dp))
            Text(
                text = stringResource(R.string.login_button_text),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.displayLarge
            )
            Spacer(Modifier.height(70.dp))
            EmailTextField(
                value = viewModel.emailInput,
                onValueChange = { viewModel.onEmailChange(it) },
                isError = viewModel.emailError != null,
                errorText = viewModel.emailError?.let { stringResource(it) }
            )
            Spacer(Modifier.height(20.dp))
            PasswordTextField(
                value = viewModel.passwordInput,
                onValueChange = { viewModel.onPasswordChange(it) },
                label = stringResource(R.string.password_text),
                isError = viewModel.passwordError != null,
                errorText = viewModel.passwordError?.let { stringResource(it) }
            )
            Spacer(Modifier.height(30.dp))
            Button(
                onClick = { viewModel.login() },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(12.dp),
                enabled = viewModel.isFormValid
            ) {
                Text(text = stringResource(R.string.login_button_text))
            }
            Spacer(Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.dont_have_account_text), style = MaterialTheme.typography.labelLarge
                )
                TextButton(
                    onClick = { navController.navigate(Screen.RegisterScreen) },
                ) {
                    Text(
                        text = stringResource(R.string.register_button_text),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }

        }
        if (uiState is AuthUiState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
