package com.example.bootcampfinalproject.presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bootcampfinalproject.domain.usecase.movies.GetSearchUseCase
import com.example.bootcampfinalproject.presentation.SearchScreenUiState
import com.example.bootcampfinalproject.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val getSearchUseCase: GetSearchUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<SearchScreenUiState>(SearchScreenUiState.Idle)
    val uiState: StateFlow<SearchScreenUiState> = _uiState.asStateFlow()

    var searchQuery by mutableStateOf("")
        private set

    fun onSearchQueryChange(query: String) {
        searchQuery = query
        if (query.length >= 3){
            searchMovie()
        } else {
            _uiState.value = SearchScreenUiState.Idle
        }
    }

    fun onClearQuery(){
        searchQuery = ""
        _uiState.value = SearchScreenUiState.Idle
    }

    private fun searchMovie() {
        viewModelScope.launch {
            _uiState.value = SearchScreenUiState.Loading
            getSearchUseCase(searchQuery).collect {
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