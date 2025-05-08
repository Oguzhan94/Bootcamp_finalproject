package com.example.bootcampfinalproject.domain.usecase.movies

import com.example.bootcampfinalproject.domain.repository.TmdbRepository
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(
    private val repository: TmdbRepository
) {
    operator fun invoke() = repository.getTopRatedMovies()

}