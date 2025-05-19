package com.example.bootcampfinalproject.presentation.authorization.register.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bootcampfinalproject.R
import com.example.bootcampfinalproject.presentation.authorization.components.EmailTextField
import com.example.bootcampfinalproject.presentation.authorization.components.PasswordTextField
import com.example.bootcampfinalproject.presentation.authorization.register.RegisterScreenViewModel
import com.example.bootcampfinalproject.navigation.Screen

@Composable
fun RegisterForm(
    viewModel: RegisterScreenViewModel,
    isLoading: Boolean,
    onRegisterClick: () -> Unit,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(Modifier.height(80.dp))

        Text(
            text = stringResource(R.string.register),
            style = MaterialTheme.typography.displayLarge
        )

        Spacer(Modifier.height(40.dp))

        EmailTextField(
            value = viewModel.emailInput,
            onValueChange = viewModel::onEmailChange,
            isError = viewModel.emailError != null,
            errorText = viewModel.emailError?.let { stringResource(it) }
        )

        Spacer(Modifier.height(20.dp))

        PasswordTextField(
            value = viewModel.passwordInput,
            onValueChange = viewModel::onPasswordChange,
            label = stringResource(R.string.password_text),
            isError = viewModel.passwordError != null,
            errorText = viewModel.passwordError?.let { stringResource(it) }
        )

        Spacer(Modifier.height(20.dp))

        PasswordTextField(
            value = viewModel.confirmPasswordInput,
            onValueChange = viewModel::onConfirmPasswordChange,
            label = stringResource(R.string.confirm_password_text),
            isError = viewModel.confirmPasswordError != null,
            errorText = viewModel.confirmPasswordError?.let { stringResource(it) }
        )

        Spacer(Modifier.height(30.dp))

        Button(
            onClick = onRegisterClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            enabled = !isLoading && viewModel.isFormValid
        ) {
            Text(text = stringResource(R.string.register))
        }

        Spacer(Modifier.height(10.dp))

        AccountRedirectText(
            isLoading = isLoading,
            onLoginClick = {
                navController.navigate(Screen.LoginScreen) {
                    popUpTo(0)
                }
            })
    }
}
