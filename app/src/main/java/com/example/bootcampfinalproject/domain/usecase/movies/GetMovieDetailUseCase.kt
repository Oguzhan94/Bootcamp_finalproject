package com.example.bootcampfinalproject.domain.usecase.movies

import com.example.bootcampfinalproject.domain.repository.TmdbRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
   private val repository: TmdbRepository
){
    suspend operator fun invoke(movieId: Int) = repository.getMovieDetails(movieId)
}