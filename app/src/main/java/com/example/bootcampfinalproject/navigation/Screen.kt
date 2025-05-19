package com.example.bootcampfinalproject.navigation

import kotlinx.serialization.Serializable


sealed interface Screen{
    @Serializable
    data object LoginScreen : Screen
    @Serializable
    data object RegisterScreen : Screen
    @Serializable
    data object HomeScreen : Screen
    @Serializable
    data class DetailScreen(val movieId: Int) : Screen
    @Serializable
    data object SearchScreen : Screen
    @Serializable
    data object BookmarkScreen : Screen
    @Serializable
    data object SettingsScreen : Screen
}