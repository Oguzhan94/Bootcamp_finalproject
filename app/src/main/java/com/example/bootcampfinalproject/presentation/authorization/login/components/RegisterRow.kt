package com.example.bootcampfinalproject.presentation.authorization.login.components

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
fun RegisterRow(onRegisterClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.dont_have_account_text),
            style = MaterialTheme.typography.labelLarge
        )
        TextButton(onClick = onRegisterClick) {
            Text(
                text = stringResource(R.string.register_button_text),
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}
