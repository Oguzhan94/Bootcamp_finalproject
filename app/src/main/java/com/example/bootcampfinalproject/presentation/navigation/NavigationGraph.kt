package com.example.bootcampfinalproject.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bootcampfinalproject.presentation.home.HomeScreen
import com.example.bootcampfinalproject.presentation.authorization.login.LoginScreen
import com.example.bootcampfinalproject.presentation.authorization.register.RegisterScreen
import com.example.bootcampfinalproject.presentation.detail.DetailScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: Screen
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Screen.LoginScreen> {
            LoginScreen(navController)
        }
        composable<Screen.RegisterScreen> {
            RegisterScreen(navController)
        }
        composable<Screen.HomeScreen> {
            HomeScreen(navController)
        }
        composable<Screen.DetailScreen> {
            DetailScreen()
        }
    }
}