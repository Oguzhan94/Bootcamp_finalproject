package com.example.bootcampfinalproject.presentation.home

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.bootcampfinalproject.data.remote.mapper.toDomain
import com.example.bootcampfinalproject.data.remote.mapper.toDomainList
import com.example.bootcampfinalproject.domain.model.Movie
import com.example.bootcampfinalproject.domain.usecase.movies.GetGenresUseCase
import com.example.bootcampfinalproject.domain.usecase.movies.GetTopRatedMoviesUseCase
import com.example.bootcampfinalproject.domain.usecase.movies.GetUpcomingMoviesUseCase
import com.example.bootcampfinalproject.presentation.AuthUiState
import com.example.bootcampfinalproject.presentation.HomeUiState
import com.example.bootcampfinalproject.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.bootcampfinalproject.R

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

    fun onStateChanged(loadState : LoadState) {
        when(loadState){
            is LoadState.Error -> _uiState.value = HomeUiState.Error(loadState.error.message ?: R.string.unknown_error.toString())
            is LoadState.Loading -> _uiState.value = HomeUiState.Loading
            is LoadState.NotLoading -> _uiState.value = HomeUiState.Success
        }
    }

    init {
        fetchHomeScreenData()
    }

    private fun fetchHomeScreenData() {
        viewModelScope.launch {
            when(val result = getGenresUseCase()){
                is ResponseState.Error ->{
                    _uiState.value = HomeUiState.Error(result.message)
                }
                ResponseState.Loading -> {
                    _uiState.value = HomeUiState.Loading
                }
                is ResponseState.Success -> {
                    _uiState.value = HomeUiState.Success
                }
            }
            launch{
                getUpcomingMoviesUseCase().cachedIn(viewModelScope).collect {
                    _upComingMovies.value = it
                }
            }

            launch {
                getTopRatedMoviesUseCase().cachedIn(viewModelScope).collect {
                    _topRatedMovies.value = it
                }
            }

        }
    }
}