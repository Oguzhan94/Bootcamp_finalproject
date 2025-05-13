package com.example.bootcampfinalproject.data.remote.mapper

import com.example.bootcampfinalproject.data.remote.model.GenreDto
import com.example.bootcampfinalproject.data.remote.model.MovieDetailsDto
import com.example.bootcampfinalproject.data.remote.model.Result
import com.example.bootcampfinalproject.domain.model.Movie
import com.example.bootcampfinalproject.domain.model.MovieDetail

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

fun MovieDetailsDto.toDomain(): MovieDetail {
    return MovieDetail(
        adult = this.adult,
        backdrop_path = this.backdrop_path,
        belongs_to_collection = this.belongs_to_collection,
        budget = this.budget,
        genres = this.genres,
        homepage = this.homepage,
        id = this.id,
        imdb_id = this.imdb_id,
        origin_country = this.origin_country,
        original_language = this.original_language,
        original_title = this.original_title,
        overview = this.overview,
        popularity = this.popularity,
        poster_path = this.poster_path,
        production_companies = this.production_companies,
        production_countries = this.production_countries,
        release_date = this.release_date,
        revenue = this.revenue,
        runtime = this.runtime,
        spoken_languages = this.spoken_languages,
        status = this.status,
        tagline = this.tagline,
        title = this.title,
        video = this.video,
        vote_average = this.vote_average,
        vote_count = this.vote_count
    )
}