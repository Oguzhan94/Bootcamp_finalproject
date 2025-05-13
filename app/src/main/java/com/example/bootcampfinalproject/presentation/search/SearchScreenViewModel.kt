package com.example.bootcampfinalproject.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bootcampfinalproject.domain.usecase.movies.GetSearchUseCase
import com.example.bootcampfinalproject.presentation.SearchScreenUiState
import com.example.bootcampfinalproject.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val getSearchUseCase: GetSearchUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<SearchScreenUiState>(SearchScreenUiState.Idle)
    val uiState: StateFlow<SearchScreenUiState> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow<String>("")
    val searchQuery: StateFlow<String> = _searchQuery

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    init {
        viewModelScope.launch {
            searchQuery
                .debounce(300)
                .filter { it.length >= 3 }
                .distinctUntilChanged()
                .collect { query ->
                    searchMovie(query)
                }
        }
    }


    fun onClearQuery() {
        _searchQuery.value = ""
        _uiState.value = SearchScreenUiState.Idle
    }

    private fun searchMovie(query: String) {
        viewModelScope.launch {
            _uiState.value = SearchScreenUiState.Loading
            getSearchUseCase(query).collect {
                when (it) {
                    is ResponseState.Success -> {
                        _uiState.value = SearchScreenUiState.Success(it.data)
                    }

                    is ResponseState.Error -> {
                        _uiState.value = SearchScreenUiState.Error(it.message)
                    }

                    ResponseState.Loading -> {
                        _uiState.value = SearchScreenUiState.Loading
                    }
                }

            }
        }


    }
}