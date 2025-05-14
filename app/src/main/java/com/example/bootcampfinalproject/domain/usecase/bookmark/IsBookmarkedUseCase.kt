package com.example.bootcampfinalproject.domain.usecase.bookmark

import com.example.bootcampfinalproject.domain.repository.BookmarkRepository
import com.example.bootcampfinalproject.util.ResponseState
import javax.inject.Inject

class IsBookmarkedUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    suspend operator fun invoke(movieId: Int, userId: String): ResponseState<Boolean> {
        return bookmarkRepository.isBookmarked(movieId, userId)
    }
}