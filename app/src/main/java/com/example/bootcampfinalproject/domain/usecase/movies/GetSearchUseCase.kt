package com.example.bootcampfinalproject.domain.usecase.movies

import com.example.bootcampfinalproject.domain.model.Movie
import com.example.bootcampfinalproject.domain.repository.TmdbRepository
import com.example.bootcampfinalproject.util.ResponseState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchUseCase @Inject constructor(
    private val repository: TmdbRepository
) {
    operator fun invoke(query : String) : Flow<ResponseState<List<Movie>>> {
        return repository.getSearchMovies(query)
    }
}