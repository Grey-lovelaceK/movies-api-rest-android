package com.inforcap.moviesapirest.network

import com.inforcap.moviesapirest.network.response.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("now_playing")
    suspend fun getAllMovies(
        @Query("api_key") apiKey: String
    ) : Response<MovieResponse>

    @GET("popular")
    suspend fun getPopular(
        @Query("api_key") apiKey: String
    ) : Response<MovieResponse>

    @GET("top_rated")
    suspend fun getTopRated(
        @Query("api_key") apiKey: String
    ) : Response<MovieResponse>

    @GET("upcoming")
    suspend fun getUpComing(
        @Query("api_key") apiKey: String
    ) : Response<MovieResponse>

}