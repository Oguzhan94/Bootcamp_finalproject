package com.example.bootcampfinalproject.presentation.authorization.register.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.bootcampfinalproject.R

@Composable
fun AccountRedirectText(
    isLoading: Boolean,
    onLoginClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.have_account_text),
            style = MaterialTheme.typography.labelLarge
        )
        TextButton(
            onClick = { onLoginClick() },
            enabled = !isLoading
        ) {
            Text(
                stringResource(R.string.login_text),
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}
