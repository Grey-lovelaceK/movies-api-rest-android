package com.inforcap.moviesapirest.network.response

import com.google.gson.annotations.SerializedName
import com.inforcap.moviesapirest.models.MovieEntity

data class MovieResponse(
    @SerializedName("results")
    var results: List<MovieEntity>
)
