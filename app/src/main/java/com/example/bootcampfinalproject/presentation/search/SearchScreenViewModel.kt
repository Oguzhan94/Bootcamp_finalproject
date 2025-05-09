package com.example.bootcampfinalproject.presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bootcampfinalproject.domain.usecase.movies.GetSearchUseCase
import com.example.bootcampfinalproject.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val getSearchUseCase: GetSearchUseCase
) : ViewModel() {

    var searchQuery by mutableStateOf("")
        private set

    fun onSearchQueryChange(query: String) {
        searchQuery = query
        searchMovie()
    }

    private fun searchMovie() {
        viewModelScope.launch {
            getSearchUseCase(searchQuery).collect {
                when (it) {
                    is ResponseState.Success -> {
                        it.data
                    }

                    is ResponseState.Error -> {
                        it.message
                    }

                    ResponseState.Loading -> {
                    }
                }

            }
        }


    }
}