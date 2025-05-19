package com.example.bootcampfinalproject.domain.repository

import com.example.bootcampfinalproject.domain.model.Bookmark
import com.example.bootcampfinalproject.util.ResponseState

interface  BookmarkRepository{
    suspend fun getBookmarks(userId: String) : ResponseState<List<Bookmark>>
    suspend fun insertBookmark(bookmark: Bookmark) : ResponseState<Unit>
    suspend fun deleteBookmark(bookmark: Bookmark) : ResponseState<Unit>
    suspend fun isBookmarked(movieId: Int, userId: String) : ResponseState<Boolean>
}