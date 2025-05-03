package com.example.bootcampfinalproject.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.bootcampfinalproject.presentation.navigation.NavigationGraph
import com.example.bootcampfinalproject.presentation.navigation.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                NavigationGraph(
                    startDestination = Screen.LoginScreen
                )
        }
    }
}