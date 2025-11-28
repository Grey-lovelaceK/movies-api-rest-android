package com.inforcap.moviesapirest.core

object Constants {
    const val API_URL = "https://www.theaudiodb.com/api/v1/json/2/"

    const val IMAGE_WIDTH = 500
    const val IMAGE_HEIGHT = 500

    const val CATEGORY_ROCK = "🎸 Rock"
    const val CATEGORY_POP = "🎤 Pop"
    const val CATEGORY_HIPHOP = "🎧 Hip Hop"
    const val CATEGORY_ELECTRONIC = "🎹 Electronic"

    val ROCK_ARTISTS = listOf(
        "Coldplay", "Linkin Park", "Imagine Dragons", "Queen", "The Beatles",
        "Metallica", "Foo Fighters", "Red Hot Chili Peppers", "Nirvana", "AC/DC",
        "Green Day", "Muse", "Radiohead", "The Rolling Stones", "Led Zeppelin",
        "Arctic Monkeys", "The Killers", "Twenty One Pilots", "Fall Out Boy", "Panic! At The Disco"
    )

    val POP_ARTISTS = listOf(
        "Taylor Swift", "Ed Sheeran", "Ariana Grande", "The Weeknd", "Dua Lipa",
        "Billie Eilish", "Harry Styles", "Adele", "Bruno Mars", "Lady Gaga",
        "Justin Bieber", "Rihanna", "Katy Perry", "Miley Cyrus", "Shakira",
        "Selena Gomez", "Camila Cabello", "Shawn Mendes", "Charlie Puth", "Olivia Rodrigo"
    )

    val HIPHOP_ARTISTS = listOf(
        "Eminem", "Drake", "Kanye West", "Kendrick Lamar", "Post Malone",
        "Travis Scott", "J. Cole", "Lil Wayne", "Snoop Dogg", "Jay-Z",
        "50 Cent", "Tupac", "Notorious B.I.G.", "Dr. Dre", "Ice Cube",
        "Nicki Minaj", "Cardi B", "Megan Thee Stallion", "Tyler The Creator", "A$ Rocky"
    )

    val ELECTRONIC_ARTISTS = listOf(
        "Daft Punk", "Calvin Harris", "Avicii", "David Guetta", "Marshmello",
        "Skrillex", "Deadmau5", "Tiësto", "Martin Garrix", "Kygo",
        "Zedd", "Diplo", "The Chainsmokers", "Swedish House Mafia", "Disclosure",
        "Flume", "ODESZA", "Porter Robinson", "Madeon", "Alan Walker"
    )

    val GENRE_COLORS = mapOf(
        "Rock" to "#E74C3C",
        "Pop" to "#9B59B6",
        "Hip Hop" to "#F39C12",
        "Hip-Hop/Rap" to "#F39C12",
        "Electronic" to "#3498DB",
        "Alternative" to "#1ABC9C",
        "Indie" to "#16A085",
        "Metal" to "#34495E",
        "Jazz" to "#D35400",
        "Classical" to "#8E44AD",
        "Country" to "#C0392B",
        "R&B" to "#E67E22",
        "Reggae" to "#27AE60",
        "Blues" to "#2980B9",
        "Folk" to "#95A5A6"
    )


    val COUNTRY_FLAGS = mapOf(
        "United States" to "🇺🇸",
        "United Kingdom" to "🇬🇧",
        "Canada" to "🇨🇦",
        "Australia" to "🇦🇺",
        "Germany" to "🇩🇪",
        "France" to "🇫🇷",
        "Spain" to "🇪🇸",
        "Italy" to "🇮🇹",
        "Sweden" to "🇸🇪",
        "Ireland" to "🇮🇪",
        "Japan" to "🇯🇵",
        "South Korea" to "🇰🇷",
        "Mexico" to "🇲🇽",
        "Brazil" to "🇧🇷",
        "Argentina" to "🇦🇷",
        "Colombia" to "🇨🇴",
        "Chile" to "🇨🇱"
    )
}