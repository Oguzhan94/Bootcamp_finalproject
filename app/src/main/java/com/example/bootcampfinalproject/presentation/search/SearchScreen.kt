package com.example.bootcampfinalproject.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController, snackBarHostState: SnackbarHostState) {
    val viewModel = hiltViewModel<SearchScreenViewModel>()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(Modifier.height(20.dp))
        SearchBar(
            inputField = {
                SearchBarDefaults.InputField(
                    query = viewModel.searchQuery,
                    onQueryChange = { viewModel.onSearchQueryChange(it) },
                    onSearch = {},
                    expanded = false,
                    onExpandedChange = { },
                    placeholder = { },
                    leadingIcon = { },
                    trailingIcon = { },
                )
            },
            expanded = false,
            onExpandedChange = {  },
//            modifier = TODO(),
//            shape = TODO(),
//            colors = TODO(),
//            tonalElevation = TODO(),
//            shadowElevation = TODO(),
//            windowInsets = TODO()
        ) {}
    }
}