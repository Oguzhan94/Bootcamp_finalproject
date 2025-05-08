package com.example.bootcampfinalproject.domain.repository

import androidx.paging.PagingData
import com.example.bootcampfinalproject.domain.model.Movie
import com.example.bootcampfinalproject.util.ResponseState
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface TmdbRepository{
     fun getUpComingMovies():  Flow<PagingData<Movie>>
}