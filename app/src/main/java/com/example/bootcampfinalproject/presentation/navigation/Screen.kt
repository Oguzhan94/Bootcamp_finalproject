package com.example.bootcampfinalproject.presentation.navigation

import kotlinx.serialization.Serializable


sealed interface Screen{
    @Serializable
    data object LoginScreen : Screen
    @Serializable
    data object RegisterScreen : Screen
    @Serializable
    data object HomeScreen : Screen
//    @Serializable
//    data class DetailScreen(val movieJson: String) : Screen
    @Serializable
    data class DetailScreen(val movieId: Int) : Screen
    @Serializable
    data object SearchScreen : Screen

    companion object {
        const val DETAIL_SCREEN_ROUTE = "detail_screen"
    }
}