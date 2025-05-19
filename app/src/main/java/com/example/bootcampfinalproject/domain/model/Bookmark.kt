package com.example.bootcampfinalproject.domain.model

data class Bookmark(
    val movieId: Int,
    val userId: String,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val averageVote: String
)