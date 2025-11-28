package com.inforcap.moviesapirest.models

import com.google.gson.annotations.SerializedName

data class ArtistEntity(
    @SerializedName("idArtist")
    var id: String = "",

    @SerializedName("strArtist")
    var name: String = "",

    @SerializedName("strArtistThumb")
    var image: String? = null,

    @SerializedName("strArtistLogo")
    var logo: String? = null,

    @SerializedName("strArtistBanner")
    var banner: String? = null,

    @SerializedName("strGenre")
    var genre: String? = null,

    @SerializedName("strCountry")
    var country: String? = null,

    @SerializedName("intFormedYear")
    var formedYear: String? = null,

    @SerializedName("strBiographyEN")
    var biography: String? = null,

    @SerializedName("strStyle")
    var style: String? = null,

    @SerializedName("strMood")
    var mood: String? = null,

    @SerializedName("strWebsite")
    var website: String? = null,

    @SerializedName("strFacebook")
    var facebook: String? = null,

    @SerializedName("intMembers")
    var members: String? = null
)

data class ArtistResponse(
    @SerializedName("artists")
    var artists: List<ArtistEntity>?
)

data class AlbumEntity(
    @SerializedName("idAlbum")
    var id: String = "",

    @SerializedName("strAlbum")
    var name: String = "",

    @SerializedName("strArtist")
    var artistName: String = "",

    @SerializedName("strAlbumThumb")
    var image: String? = null,

    @SerializedName("intYearReleased")
    var year: String? = null,

    @SerializedName("strGenre")
    var genre: String? = null,

    @SerializedName("strDescriptionEN")
    var description: String? = null,

    @SerializedName("intScore")
    var score: String? = null,

    @SerializedName("intScoreVotes")
    var votes: String? = null
)

data class AlbumResponse(
    @SerializedName("album")
    var albums: List<AlbumEntity>?
)

data class TrendingResponse(
    @SerializedName("trending")
    var trending: List<AlbumEntity>?
)