package com.example.bootcampfinalproject.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable
@Serializable
@Parcelize
data class Movie(
    val adult: Boolean,
    val backdropPath: String?,
    val genreNames: List<String>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
): Parcelable