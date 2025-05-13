package com.example.bootcampfinalproject.domain.repository

import androidx.paging.PagingData
import com.example.bootcampfinalproject.domain.model.Genre
import com.example.bootcampfinalproject.domain.model.Movie
import com.example.bootcampfinalproject.domain.model.MovieDetail
import com.example.bootcampfinalproject.util.ResponseState
import kotlinx.coroutines.flow.Flow

interface TmdbRepository{
     fun getUpComingMovies():  Flow<PagingData<Movie>>
     fun getTopRatedMovies():  Flow<PagingData<Movie>>
     suspend fun getMovieGenres(): ResponseState<List<Genre>>
     fun getSearchMovies(query: String): Flow<ResponseState<List<Movie>>>
     suspend fun getMovieDetails(movieId: Int): ResponseState<MovieDetail>
}