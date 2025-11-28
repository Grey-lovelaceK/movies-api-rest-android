package com.inforcap.moviesapirest.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.inforcap.moviesapirest.R
import com.inforcap.moviesapirest.core.Constants
import com.inforcap.moviesapirest.databinding.ActivityMainBinding
import com.inforcap.moviesapirest.models.ArtistEntity
import com.inforcap.moviesapirest.viewmodel.MusicViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MusicViewModel
    private lateinit var adapter: AdapterMusica

    private var allArtists: List<ArtistEntity> = listOf()
    private var currentCategory = Constants.CATEGORY_ROCK
    private var searchQuery = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MusicViewModel::class.java]

        initRecyclerView()
        setupSearchBar()
        setupYearFilter()
        setupSortSpinner()
        setupCategoryChips()

        // Cargar artistas por defecto
        binding.tvCategory.text = currentCategory
        viewModel.getRockArtists()

        // Observers
        viewModel.artistList.observe(this) { artists ->
            allArtists = artists
            applyFilters()
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.rvMovies.visibility = if (isLoading) View.GONE else View.VISIBLE
        }

        viewModel.error.observe(this) { error ->
            error?.let {
                binding.tvNoResults.text = it
                binding.tvNoResults.visibility = View.VISIBLE
            }
        }
    }

    private fun initRecyclerView() {
        val layoutManager = GridLayoutManager(this, 2)
        binding.rvMovies.layoutManager = layoutManager
        adapter = AdapterMusica(this, arrayListOf())
        binding.rvMovies.adapter = adapter
    }

    private fun setupSearchBar() {
        binding.searchBar.hint = "Buscar artista..."

        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchQuery = s.toString()

                if (searchQuery.length >= 3) {

                } else {

                    applyFilters()
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })


        binding.searchBar.setOnEditorActionListener { _, _, _ ->
            if (searchQuery.isNotEmpty()) {
                viewModel.searchArtist(searchQuery)
            }
            false
        }
    }

    private fun setupYearFilter() {
        binding.ratingSlider.valueFrom = 1950f
        binding.ratingSlider.valueTo = 2024f
        binding.ratingSlider.value = 1950f
        binding.tvRatingValue.text = "Año mínimo: 1950"

        binding.ratingSlider.addOnChangeListener { slider, value, fromUser ->
            binding.tvRatingValue.text = "Año mínimo: ${value.toInt()}"
            applyFilters()
        }
    }

    private fun setupSortSpinner() {
        val sortOptions = arrayOf(
            "Sin ordenar",
            "Nombre (A-Z)",
            "Nombre (Z-A)",
            "Año (Más reciente)",
            "Año (Más antiguo)",
            "País (A-Z)"
        )
        val adapterSpinner = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, sortOptions)
        binding.sortSpinner.adapter = adapterSpinner

        binding.sortSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                applyFilters()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setupCategoryChips() {
        binding.chipPremiere.text = "🎸 Rock"
        binding.chipPopular.text = "🎤 Pop"
        binding.chipTopRated.text = "🎧 Hip Hop"
        binding.chipUpcoming.text = "🎹 Electronic"

        binding.chipPremiere.setOnClickListener {
            loadCategory(Constants.CATEGORY_ROCK) { viewModel.getRockArtists() }
        }
        binding.chipPopular.setOnClickListener {
            loadCategory(Constants.CATEGORY_POP) { viewModel.getPopArtists() }
        }
        binding.chipTopRated.setOnClickListener {
            loadCategory(Constants.CATEGORY_HIPHOP) { viewModel.getHipHopArtists() }
        }
        binding.chipUpcoming.setOnClickListener {
            loadCategory(Constants.CATEGORY_ELECTRONIC) { viewModel.getElectronicArtists() }
        }
    }

    private fun loadCategory(category: String, loadAction: () -> Unit) {
        if (currentCategory != category) {
            currentCategory = category
            binding.tvCategory.text = category
            binding.progressBar.visibility = View.VISIBLE
            binding.rvMovies.visibility = View.GONE
            binding.tvNoResults.visibility = View.GONE


            binding.chipGroupCategories.clearCheck()
            when(category) {
                Constants.CATEGORY_ROCK -> binding.chipPremiere.isChecked = true
                Constants.CATEGORY_POP -> binding.chipPopular.isChecked = true
                Constants.CATEGORY_HIPHOP -> binding.chipTopRated.isChecked = true
                Constants.CATEGORY_ELECTRONIC -> binding.chipUpcoming.isChecked = true
            }

            loadAction()
        }
    }

    private fun applyFilters() {
        var filteredList = allArtists


        if (searchQuery.isNotEmpty()) {
            filteredList = filteredList.filter {
                it.name.lowercase().contains(searchQuery.lowercase()) ||
                        it.genre?.lowercase()?.contains(searchQuery.lowercase()) == true ||
                        it.country?.lowercase()?.contains(searchQuery.lowercase()) == true
            }
        }


        val minYear = binding.ratingSlider.value.toInt()
        filteredList = filteredList.filter {
            it.formedYear?.toIntOrNull()?.let { year -> year >= minYear } ?: true
        }


        filteredList = when (binding.sortSpinner.selectedItemPosition) {
            1 -> filteredList.sortedBy { it.name }
            2 -> filteredList.sortedByDescending { it.name }
            3 -> filteredList.sortedByDescending { it.formedYear?.toIntOrNull() ?: 0 }
            4 -> filteredList.sortedBy { it.formedYear?.toIntOrNull() ?: 9999 }
            5 -> filteredList.sortedBy { it.country ?: "ZZZ" }
            else -> filteredList
        }

        adapter.artistList = filteredList
        adapter.notifyDataSetChanged()

        binding.tvNoResults.visibility = if (filteredList.isEmpty()) View.VISIBLE else View.GONE
        if (filteredList.isEmpty()) {
            binding.tvNoResults.text = "😔 No se encontraron artistas"
        }
    }
}