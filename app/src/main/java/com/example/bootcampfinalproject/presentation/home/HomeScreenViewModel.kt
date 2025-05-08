package com.example.bootcampfinalproject.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.bootcampfinalproject.domain.model.Movie
import com.example.bootcampfinalproject.domain.usecase.movies.GetTopRatedMoviesUseCase
import com.example.bootcampfinalproject.domain.usecase.movies.GetUpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
) : ViewModel() {

    private val _upComingMovies = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val upComingMovies: StateFlow<PagingData<Movie>> = _upComingMovies


    private val _topRatedMovies = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val topRatedMovies: StateFlow<PagingData<Movie>> = _topRatedMovies

    init {
        viewModelScope.launch {
            launch {
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