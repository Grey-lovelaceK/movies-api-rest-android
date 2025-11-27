# 🎬 Movies API Rest - Android App

<p align="center">
  <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" />
  <img src="https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white" />
  <img src="https://img.shields.io/badge/Material%20Design-757575?style=for-the-badge&logo=material-design&logoColor=white" />
  <img src="https://img.shields.io/badge/API-TMDB-01D277?style=for-the-badge" />
</p>

Aplicación Android nativa desarrollada en **Kotlin** que consume la API de **The Movie Database (TMDB)** para mostrar información actualizada de películas. Incluye búsqueda en tiempo real, filtros avanzados y una interfaz moderna con Material Design 3.

---

## ✨ Características Principales

| Funcionalidad | Descripción |
|--------------|-------------|
| 🎯 **Categorías** | Now Playing, Popular, Top Rated, Upcoming |
| 🔍 **Búsqueda Inteligente** | Filtra películas en tiempo real mientras escribes |
| ⭐ **Filtro por Rating** | Slider interactivo para seleccionar calificación mínima (0-10) |
| 🔄 **Ordenamiento** | Por rating, popularidad o alfabético |
| 🎨 **UI Moderna** | Material Design 3 con chips, cards y animaciones |
| 📊 **Badges Dinámicos** | Indicadores de rating con colores según puntuación |
| 🖼️ **Imágenes HD** | Carga optimizada con Glide y placeholders |
| 📱 **Responsive** | Grid adaptativo de 2 columnas |

---

## 🛠️ Stack Tecnológico

### Core
- **Lenguaje:** Kotlin 1.9+
- **Min SDK:** 25 (Android 7.1)
- **Target SDK:** 35 (Android 15)
- **IDE:** Android Studio Hedgehog 2023.1.1+

### Arquitectura
```
MVVM (Model-View-ViewModel)
├── Model: MovieEntity (data classes)
├── View: MainActivity + AdapterMovies
└── ViewModel: MoviesViewModel (LiveData)
```

### Bibliotecas

| Categoría | Librería | Versión | Uso |
|-----------|----------|---------|-----|
| **Networking** | Retrofit | 2.9.0 | Consumo de API REST |
| | Gson Converter | 2.9.0 | Serialización JSON |
| | OkHttp Interceptor | 4.12.0 | Logging de requests |
| **Async** | Coroutines | 1.7.3 | Operaciones asíncronas |
| **UI** | Material Design | 1.10.0+ | Componentes visuales |
| | Glide | 4.16.0 | Carga de imágenes |
| | RecyclerView | 1.3.2 | Listas eficientes |
| **Architecture** | ViewModel | 2.8.7 | Gestión de estado |
| | LiveData | 2.8.7 | Observables |

---

## 🚀 Instalación

### Prerrequisitos

```bash
✅ Android Studio Hedgehog (2023.1.1) o superior
✅ JDK 17+
✅ Android SDK con API 35
✅ Cuenta TMDB (gratuita)
```

### Paso 1: Clonar el Repositorio

```bash
git clone https://github.com/TU_USUARIO/movies-api-rest-android.git
cd movies-api-rest-android
```

### Paso 2: Obtener API Key de TMDB

1. Regístrate en [TMDB](https://www.themoviedb.org/signup)
2. Ve a **Settings → API**
3. Solicita una API Key (v3 auth)
4. Copia tu API Key

### Paso 3: Configurar API Key Localmente

Edita el archivo `local.properties` (en la raíz del proyecto):

```properties
sdk.dir=/ruta/a/tu/Android/Sdk

# Agrega esta línea con TU API Key
tmdb.api.key=TU_API_KEY_AQUI
```

> ⚠️ **IMPORTANTE:** Este archivo NO se sube a GitHub (está en .gitignore)

### Paso 4: Sincronizar y Ejecutar

```bash
# 1. Abre el proyecto en Android Studio
# 2. Espera la sincronización de Gradle
# 3. Ejecuta: Build → Clean Project
# 4. Ejecuta: Build → Rebuild Project
# 5. Conecta un dispositivo o inicia un emulador
# 6. Click en Run ▶️ (o Shift+F10)
```

---

## 📂 Estructura del Proyecto

```
app/src/main/
├── java/com/inforcap/moviesapirest/
│   ├── core/
│   │   └── Constants.kt              # Configuración global
│   ├── models/
│   │   └── MovieEntity.kt            # Data class película
│   ├── network/
│   │   ├── ApiService.kt             # Endpoints Retrofit
│   │   └── response/
│   │       └── MovieResponse.kt      # Respuesta API
│   ├── ui/
│   │   ├── MainActivity.kt           # Pantalla principal
│   │   └── AdapterMovies.kt          # Adapter RecyclerView
│   └── viewmodel/
│       └── MoviesViewModel.kt        # ViewModel MVVM
│
└── res/
    ├── layout/
    │   ├── activity_main.xml         # Layout principal
    │   └── item_rvmovie.xml          # Card de película
    ├── drawable/
    │   └── gradient_overlay.xml      # Gradiente de cards
    └── values/
        ├── colors.xml
        ├── strings.xml
        └── themes.xml
```

---

## 🎨 Detallez de la interfaz

### Pantalla Principal
Vista en grid con categorías, búsqueda y filtros interactivos.

### Búsqueda y Filtros
- **Barra de búsqueda:** Filtrado instantáneo por título
- **Slider de rating:** Rango de 0 a 10 estrellas
- **Spinner:** Ordenamiento múltiple
- **Chips:** Navegación por categorías

### Detalle de Película
Modal con sinopsis completa, rating numérico y popularidad formateada.

---

## 🔧 Configuración Avanzada

### Endpoints Disponibles

```kotlin
interface ApiService {
    @GET("now_playing")
    suspend fun getAllMovies(@Query("api_key") apiKey: String): Response<MovieResponse>
    
    @GET("popular")
    suspend fun getPopular(@Query("api_key") apiKey: String): Response<MovieResponse>
    
    @GET("top_rated")
    suspend fun getTopRated(@Query("api_key") apiKey: String): Response<MovieResponse>
    
    @GET("upcoming")
    suspend fun getUpComing(@Query("api_key") apiKey: String): Response<MovieResponse>
}
```

### Modelo de Datos

```kotlin
data class MovieEntity(
    @SerializedName("id") var id: String,
    @SerializedName("original_title") var title: String,
    @SerializedName("overview") var overview: String,
    @SerializedName("poster_path") var image: String,
    @SerializedName("popularity") var popularity: String,
    @SerializedName("vote_average") var rating: String
)
```

### Filtrado Inteligente

```kotlin
private fun applyFilters() {
    var filteredList = allMovies
    
    // Búsqueda por título
    val query = searchBar.text.toString().lowercase()
    if (query.isNotEmpty()) {
        filteredList = filteredList.filter { 
            it.title.lowercase().contains(query) 
        }
    }
    
    // Filtro por rating mínimo
    val minRating = ratingSlider.value
    filteredList = filteredList.filter { 
        it.rating.toFloatOrNull()?.let { it >= minRating } ?: true
    }
    
    // Ordenamiento
    filteredList = when (sortSpinner.selectedItemPosition) {
        1 -> filteredList.sortedByDescending { it.rating.toFloatOrNull() }
        2 -> filteredList.sortedBy { it.rating.toFloatOrNull() }
        3 -> filteredList.sortedByDescending { it.popularity.toFloatOrNull() }
        4 -> filteredList.sortedBy { it.title }
        else -> filteredList
    }
    
    adapterMovies.updateList(filteredList)
}
```

---

## 🎯 Roadmap

### Versión 1.1 (Próximamente)
- [ ] 🌙 Modo oscuro (Dark Theme)
- [ ] ♾️ Scroll infinito con paginación
- [ ] 💾 Persistencia local con Room Database
- [ ] ❤️ Sistema de favoritos

### Versión 1.2
- [ ] 🎥 Integración de trailers (YouTube API)
- [ ] 🔔 Notificaciones de estrenos
- [ ] 🌐 Soporte multiidioma (ES/EN)
- [ ] 📤 Compartir películas

### Versión 2.0
- [ ] 🧪 Tests unitarios (JUnit, Mockito)
- [ ] 🎭 Tests de UI (Espresso)
- [ ] 🏗️ Migración a Jetpack Compose
- [ ] 🔐 Autenticación de usuarios

---

## 🤝 Contribuir

¡Las contribuciones son bienvenidas! Sigue estos pasos:

1. **Fork** el repositorio
2. Crea una rama: `git checkout -b feature/nueva-funcionalidad`
3. Commit: `git commit -m 'Agrega nueva funcionalidad'`
4. Push: `git push origin feature/nueva-funcionalidad`
5. Abre un **Pull Request**

### Guías de Contribución
- Usa convenciones de Kotlin (camelCase, etc.)
- Documenta funciones complejas
- Mantén el código limpio y legible
- Prueba antes de hacer PR

---

## 🐛 Reportar Bugs

Abre un [Issue](https://github.com/TU_USUARIO/movies-api-rest-android/issues) con:
- Descripción del problema
- Pasos para reproducir
- Screenshots (si aplica)
- Versión de Android
- Modelo del dispositivo

---

## 📄 Licencia

```
MIT License

Copyright (c) 2025 Cristian

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

---

## 👨‍💻 Autor

**Cristian** 
- GitHub: [@Grey-lovelaceK](https://github.com/Grey-lovelaceK)
- Email: greyc9404@gmail.com

---

<p align="center">
  ⭐ Si este proyecto te fue útil, considera darle una estrella ⭐
</p>

<p align="center">
  Hecho con ❤️ y ☕ en Chile
</p>
