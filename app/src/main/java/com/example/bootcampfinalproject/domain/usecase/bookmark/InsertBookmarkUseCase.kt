package com.example.bootcampfinalproject.domain.usecase.bookmark

import com.example.bootcampfinalproject.domain.model.Bookmark
import com.example.bootcampfinalproject.domain.repository.BookmarkRepository
import com.example.bootcampfinalproject.util.ResponseState
import javax.inject.Inject

class InsertBookmarkUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    suspend operator fun invoke(bookmark: Bookmark): ResponseState<Unit> {
        return bookmarkRepository.insertBookmark(bookmark)

    }
}