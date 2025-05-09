package com.example.bootcampfinalproject.domain.usecase.movies

import com.example.bootcampfinalproject.data.remote.model.Genres
import com.example.bootcampfinalproject.domain.model.Genre
import com.example.bootcampfinalproject.domain.repository.TmdbRepository
import com.example.bootcampfinalproject.util.ResponseState
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val repository: TmdbRepository
) {
    suspend operator fun invoke(): ResponseState<List<Genre>> = repository.getMovieGenres()
}