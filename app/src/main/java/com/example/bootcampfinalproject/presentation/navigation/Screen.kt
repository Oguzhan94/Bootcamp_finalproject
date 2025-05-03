package com.example.bootcampfinalproject.presentation.navigation

sealed interface Screen{
    
    object LoginScreen : Screen
    object RegisterScreen : Screen
    object HomeScreen : Screen

}