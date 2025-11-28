package com.inforcap.moviesapirest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inforcap.moviesapirest.core.Constants
import com.inforcap.moviesapirest.models.ArtistEntity
import com.inforcap.moviesapirest.network.ApiService
import kotlinx.coroutines.launch

class MusicViewModel : ViewModel() {

    private val apiService = ApiService.create()

    private val _artistList = MutableLiveData<List<ArtistEntity>>()
    val artistList: LiveData<List<ArtistEntity>> = _artistList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error


    fun getRockArtists() {
        loadArtistsByCategory(Constants.ROCK_ARTISTS)
    }


    fun getPopArtists() {
        loadArtistsByCategory(Constants.POP_ARTISTS)
    }


    fun getHipHopArtists() {
        loadArtistsByCategory(Constants.HIPHOP_ARTISTS)
    }


    fun getElectronicArtists() {
        loadArtistsByCategory(Constants.ELECTRONIC_ARTISTS)
    }


    fun searchArtist(query: String) {
        if (query.isBlank()) return

        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                val response = apiService.searchArtist(query)

                if (response.isSuccessful) {
                    val artists = response.body()?.artists?.filterNotNull() ?: emptyList()
                    _artistList.value = artists

                    if (artists.isEmpty()) {
                        _error.value = "No se encontraron artistas"
                    }
                } else {
                    _error.value = "Error al buscar: ${response.code()}"
                }
            } catch (e: Exception) {
                _error.value = "Error de conexión: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }


    private fun loadArtistsByCategory(artistNames: List<String>) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                val allArtists = mutableListOf<ArtistEntity>()


                for (name in artistNames) {
                    try {
                        val response = apiService.searchArtist(name)
                        if (response.isSuccessful) {
                            response.body()?.artists?.firstOrNull()?.let { artist ->
                                allArtists.add(artist)
                            }
                        }
                    } catch (e: Exception) {

                        continue
                    }
                }

                _artistList.value = allArtists

                if (allArtists.isEmpty()) {
                    _error.value = "No se pudieron cargar artistas"
                }
            } catch (e: Exception) {
                _error.value = "Error de conexión: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}