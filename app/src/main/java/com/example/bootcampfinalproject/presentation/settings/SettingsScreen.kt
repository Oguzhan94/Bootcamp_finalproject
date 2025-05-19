package com.example.bootcampfinalproject.presentation.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LocalPolice
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.bootcampfinalproject.navigation.Screen
import com.example.bootcampfinalproject.presentation.settings.components.LogoutAlertDialogComponent
import com.example.bootcampfinalproject.presentation.settings.components.SettingsItemComponent

@Composable
fun SettingsScreen(navController: NavController) {
    val viewModel = hiltViewModel<SettingsScreenViewModel>()
    val isDark by viewModel.isDarkMode.collectAsStateWithLifecycle()
    val isLogout by viewModel.logoutSuccess.collectAsStateWithLifecycle()
    var showLogoutDialog by remember { mutableStateOf(false) }

    LaunchedEffect(isLogout) {
        if (isLogout) {
            navController.navigate(Screen.LoginScreen) {
                popUpTo(0)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Appearance",
                Modifier.padding(top = 15.dp, bottom = 10.dp),
                style = MaterialTheme.typography.titleLarge
            )
            SettingsItemComponent(
                Icons.Default.DarkMode,
                "Dark Mode",
                "Enable dark mode",
                trailingContent = {
                    Switch(
                        checked = isDark,
                        onCheckedChange = { viewModel.setDarkMode(it) }
                    )
                }
            )
            Text(
                text = "Account",
                Modifier.padding(top = 15.dp, bottom = 10.dp),
                style = MaterialTheme.typography.titleLarge
            )
            SettingsItemComponent(
                Icons.AutoMirrored.Default.Logout,
                "Edit Profile",
                "Edit profile information"
            )
            SettingsItemComponent(
                Icons.Default.Lock,
                "Change Password",
                "Change password"

            )
            SettingsItemComponent(
                Icons.AutoMirrored.Default.Logout,
                "Logout",
                "Sign out of the app"
            ) {
                showLogoutDialog = true
            }
            Text(
                text = "About",
                Modifier.padding(top = 15.dp, bottom = 10.dp),
                style = MaterialTheme.typography.titleLarge
            )
            SettingsItemComponent(
                Icons.Default.Android,
                "App Version",
                "1.0.0"
            )
            SettingsItemComponent(
                Icons.Default.LocalPolice,
                "Open Source Licenses",
                "View open source licenses"
            )
        }
    }
    LogoutAlertDialogComponent(
        onDismiss = { showLogoutDialog = false },
        onConfirm = { viewModel.logout() },
        showDialog = showLogoutDialog
    )
}
