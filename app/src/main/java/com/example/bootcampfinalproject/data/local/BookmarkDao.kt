package com.example.bootcampfinalproject.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM bookmarks WHERE userId = :userId")
    suspend fun getBookmarks(userId: String): List<BookmarkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmark: BookmarkEntity)

    @Delete
    suspend fun deleteBookmark(bookmark: BookmarkEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarks WHERE movieId = :movieId AND userId = :userId)")
    suspend fun isBookmarked(movieId: Int, userId: String): Boolean
}