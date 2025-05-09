package com.example.bootcampfinalproject.data.remote.mapper

import com.example.bootcampfinalproject.data.remote.model.GenreDto
import com.example.bootcampfinalproject.data.remote.model.Genres
import com.example.bootcampfinalproject.domain.model.Genre


fun GenreDto.toDomain(): Genre {
    return Genre(id = this.id, name = this.name)
}
fun List<GenreDto>.toDomainList(): List<Genre> = map { it.toDomain() }

fun Genres.toDomain(): List<Genre> {
    return this.genres.map { it.toDomain() }
}
