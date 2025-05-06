package com.example.bootcampfinalproject.presentation.navigation

import kotlinx.serialization.Serializable


sealed interface Screen{
    @Serializable
    data object LoginScreen : Screen
    @Serializable
    data object RegisterScreen : Screen
    @Serializable
    data object HomeScreen : Screen
    @Serializable
    data object DetailScreen : Screen

}