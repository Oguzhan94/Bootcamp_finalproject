package com.example.bootcampfinalproject.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bootcampfinalproject.presentation.authorization.login.LoginScreen
import com.example.bootcampfinalproject.presentation.authorization.register.RegisterScreen
import com.example.bootcampfinalproject.presentation.detail.DetailScreen
import com.example.bootcampfinalproject.presentation.home.HomeScreen
import com.example.bootcampfinalproject.presentation.theme.onSurfaceLight

import kotlin.reflect.KClass

data class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(startDestination: Screen) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val items = listOf(
        BottomNavItem("Home", Icons.Default.Home, Screen.HomeScreen),
        BottomNavItem("Detail", Icons.Default.Abc, Screen.DetailScreen)
    )

    fun isCurrentScreen(screen: KClass<out Screen>): Boolean {
        return currentDestination?.hierarchy?.any {
            it.route?.contains(screen.simpleName ?: "") == true
        } == true
    }

    val isAuthScreen =
        isCurrentScreen(Screen.LoginScreen::class) || isCurrentScreen(Screen.RegisterScreen::class)

    val title = when {
        isCurrentScreen(Screen.HomeScreen::class) -> "Home"
        isCurrentScreen(Screen.DetailScreen::class) -> "Detail"
        isCurrentScreen(Screen.LoginScreen::class) -> "Login"
        isCurrentScreen(Screen.RegisterScreen::class) -> "Register"
        else -> ""
    }

    Scaffold(
        topBar = {
            if (!isAuthScreen) {
                Column {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                text = "Movies",
                                style = MaterialTheme.typography.displayMedium
                            )
                        },
                        navigationIcon = {
                            if (!isCurrentScreen(Screen.HomeScreen::class)) {
                                IconButton(
                                    onClick = { navController.navigateUp() }
                                ) {
                                    Icon(
                                        Icons.AutoMirrored.Default.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                }
                            }
                        }
                    )
                    HorizontalDivider(Modifier.fillMaxWidth(), thickness = 1.dp)
                }
            }
        },
        bottomBar = {
            if (!isAuthScreen) {
                NavigationBar(
                ) {
                    items.forEach { item ->
                        NavigationBarItem(
                            selected = currentDestination?.hierarchy?.any {
                                it.route?.contains(item.screen::class.simpleName ?: "") == true
                            } == true,
                            onClick = {
                                if (item.screen == Screen.HomeScreen) {
                                    navController.navigate(Screen.HomeScreen) {
                                        popUpTo(0) { inclusive = true }
                                        launchSingleTop = true
                                    }
                                } else {
                                    navController.navigate(item.screen) {
                                        launchSingleTop = true
                                    }
                                }
                            },
                            icon = {
                                Icon(
                                    item.icon,
                                    contentDescription = item.title,
                                )
                            },
                            label = { Text(item.title) },
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
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
}
