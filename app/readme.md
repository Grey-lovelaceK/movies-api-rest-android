# 🎬 Movies API Rest - Android App

Aplicación Android nativa desarrollada en **Kotlin** que consume la API de **The Movie Database (TMDB)** para mostrar información de películas en tiempo real.

## 📱 Características

- ✅ **Navegación por categorías**: Premieres, Popular, Top Rated, Upcoming
- 🔍 **Búsqueda en tiempo real**: Encuentra películas mientras escribes
- ⭐ **Filtro por rating**: Deslizador para filtrar por calificación mínima
- 🔄 **Ordenamiento múltiple**: Por rating, popularidad o título
- 🎨 **Interfaz moderna**: Material Design 3 con chips y cards
- 📊 **Indicadores visuales**: Badges de rating con código de colores
- 💾 **Arquitectura limpia**: MVVM + Retrofit + Coroutines

## 🛠️ Tecnologías Utilizadas

| Tecnología | Uso |
|------------|-----|
| **Kotlin** | Lenguaje principal |
| **Retrofit** | Cliente HTTP para consumir API REST |
| **Coroutines** | Programación asíncrona |
| **MVVM** | Patrón de arquitectura |
| **Glide** | Carga de imágenes |
| **Material Design 3** | Componentes UI modernos |
| **RecyclerView** | Listas eficientes |
| **ViewBinding** | Binding seguro de vistas |

## 📦 Dependencias Principales

```gradle
dependencies {
    // Retrofit para peticiones HTTP
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    
    // Coroutines para asincronía
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3'
    
    // ViewModel y LiveData
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2'
    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.6.2'
    
    // Glide para imágenes
    implementation 'com.github.bumptech.glide:glide:4.16.0'
    
    // Material Design
    implementation 'com.google.android.material:material:1.10.0'
}
```

## 🚀 Instalación y Configuración

### Prerrequisitos

- Android Studio Hedgehog | 2023.1.1 o superior
- JDK 17 o superior
- Android SDK API 24+ (Android 7.0+)
- Cuenta en [TMDB](https://www.themoviedb.org/) para obtener API Key

### Pasos de instalación

1. **Clonar el repositorio**
```bash
git clone https://github.com/TU_USUARIO/movies-api-rest-android.git
cd movies-api-rest-android
```

2. **Obtener API Key de TMDB**
    - Regístrate en [TMDB](https://www.themoviedb.org/signup)
    - Ve a tu perfil → Settings → API
    - Copia tu API Key (v3 auth)

3. **Configurar API Key**

En `core/Constants.kt`:
```kotlin
object Constants {
    const val API_KEY = "TU_API_KEY_AQUI"
    const val API_URL = "https://api.themoviedb.org/3/movie/"
    const val API_URL_IMAGE = "https://image.tmdb.org/t/p/w500"
}
```

4. **Sincronizar el proyecto**
    - Abre el proyecto en Android Studio
    - Espera a que Gradle sincronice automáticamente
    - Si no sincroniza: `File → Sync Project with Gradle Files`

5. **Ejecutar la aplicación**
    - Conecta un dispositivo Android o inicia un emulador
    - Click en el botón **Run ▶️** (o `Shift + F10`)

## 📂 Estructura del Proyecto

```
app/
├── src/main/java/com/inforcap/moviesapirest/
│   ├── core/
│   │   └── Constants.kt           # Constantes globales
│   ├── models/
│   │   └── MovieEntity.kt         # Modelo de datos
│   ├── network/
│   │   ├── ApiService.kt          # Interfaz Retrofit
│   │   └── response/
│   │       └── MovieResponse.kt   # Respuesta API
│   ├── ui/
│   │   ├── MainActivity.kt        # Actividad principal
│   │   └── AdapterMovies.kt       # Adaptador RecyclerView
│   └── viewmodel/
│       └── MoviesViewModel.kt     # ViewModel MVVM
├── res/
│   ├── layout/
│   │   ├── activity_main.xml      # Layout principal
│   │   └── item_rvmovie.xml       # Item de película
│   └── drawable/
│       └── gradient_overlay.xml   # Gradiente para cards
└── AndroidManifest.xml
```

## 🎨 Capturas de Pantalla

### Pantalla Principal
Muestra las películas en un grid de 2 columnas con filtros dinámicos.

### Filtros Activos
- Barra de búsqueda con filtrado en tiempo real
- Slider de rating mínimo (0-10)
- Spinner de ordenamiento
- Chips de categorías

### Detalle de Película
Modal con sinopsis completa, rating y popularidad.

## 🔑 Funcionalidades Principales

### Búsqueda y Filtrado
```kotlin
private fun applyFilters() {
    var filteredList = allMovies

    // Búsqueda por título
    val searchQuery = binding.searchBar.text.toString().lowercase()
    if (searchQuery.isNotEmpty()) {
        filteredList = filteredList.filter { 
            it.title.lowercase().contains(searchQuery) 
        }
    }

    // Filtro por rating mínimo
    val minRating = binding.ratingSlider.value
    filteredList = filteredList.filter { 
        it.rating.toFloatOrNull()?.let { rating -> rating >= minRating } ?: true
    }
    
    // Actualizar UI
    adapterMovies.movieList = filteredList
    adapterMovies.notifyDataSetChanged()
}
```

### Consumo de API con Retrofit
```kotlin
suspend fun getAllMovies(apiKey: String): Response<MovieResponse>
suspend fun getPopular(apiKey: String): Response<MovieResponse>
suspend fun getTopRated(apiKey: String): Response<MovieResponse>
suspend fun getUpComing(apiKey: String): Response<MovieResponse>
```

## 🎯 Próximas Mejoras

- [ ] Agregar modo oscuro
- [ ] Implementar paginación infinita
- [ ] Guardar películas favoritas (Room Database)
- [ ] Compartir películas en redes sociales
- [ ] Vista detallada con trailer de YouTube
- [ ] Caché de imágenes offline
- [ ] Tests unitarios y de UI

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Por favor:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/NuevaCaracteristica`)
3. Commit tus cambios (`git commit -m 'Agrega nueva característica'`)
4. Push a la rama (`git push origin feature/NuevaCaracteristica`)
5. Abre un Pull Request

