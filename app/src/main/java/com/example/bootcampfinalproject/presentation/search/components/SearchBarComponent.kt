package com.example.bootcampfinalproject.presentation.search.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bootcampfinalproject.presentation.search.SearchScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarComponent(viewModel: SearchScreenViewModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            inputField = {
                SearchBarDefaults.InputField(
                    query = viewModel.searchQuery,
                    onQueryChange = { viewModel.onSearchQueryChange(it) },
                    onSearch = {},
                    expanded = false,
                    onExpandedChange = { },
                    placeholder = {
                        Text("Search Movie")
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon"
                        )
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = { viewModel.onClearQuery() }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear Icon"
                            )
                        }
                    },
                )
            },
            expanded = false,
            onExpandedChange = { },
//            modifier = TODO(),
            shape = RoundedCornerShape(8.dp),
//            colors = TODO(),
//            tonalElevation = TODO(),
//            shadowElevation = TODO(),
            windowInsets = WindowInsets(0, 0, 0, 0)
        ) {}
    }

}
