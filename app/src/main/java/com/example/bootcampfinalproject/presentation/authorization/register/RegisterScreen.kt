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
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.bootcampfinalproject.presentation.AuthUiState
import com.example.bootcampfinalproject.presentation.authorization.components.EmailTextField
import com.example.bootcampfinalproject.presentation.authorization.components.PasswordTextField
import com.example.bootcampfinalproject.presentation.navigation.Screen
import com.example.bootcampfinalproject.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavController,
    snackBarHostState: SnackbarHostState
) {
    val viewModel = hiltViewModel<RegisterScreenViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState) {
        when (uiState) {
            is AuthUiState.Success -> {
                navController.navigate(Screen.HomeScreen) {
                    popUpTo(Screen.RegisterScreen) { inclusive = true }
                }
                snackBarHostState.showSnackbar(
                    message = R.string.success_signup_text.toString(), duration = SnackbarDuration.Short
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
                text = stringResource(R.string.register), style = MaterialTheme.typography.displayLarge
            )
            Spacer(Modifier.height(40.dp))

            EmailTextField(
                value = viewModel.emailInput,
                onValueChange = { viewModel.onEmailChange(it) },
                isError = viewModel.emailError != null,
                errorText = viewModel.emailError?.let { stringResource(it) }
            )

            Spacer(Modifier.height(20.dp))

            OutlinedTextField(
                value = viewModel.fullNameInput,
                onValueChange = { viewModel.onFullNameChange(it) },
                label = { Text(stringResource(R.string.full_name_text)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                isError = viewModel.fullNameError != null,
                supportingText = {
                    if (viewModel.fullNameError != null) {
                        Text(
                            text = stringResource(R.string.full_name_text),
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            )
            Spacer(Modifier.height(20.dp))
            PasswordTextField(
                value = viewModel.passwordInput,
                onValueChange = { viewModel.onPasswordChange(it) },
                label = stringResource(R.string.password_text),
                isError = viewModel.passwordError != null,
                errorText = viewModel.passwordError?.let { stringResource(it) }
            )
            Spacer(Modifier.height(20.dp))
            PasswordTextField(
                value = viewModel.confirmPasswordInput,
                onValueChange = { viewModel.onConfirmPasswordChange(it) },
                label = stringResource(R.string.confirm_password_text),
                isError = viewModel.confirmPasswordError != null,
                errorText = viewModel.confirmPasswordError?.let { stringResource(it) }
            )
            Spacer(Modifier.height(30.dp))
            Button(
                onClick = { viewModel.registerUser() },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                enabled = uiState != AuthUiState.Loading && viewModel.isFormValid
            ) {
                Text(text = stringResource(R.string.register))
            }
            Spacer(Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.have_account_text), style = MaterialTheme.typography.labelLarge
                )
                TextButton(
                    onClick = {
                        if (uiState != AuthUiState.Loading) {
                            navController.navigate(Screen.LoginScreen)
                        }
                    }, enabled = uiState != AuthUiState.Loading
                ) {
                    Text(stringResource(R.string.login_text),
                        style = MaterialTheme.typography.labelLarge)
                }
            }
        }

        if (uiState is AuthUiState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
