package com.example.bootcampfinalproject.data.local.mapper

import com.example.bootcampfinalproject.data.local.BookmarkEntity
import com.example.bootcampfinalproject.domain.Bookmark

fun BookmarkEntity.toDomain(): Bookmark {
    return Bookmark(
        movieId = movieId,
        userId = userId,
        title = title,
        posterPath = posterPath,
        releaseDate = releaseDate,
        averageVote = averageVote
    )
}

fun Bookmark.toEntity(): BookmarkEntity {
    return BookmarkEntity(
        movieId = movieId,
        userId = userId,
        title = title,
        posterPath = posterPath,
        releaseDate = releaseDate,
        averageVote = averageVote
    )
}