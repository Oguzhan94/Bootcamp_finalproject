package com.example.bootcampfinalproject.data.remote.mapper

import com.example.bootcampfinalproject.data.remote.model.Result
import com.example.bootcampfinalproject.domain.model.Movie
import com.google.android.play.core.integrity.v

fun Result.toDomain(): Movie {
    return Movie(
        adult = adult,
        backdropPath = backdrop_path,
        genreIds = genre_ids,
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
