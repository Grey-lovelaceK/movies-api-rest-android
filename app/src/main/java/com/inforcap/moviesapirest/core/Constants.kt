package com.inforcap.moviesapirest.core

import com.inforcap.moviesapirest.BuildConfig

object Constants {
    // API Key se lee desde BuildConfig (configurado en build.gradle)
    const val API_KEY = BuildConfig.TMDB_API_KEY

    // URLs de la API
    const val API_URL = "https://api.themoviedb.org/3/movie/"
    const val API_URL_IMAGE = "https://image.tmdb.org/t/p/w500"

    // Dimensiones de imágenes
    const val IMAGE_WIDTH = 500
    const val IMAGE_HEIGHT = 750

    // Nombres de categorías
    const val CATEGORY_ALLMOVIES = "🎬 Now Playing"
    const val CATEGORY_POPULARITY = "🔥 Popular Movies"
    const val CATEGORY_TOPRATED = "⭐ Top Rated"
    const val CATEGORY_UPCOMING = "🎯 Upcoming Movies"
}