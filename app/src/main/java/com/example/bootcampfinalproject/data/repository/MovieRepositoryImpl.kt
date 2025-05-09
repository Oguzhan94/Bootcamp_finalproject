package com.example.bootcampfinalproject.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.bootcampfinalproject.data.remote.TmdbApi
import com.example.bootcampfinalproject.data.remote.mapper.toDomain
import com.example.bootcampfinalproject.data.remote.model.GenreDto
import com.example.bootcampfinalproject.data.remote.paging.MoviePagingSource
import com.example.bootcampfinalproject.domain.MovieCategory
import com.example.bootcampfinalproject.domain.model.Genre
import com.example.bootcampfinalproject.domain.model.Movie
import com.example.bootcampfinalproject.domain.repository.TmdbRepository
import com.example.bootcampfinalproject.util.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: TmdbApi
) : TmdbRepository {
    private var genresList: List<GenreDto> = emptyList()

    override fun getUpComingMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1, enablePlaceholders = false
            ), pagingSourceFactory = {
                MoviePagingSource(
                    api, MovieCategory.UP_COMING
                )
            }).flow.map { pagingData ->
            pagingData.map { it.toDomain(genresList) }
        }
    }

    override fun getTopRatedMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1, enablePlaceholders = false
            ), pagingSourceFactory = {
                MoviePagingSource(
                    api, MovieCategory.TOP_RATED
                )
            }).flow.map { pagingData ->
            pagingData.map { it.toDomain(genresList) }
        }
    }

    override suspend fun getMovieGenres(): ResponseState<List<Genre>> {
        ResponseState.Loading
        return try {
            val response = api.getGenres()
            genresList = response.genres
            ResponseState.Success(response.genres.map { it.toDomain() })
        } catch (e: Exception) {
            ResponseState.Error(e.localizedMessage ?: "Bir hata oluştu")
        }
    }

    override fun getSearchMovies(query: String): Flow<ResponseState<List<Movie>>> {
        return flow {
            emit(ResponseState.Loading)
            val response = api.searchMovie(query)
            emit(
                ResponseState.Success(
                response.results.map {
                    it.toDomain(genresList)
                }))
        }.catch {
            emit(ResponseState.Error(it.localizedMessage ?: "Bir hata oluştu"))

        }
    }
}