package com.example.bootcampfinalproject.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bootcampfinalproject.presentation.authorization.login.LoginScreenViewModel
import com.example.bootcampfinalproject.presentation.navigation.NavigationGraph
import com.example.bootcampfinalproject.presentation.navigation.Screen
import com.example.bootcampfinalproject.presentation.theme.BootcampFinalProjectTheme
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
            BootcampFinalProjectTheme {
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                val startDestination = when (uiState) {
                    is AuthUiState.Success -> Screen.HomeScreen
                    is AuthUiState.Error,
                    AuthUiState.Idle -> Screen.LoginScreen
                    AuthUiState.Loading -> null
                }
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        if (startDestination != null) {
                            NavigationGraph(
                                startDestination = startDestination
                            )
                        }
                    }
                }
            }
        }
    }
}
