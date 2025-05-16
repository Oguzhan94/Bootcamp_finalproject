package com.example.bootcampfinalproject.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.LibraryBooks
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bootcampfinalproject.R
import com.example.bootcampfinalproject.presentation.authorization.login.LoginScreen
import com.example.bootcampfinalproject.presentation.authorization.register.RegisterScreen
import com.example.bootcampfinalproject.presentation.bookmark.BookmarkScreen
import com.example.bootcampfinalproject.presentation.detail.DetailScreen
import com.example.bootcampfinalproject.presentation.home.HomeScreen
import com.example.bootcampfinalproject.presentation.search.SearchScreen
import com.example.bootcampfinalproject.presentation.settings.SettingsScreen
import kotlin.reflect.KClass

data class BottomNavItem(
    val title: String, val icon: ImageVector, val screen: Screen
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(startDestination: Screen) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val snackBarHostState = remember { SnackbarHostState() }

    val items = listOf(
        BottomNavItem("Home", Icons.Default.Home, Screen.HomeScreen),
        BottomNavItem("Search", Icons.Default.Search, Screen.SearchScreen),
        BottomNavItem("Bookmark", Icons.AutoMirrored.Filled.LibraryBooks, Screen.BookmarkScreen),
        BottomNavItem("Settings", Icons.Default.Settings, Screen.SettingsScreen)
    )

    fun isCurrentScreen(screen: KClass<out Screen>): Boolean {
        return currentDestination?.hierarchy?.any {
            it.route?.contains(screen.simpleName ?: "") == true
        } == true
    }

    val isAuthScreen =
        isCurrentScreen(Screen.LoginScreen::class) || isCurrentScreen(Screen.RegisterScreen::class)

    Scaffold(topBar = {
        if (!isAuthScreen) {
            Column {
                CenterAlignedTopAppBar(title = {
                    Text(
                        text = stringResource(R.string.screen_title),
                        style = MaterialTheme.typography.displayMedium
                    )
                }, navigationIcon = {
                    if (isCurrentScreen(Screen.DetailScreen::class)) {
                        IconButton(
                            onClick = { navController.navigateUp() }) {
                            Icon(
                                Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = stringResource(R.string.back_button_description)
                            )
                        }
                    }
                })
                HorizontalDivider(Modifier.fillMaxWidth(), thickness = 1.dp)
            }
        }
    }, bottomBar = {
        if (!isAuthScreen) {
            NavigationBar {
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
    }, snackbarHost = {
        SnackbarHost(
            hostState = snackBarHostState, modifier = Modifier.padding(bottom = 16.dp)
        )
    }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<Screen.LoginScreen> {
                LoginScreen(navController, snackBarHostState)
            }
            composable<Screen.RegisterScreen> {
                RegisterScreen(navController, snackBarHostState)
            }
            composable<Screen.HomeScreen> {
                HomeScreen(snackBarHostState, onNavigateToDetail = { movie ->
                    navController.navigate(Screen.DetailScreen(movie))
                })
            }

            composable<Screen.DetailScreen> { it ->
                DetailScreen()
            }
            composable<Screen.SearchScreen> {
                SearchScreen(snackBarHostState, onNavigateToDetail = { movie ->
                    navController.navigate(Screen.DetailScreen(movie))
                })
            }
            composable<Screen.BookmarkScreen> {
                BookmarkScreen {
                    navController.navigate(Screen.DetailScreen(it))
                }
            }
            composable<Screen.SettingsScreen> {
                SettingsScreen(navController)
            }
        }
    }
}
