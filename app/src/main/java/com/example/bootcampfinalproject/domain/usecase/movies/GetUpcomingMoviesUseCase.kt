package com.example.bootcampfinalproject.domain.usecase.movies

import androidx.paging.PagingData
import com.example.bootcampfinalproject.domain.model.Movie
import com.example.bootcampfinalproject.domain.repository.TmdbRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUpcomingMoviesUseCase @Inject constructor(
    private val repository: TmdbRepository
) {
    operator fun invoke(): Flow<PagingData<Movie>> {
        return repository.getUpComingMovies()
    }
}
