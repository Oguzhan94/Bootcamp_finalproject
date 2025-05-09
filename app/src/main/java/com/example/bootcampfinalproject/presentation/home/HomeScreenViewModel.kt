package com.example.bootcampfinalproject.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.bootcampfinalproject.R
import com.example.bootcampfinalproject.domain.model.Movie
import com.example.bootcampfinalproject.domain.usecase.movies.GetGenresUseCase
import com.example.bootcampfinalproject.domain.usecase.movies.GetTopRatedMoviesUseCase
import com.example.bootcampfinalproject.domain.usecase.movies.GetUpcomingMoviesUseCase
import com.example.bootcampfinalproject.presentation.home.HomeUiState
import com.example.bootcampfinalproject.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getGenresUseCase: GetGenresUseCase
) : ViewModel() {

    private val _upComingMovies = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val upComingMovies: StateFlow<PagingData<Movie>> = _upComingMovies

    private val _topRatedMovies = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val topRatedMovies: StateFlow<PagingData<Movie>> = _topRatedMovies

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Idle)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        fetchHomeScreenData()
    }

    fun onLoadStateChanged(loadState: LoadState) {
        when (loadState) {
            is LoadState.Error -> {
                val errorMsg = loadState.error.localizedMessage ?: R.string.unknown_error.toString()
                _uiState.value = HomeUiState.Error(errorMsg)
            }

            is LoadState.Loading -> _uiState.value = HomeUiState.Loading
            is LoadState.NotLoading -> _uiState.value = HomeUiState.Success
        }
    }

    fun refreshData() {
        fetchHomeScreenData()
    }

    private fun fetchHomeScreenData() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading

            try {
                when (val result = getGenresUseCase()) {
                    is ResponseState.Error -> {
                        _uiState.value = HomeUiState.Error(result.message)
                        return@launch
                    }

                    is ResponseState.Success -> {
                        loadMovies()
                    }

                    else -> Unit
                }
            } catch (e: Exception) {
                _uiState.value =
                    HomeUiState.Error(e.localizedMessage ?: R.string.unknown_error.toString())
            }
        }
    }

    private fun loadMovies() {
        viewModelScope.launch {
            launch {
                getUpcomingMoviesUseCase()
                    .cachedIn(viewModelScope)
                    .collect {
                        _upComingMovies.value = it
                    }
            }

            launch {
                getTopRatedMoviesUseCase()
                    .cachedIn(viewModelScope)
                    .collect {
                        _topRatedMovies.value = it
                    }
            }

            _uiState.value = HomeUiState.Success
        }
    }
}