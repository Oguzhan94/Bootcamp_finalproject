package com.example.bootcampfinalproject.data.remote

import com.example.bootcampfinalproject.data.remote.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {

    @GET("movie/upcoming")
        suspend fun getUpComingMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int,
        ): MovieResponse
}