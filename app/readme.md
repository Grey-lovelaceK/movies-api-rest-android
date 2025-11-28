# 🎵 Music Discovery - Android App

<p align="center">
  <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" />
  <img src="https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white" />
  <img src="https://img.shields.io/badge/Material%20Design-757575?style=for-the-badge&logo=material-design&logoColor=white" />
  <img src="https://img.shields.io/badge/API-TheAudioDB-1DB954?style=for-the-badge" />
</p>

Aplicación Android nativa tipo **Spotify/Apple Music** desarrollada en **Kotlin** que consume la **TheAudioDB API** para descubrir artistas musicales, álbumes y biografías. Incluye búsqueda en tiempo real, filtros por género y una interfaz moderna inspirada en plataformas de streaming.

---

## ✨ Características Principales

| Funcionalidad | Descripción |
|--------------|-------------|
| 🎸 **Géneros Musicales** | Rock, Pop, Hip Hop, Electronic |
| 🔍 **Búsqueda Global** | Encuentra cualquier artista por nombre |
| 📅 **Filtro por Año** | Descubre artistas desde 1950 hasta hoy |
| 🔄 **Ordenamiento** | Por nombre, año de formación o país |
| 🎨 **UI Dinámica** | Colores adaptados según género musical |
| 🌍 **Banderas de Países** | Identificación visual del origen |
| 📖 **Biografías** | Info detallada de cada artista |
| 📱 **Grid Moderno** | Vista tipo galería de 2 columnas |

---

## 🎯 ¿Qué hace especial esta app?

```
✅ NO necesita API Key (TheAudioDB v2 es gratis)
✅ Datos REALES de artistas famosos
✅ UI profesional tipo Spotify
✅ Información rica (biografías, redes sociales, etc)
✅ Colores dinámicos por género
✅ Búsqueda de cualquier artista mundial
```

---

## 🛠️ Stack Tecnológico

### Core
- **Lenguaje:** Kotlin 1.9+
- **Min SDK:** 25 (Android 7.1)
- **Target SDK:** 35 (Android 15)
- **IDE:** Android Studio Hedgehog 2023.1.1+

### Arquitectura MVVM

```
Model-View-ViewModel Pattern
├── Model: ArtistEntity, AlbumEntity
├── View: MainActivity + AdapterMusic
└── ViewModel: MusicViewModel (LiveData + Coroutines)
```

### Dependencias Principales

| Categoría | Librería | Versión | Propósito |
|-----------|----------|---------|-----------|
| **Networking** | Retrofit | 2.9.0 | Cliente HTTP REST |
| | Gson | 2.9.0 | Parseo JSON |
| **Async** | Coroutines | 1.7.3 | Operaciones asíncronas |
| **UI** | Material Design 3 | 1.10.0+ | Componentes modernos |
| | Glide | 4.16.0 | Carga eficiente de imágenes |
| | RecyclerView | 1.3.2 | Listas optimizadas |
| **Architecture** | ViewModel | 2.8.7 | Gestión de UI state |
| | LiveData | 2.8.7 | Datos observables |

---

## 🚀 Instalación y Configuración

### Requisitos Previos

```bash
✅ Android Studio Hedgehog o superior
✅ JDK 17+
✅ Android SDK API 35
✅ NO necesitas API Key (es gratis!)
```

### Instalación en 3 Pasos

**1. Clonar el repositorio**
```bash
git clone https://github.com/TU_USUARIO/music-discovery-android.git
cd music-discovery-android
```

**2. Abrir en Android Studio**
```
File → Open → Selecciona la carpeta
Espera la sincronización de Gradle
```

**3. Ejecutar**
```bash
# En Android Studio:
Build → Clean Project
Build → Rebuild Project
Run ▶️ (o Shift + F10)
```

---

## 📂 Estructura del Proyecto

```
app/src/main/java/com/inforcap/moviesapirest/
│
├── core/
│   └── Constants.kt                  # Constantes, colores, banderas
│
├── models/
│   ├── ArtistEntity.kt              # Modelo de artista
│   ├── AlbumEntity.kt               # Modelo de álbum
│   └── ArtistResponse.kt            # Respuestas de API
│
├── network/
│   └── ApiService.kt                # Endpoints Retrofit
│
├── viewmodel/
│   └── MusicViewModel.kt            # Lógica de negocio
│
└── ui/
    ├── MainActivity.kt              # Activity principal
    └── AdapterMusic.kt              # Adapter RecyclerView
```

---

## 🎨 Diseño y UX

### Colores por Género Musical

La app cambia colores automáticamente según el género:

| Género | Color | Ejemplo |
|--------|-------|---------|
| 🎸 Rock | Rojo | #E74C3C |
| 🎤 Pop | Púrpura | #9B59B6 |
| 🎧 Hip Hop | Naranja | #F39C12 |
| 🎹 Electronic | Azul | #3498DB |
| 🎺 Jazz | Naranja Oscuro | #D35400 |
| 🎻 Classical | Violeta | #8E44AD |

### Información por Artista

Cada card muestra:
- **Imagen oficial** del artista
- **Nombre** en tipografía destacada
- **Año de formación** en badge colorido
- **País de origen** con emoji de bandera
- **Género musical** (color del badge)

Al hacer click:
- Biografía del artista
- Estilo musical
- Cantidad de miembros
- Año de formación
- País de origen

---

## 🌐 API - TheAudioDB

### Endpoints Utilizados

```kotlin
// Buscar artista por nombre
GET search.php?s={artist_name}

// Obtener álbumes de un artista
GET album.php?i={artist_id}

// Búsqueda de álbumes
GET searchalbum.php?s={artist_name}
```

### Ejemplo de Respuesta

```json
{
  "artists": [
    {
      "idArtist": "111239",
      "strArtist": "Coldplay",
      "strGenre": "Alternative Rock",
      "strCountry": "United Kingdom",
      "intFormedYear": "1996",
      "strArtistThumb": "https://...",
      "strBiographyEN": "Coldplay are a British rock band...",
      "strWebsite": "www.coldplay.com",
      "strFacebook": "www.facebook.com/coldplay"
    }
  ]
}
```

---

## 🔧 Funcionalidades Clave

### 1. Búsqueda Inteligente

```kotlin
// Busca en múltiples campos
fun searchArtist(query: String) {
    filteredList = artists.filter { 
        it.name.contains(query, ignoreCase = true) ||
        it.genre?.contains(query, ignoreCase = true) == true ||
        it.country?.contains(query, ignoreCase = true) == true
    }
}
```

### 2. Filtro por Año

Slider interactivo de 1950 a 2024 para descubrir artistas por época.

### 3. Categorías Predefinidas

**Rock:**
- Coldplay, Linkin Park, Imagine Dragons, Queen, The Beatles

**Pop:**
- Taylor Swift, Ed Sheeran, Ariana Grande, The Weeknd, Dua Lipa

**Hip Hop:**
- Eminem, Drake, Kanye West, Kendrick Lamar, Post Malone

**Electronic:**
- Daft Punk, Calvin Harris, Avicii, David Guetta, Marshmello

---

## 📊 Modelo de Datos

```kotlin
data class ArtistEntity(
    var id: String,
    var name: String,
    var image: String?,           // Foto principal
    var logo: String?,            // Logo del artista
    var genre: String?,           // Género musical
    var country: String?,         // País de origen
    var formedYear: String?,      // Año de formación
    var biography: String?,       // Biografía
    var style: String?,          // Estilo musical
    var members: String?,        // Cantidad de miembros
    var website: String?,        // Sitio web oficial
    var facebook: String?        // Facebook oficial
)
```

---

## 🎯 Roadmap

### Versión 1.1
- [ ] 🌙 Dark Mode con paleta musical
- [ ] 💾 Artistas favoritos (Room)
- [ ] 📱 Vista de álbumes por artista
- [ ] 🎵 Preview de canciones

### Versión 1.2
- [ ] 🔍 Filtros avanzados (década, país)
- [ ] 📊 Top artistas por país
- [ ] 🎨 Animaciones de transición
- [ ] 🌐 Más géneros (Jazz, Metal, Country)

### Versión 2.0
- [ ] 🏗️ Jetpack Compose
- [ ] 🧪 Unit Tests completos
- [ ] 🎮 Integración con Spotify API
- [ ] 📻 Radio por género

---

## 🤝 Contribuir

¡Contribuciones bienvenidas!

```bash
# 1. Fork el proyecto
# 2. Crea tu branch
git checkout -b feature/nueva-funcionalidad

# 3. Commit
git commit -m '✨ Agrega nueva funcionalidad'

# 4. Push
git push origin feature/nueva-funcionalidad

# 5. Abre un Pull Request
```

### Convenciones
- Usa Kotlin idiomático
- Sigue Material Design guidelines
- Documenta funciones complejas
- Prueba en múltiples dispositivos

---

## 🐛 Reportar Issues

Abre un [Issue](https://github.com/TU_USUARIO/music-discovery-android/issues) incluyendo:

- 📝 Descripción detallada
- 🔄 Pasos para reproducir
- 📱 Dispositivo y versión de Android
- 📸 Screenshots (opcional)

---

## 📄 Licencia

```
MIT License

Copyright (c) 2025 Cristian

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction.
```

---

## 👨‍💻 Autor

**Cristian**
- GitHub: [@Grey-lovelaceK](https://github.com/[TU_USUARIO](https://github.com/Grey-lovelaceK))
- Email: greyc9404@gmail.com
- LinkedIn: [Tu perfil](https://linkedin.com/in/cristian-florez-revilla-420b27293)

---

## 🙏 Agradecimientos

- [TheAudioDB](https://www.theaudiodb.com/) - API gratuita de música
- [Material Design](https://m3.material.io/) - Sistema de diseño
- [Kotlin Team](https://kotlinlang.org/) - Lenguaje moderno
- Comunidad Android Developers

---

## 📚 Recursos Útiles

- [TheAudioDB Docs](https://www.theaudiodb.com/api_guide.php)
- [Retrofit Guide](https://square.github.io/retrofit/)
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-guide.html)
- [Material Design 3](https://m3.material.io/)

---

<p align="center">
  <b>🎵 Descubre música. Descubre artistas. Descubre el mundo. 🌍</b>
</p>

<p align="center">
  ⭐ Si te gusta el proyecto, dale una estrella ⭐
</p>

<p align="center">
  Made with ❤️ and 🎸 in Chile 🇨🇱
</p>
