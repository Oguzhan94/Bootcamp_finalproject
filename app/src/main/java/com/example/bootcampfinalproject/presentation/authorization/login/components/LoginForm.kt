package com.example.bootcampfinalproject.presentation.authorization.login.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bootcampfinalproject.R
import com.example.bootcampfinalproject.presentation.authorization.components.EmailTextField
import com.example.bootcampfinalproject.presentation.authorization.components.PasswordTextField

@Composable
fun LoginForm(
    email: String,
    onEmailChange: (String) -> Unit,
    emailError: Int?,
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordError: Int?,
    onLoginClick: () -> Unit,
    isFormValid: Boolean,
    onRegisterClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        LoginHeader()

        Spacer(Modifier.height(70.dp))

        EmailTextField(
            value = email,
            onValueChange = onEmailChange,
            isError = emailError != null,
            errorText = emailError?.let { stringResource(it) }
        )

        Spacer(Modifier.height(20.dp))

        PasswordTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = stringResource(R.string.password_text),
            isError = passwordError != null,
            errorText = passwordError?.let { stringResource(it) }
        )

        Spacer(Modifier.height(30.dp))

        LoginButton(
            onClick = onLoginClick,
            enabled = isFormValid
        )

        Spacer(Modifier.height(10.dp))

        RegisterRow(onRegisterClick = onRegisterClick)
    }
}
