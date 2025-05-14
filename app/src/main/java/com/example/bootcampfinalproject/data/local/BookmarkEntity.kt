package com.example.bootcampfinalproject.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey val movieId: Int,
    val userId: String,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val averageVote: String
)