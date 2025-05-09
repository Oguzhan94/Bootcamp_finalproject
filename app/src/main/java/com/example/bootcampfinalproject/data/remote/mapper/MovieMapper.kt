package com.example.bootcampfinalproject.data.remote.mapper

import com.example.bootcampfinalproject.data.remote.model.GenreDto
import com.example.bootcampfinalproject.data.remote.model.Result
import com.example.bootcampfinalproject.domain.model.Movie
import com.google.android.play.core.integrity.v

fun Result.toDomain(genres: List<GenreDto>): Movie {
    val genreNames = genre_ids.map { genreId ->
        genres.find { it.id == genreId }?.name ?: "Unknown"
    }

    return Movie(
        adult = adult,
        backdropPath = backdrop_path,
        genreNames = genreNames,
        id = id,
        originalLanguage = original_language,
        originalTitle = original_title,
        overview = overview,
        popularity = popularity,
        posterPath = poster_path,
        releaseDate = release_date,
        title = title,
        video = video,
        voteAverage = vote_average,
        voteCount = vote_count
    )
}
