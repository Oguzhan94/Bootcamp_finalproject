package com.example.bootcampfinalproject.data.repository

import com.example.bootcampfinalproject.data.local.BookmarkDao
import com.example.bootcampfinalproject.data.local.mapper.toDomain
import com.example.bootcampfinalproject.data.local.mapper.toEntity
import com.example.bootcampfinalproject.domain.Bookmark
import com.example.bootcampfinalproject.domain.repository.BookmarkRepository
import com.example.bootcampfinalproject.util.ResponseState
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val bookmarkDao: BookmarkDao
) : BookmarkRepository {

    override suspend fun getBookmarks(userId: String): ResponseState<List<Bookmark>> {
        return try {
            val bookmarks = bookmarkDao.getBookmarks(userId).map { it.toDomain() }
            ResponseState.Success(bookmarks)
        } catch (e: Exception) {
            ResponseState.Error("Error fetching bookmarks: ${e.message}")
        }
    }

    override suspend fun insertBookmark(bookmark: Bookmark): ResponseState<Unit> {
        return try {
            bookmarkDao.insertBookmark(bookmark.toEntity())
            ResponseState.Success(Unit)
        } catch (e: Exception) {
            ResponseState.Error("Error inserting bookmark: ${e.message}")
        }
    }

    override suspend fun deleteBookmark(bookmark: Bookmark): ResponseState<Unit> {
        return try {
            bookmarkDao.deleteBookmark(bookmark.toEntity())
            ResponseState.Success(Unit)
        } catch (e: Exception) {
            ResponseState.Error("Error deleting bookmark: ${e.message}")
        }
    }

    override suspend fun isBookmarked(movieId: Int, userId: String): ResponseState<Boolean> {
        return try {
            val isBookmarked = bookmarkDao.isBookmarked(movieId, userId)
            ResponseState.Success(isBookmarked)
        } catch (e: Exception) {
            ResponseState.Error("Error checking bookmark: ${e.message}")
        }
    }
}