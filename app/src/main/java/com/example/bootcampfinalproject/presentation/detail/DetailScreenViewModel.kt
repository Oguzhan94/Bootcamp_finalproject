package com.example.bootcampfinalproject.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bootcampfinalproject.domain.Bookmark
import com.example.bootcampfinalproject.domain.model.MovieDetail
import com.example.bootcampfinalproject.domain.usecase.auth.CurrentUserUseCase
import com.example.bootcampfinalproject.domain.usecase.bookmark.DeleteBookmarkUseCase
import com.example.bootcampfinalproject.domain.usecase.bookmark.InsertBookmarkUseCase
import com.example.bootcampfinalproject.domain.usecase.bookmark.IsBookmarkedUseCase
import com.example.bootcampfinalproject.domain.usecase.movies.GetMovieDetailsUseCase
import com.example.bootcampfinalproject.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailsUseCase,
    savedStateHandle: SavedStateHandle,
    private val insertBookmarkUseCase: InsertBookmarkUseCase,
    private val deleteBookmarkUseCase: DeleteBookmarkUseCase,
    private val isBookmarkedUseCase: IsBookmarkedUseCase,
    private val getCurrentUserUseCase: CurrentUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailScreenUiState>(DetailScreenUiState.Idle)
    val uiState: StateFlow<DetailScreenUiState> = _uiState.asStateFlow()

    private val _movie = MutableStateFlow<MovieDetail?>(null)
    val movie: StateFlow<MovieDetail?> = _movie.asStateFlow()

    private val _isBookmarked = MutableStateFlow(false)
    val isBookmarked: StateFlow<Boolean> = _isBookmarked.asStateFlow()

    private var currentUserId: String? = null

    init {
        val movieId = savedStateHandle.get<Int>("movieId")
        movieId?.let {
            loadMovieDetail(it)
            getCurrentUser()
        }
    }

    fun onBookmarkedChange(newBookmarked: Boolean) {
        if (newBookmarked) insertBookmark() else deleteBookmark()
        _isBookmarked.value = newBookmarked
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            getCurrentUserUseCase().collect {
                if (it is ResponseState.Success) {
                    currentUserId = it.data?.uid
                }
            }
        }
    }

    private fun loadMovieDetail(movieId: Int) {
        viewModelScope.launch {
            _uiState.value = DetailScreenUiState.Loading
            when (val result = getMovieDetailUseCase(movieId)) {
                is ResponseState.Success -> {
                    _movie.value = result.data
                    _uiState.value = DetailScreenUiState.Success(result.data)
                    currentUserId?.let {
                        checkIfBookmarked()
                    }
                }

                is ResponseState.Error -> {
                    _uiState.value = DetailScreenUiState.Error(result.message)
                }

                ResponseState.Loading -> {
                    _uiState.value = DetailScreenUiState.Loading
                }
            }
        }
    }


    private fun insertBookmark() {
        val userId = currentUserId ?: return
        val movie = _movie.value ?: return

        val bookmark = Bookmark(
            movieId = movie.id,
            userId = userId,
            title = movie.title,
            posterPath = movie.poster_path,
            releaseDate = movie.release_date,
            averageVote = String.format(Locale.US,"%.1f", movie.vote_average)
        )

        viewModelScope.launch {
            when (val result = insertBookmarkUseCase(bookmark)) {
                is ResponseState.Error -> {
                    _uiState.value = DetailScreenUiState.Error(result.message)
                }

                is ResponseState.Success -> {
                    checkIfBookmarked()
                }

                else -> Unit
            }
        }
    }

    private fun deleteBookmark() {
        val userId = currentUserId ?: return
        val movie = _movie.value ?: return

        val bookmark = Bookmark(
            movieId = movie.id,
            userId = userId,
            title = movie.title,
            posterPath = movie.poster_path,
            releaseDate = movie.release_date,
            averageVote = String.format(Locale.US,"%.1f", movie.vote_average)
        )

        viewModelScope.launch {
            when (val result = deleteBookmarkUseCase(bookmark)) {
                is ResponseState.Error -> {
                    _uiState.value = DetailScreenUiState.Error(result.message)
                }

                is ResponseState.Success -> {
                    checkIfBookmarked()
                }

                else -> Unit
            }
        }
    }

    private fun checkIfBookmarked() {
        val userId = currentUserId ?: return
        val movieId = _movie.value?.id ?: return

        viewModelScope.launch {
            when (val result = isBookmarkedUseCase(movieId, userId)) {
                is ResponseState.Error -> {
                    _uiState.value = DetailScreenUiState.Error(result.message)
                }

                is ResponseState.Success -> {
                    _isBookmarked.value = result.data
                }

                else -> Unit
            }
        }
    }
}
