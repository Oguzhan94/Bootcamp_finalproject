package com.example.bootcampfinalproject.data.remote

import com.example.bootcampfinalproject.data.remote.model.Genres
import com.example.bootcampfinalproject.data.remote.model.MovieDetailsDto
import com.example.bootcampfinalproject.data.remote.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
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


    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("language") language: String = "en-US",
        @Query("include_adult") includeAdult: Boolean = false,
        ): MovieResponse
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en-US",
    ): MovieDetailsDto


}