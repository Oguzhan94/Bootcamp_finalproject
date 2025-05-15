package com.example.bootcampfinalproject.presentation.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bootcampfinalproject.domain.usecase.auth.CurrentUserUseCase
import com.example.bootcampfinalproject.domain.usecase.bookmark.GetBookmarksUseCase
import com.example.bootcampfinalproject.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkScreenViewModel @Inject constructor(
    private val getBookmarksUseCase: GetBookmarksUseCase,
    private val getCurrentUserUseCase: CurrentUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<BookmarkScreenUiState>(BookmarkScreenUiState.Idle)
    val uiState: StateFlow<BookmarkScreenUiState> = _uiState.asStateFlow()

    init {
        observeCurrentUser()
    }

    private fun observeCurrentUser() {
        viewModelScope.launch {
            getCurrentUserUseCase().collectLatest { response ->
                if (response is ResponseState.Success && response.data != null) {
                    val userId = response.data.uid
                    getBookmarks(userId)
                }
            }
        }
    }

    private fun getBookmarks(userId: String) {
        viewModelScope.launch {
            when (val result = getBookmarksUseCase(userId)) {
                is ResponseState.Success -> {
                    _uiState.value = BookmarkScreenUiState.Success(result.data)
                }

                is ResponseState.Error -> {
                    _uiState.value = BookmarkScreenUiState.Error(result.message)
                }

                is ResponseState.Loading -> {
                    _uiState.value = BookmarkScreenUiState.Loading
                }
            }
        }
    }
}