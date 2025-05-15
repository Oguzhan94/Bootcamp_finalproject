package com.example.bootcampfinalproject.presentation.authorization.login.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bootcampfinalproject.R

@Composable
fun LoginHeader(){
    Spacer(Modifier.height(210.dp))
    Text(
        text = stringResource(R.string.login_button_text),
        style = MaterialTheme.typography.displayLarge
    )
}
