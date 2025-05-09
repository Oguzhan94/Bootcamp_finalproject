package com.example.bootcampfinalproject.data.remote

import com.example.bootcampfinalproject.data.remote.model.Genres
import com.example.bootcampfinalproject.data.remote.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {

    @GET("movie/upcoming")
    suspend fun getUpComingMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int,
    ): MovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int,
    ): MovieResponse

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("language") language: String = "en-US",
    ): Genres

}