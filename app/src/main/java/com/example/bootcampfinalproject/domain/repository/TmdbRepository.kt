package com.example.bootcampfinalproject.domain.repository

import androidx.paging.PagingData
import com.example.bootcampfinalproject.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface TmdbRepository{
     fun getUpComingMovies():  Flow<PagingData<Movie>>
     fun getTopRatedMovies():  Flow<PagingData<Movie>>

}