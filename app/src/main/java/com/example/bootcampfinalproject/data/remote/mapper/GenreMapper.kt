package com.example.bootcampfinalproject.data.remote.mapper

import com.example.bootcampfinalproject.data.remote.model.GenreDto
import com.example.bootcampfinalproject.domain.model.Genre

fun GenreDto.toDomain(): Genre {
    return Genre(id = this.id, name = this.name)
}
