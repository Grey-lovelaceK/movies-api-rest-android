package com.inforcap.moviesapirest.network

import com.inforcap.moviesapirest.models.AlbumResponse
import com.inforcap.moviesapirest.models.ArtistResponse
import com.inforcap.moviesapirest.models.TrendingResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("search.php")
    suspend fun searchArtist(
        @Query("s") artistName: String
    ): Response<ArtistResponse>


    @GET("album.php")
    suspend fun getAlbumsByArtist(
        @Query("i") artistId: String
    ): Response<AlbumResponse>


    @GET("searchalbum.php")
    suspend fun searchAlbum(
        @Query("s") artistName: String
    ): Response<AlbumResponse>


    @GET("trending.php")
    suspend fun getTrending(
        @Query("country") country: String = "us",
        @Query("type") type: String = "itunes",
        @Query("format") format: String = "albums"
    ): Response<TrendingResponse>

    companion object {
        private const val BASE_URL = "https://www.theaudiodb.com/api/v1/json/2/"

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}