package com.example.bootcampfinalproject.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.bootcampfinalproject.data.remote.TmdbApi
import com.example.bootcampfinalproject.data.remote.mapper.toDomain
import com.example.bootcampfinalproject.data.remote.paging.MoviePagingSource
import com.example.bootcampfinalproject.domain.model.Movie
import com.example.bootcampfinalproject.domain.repository.TmdbRepository
import com.example.bootcampfinalproject.util.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: TmdbApi
) : TmdbRepository {
    override fun getUpComingMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 1),
            pagingSourceFactory = { MoviePagingSource(api) }
        ).flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }
}