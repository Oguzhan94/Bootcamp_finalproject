package com.example.bootcampfinalproject.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bootcampfinalproject.domain.model.MovieDetail
import com.example.bootcampfinalproject.domain.usecase.movies.GetMovieDetailsUseCase
import com.example.bootcampfinalproject.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DetailScreenViewModel @Inject constructor(
 private val getMovieDetailUseCase: GetMovieDetailsUseCase,
 savedStateHandle: SavedStateHandle) : ViewModel(){

    private val _uiState = MutableStateFlow<DetailScreenUiState>(DetailScreenUiState.Idle)
    val uiState: StateFlow<DetailScreenUiState> = _uiState.asStateFlow()

    private val _movie = MutableStateFlow<MovieDetail?>(null)
    val movie: StateFlow<MovieDetail?> = _movie.asStateFlow()

    init {
        val movieId = savedStateHandle.get<Int>("movieId")
        viewModelScope.launch {
            _uiState.value = DetailScreenUiState.Loading
            movieId?.let {
                when(val result = getMovieDetailUseCase(movieId)){
                    is ResponseState.Error -> _uiState.value = DetailScreenUiState.Error(result.message)
                    ResponseState.Loading -> _uiState.value = DetailScreenUiState.Loading
                    is ResponseState.Success -> _uiState.value =DetailScreenUiState.Success(result.data)
                }
            }
        }
    }
}