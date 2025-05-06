package com.example.bootcampfinalproject.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.bootcampfinalproject.presentation.authorization.login.LoginScreenViewModel
import com.example.bootcampfinalproject.presentation.navigation.AppNavigation
import com.example.bootcampfinalproject.presentation.navigation.Screen
import com.example.bootcampfinalproject.presentation.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: LoginScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition {
            viewModel.uiState.value is AuthUiState.Loading
        }

        enableEdgeToEdge()

        setContent {
            AppTheme {
                val navController = rememberNavController()
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                val startDestination = when (uiState) {
                    is AuthUiState.Success -> Screen.HomeScreen
                    is AuthUiState.Error,
                    AuthUiState.Idle -> Screen.LoginScreen

                    AuthUiState.Loading -> null
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    if (startDestination != null) {
                        AppNavigation(startDestination = startDestination)
                    }

                }
            }
        }
    }
}
