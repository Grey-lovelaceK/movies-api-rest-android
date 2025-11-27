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
import com.google.android.material.chip.Chip
import com.google.android.material.slider.RangeSlider
import com.inforcap.moviesapirest.R
import com.inforcap.moviesapirest.core.Constants
import com.inforcap.moviesapirest.databinding.ActivityMainBinding
import com.inforcap.moviesapirest.models.MovieEntity
import com.inforcap.moviesapirest.viewmodel.MoviesViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MoviesViewModel
    private lateinit var adapterMovies: AdapterMovies

    private var allMovies: List<MovieEntity> = listOf()
    private var currentCategory = Constants.CATEGORY_ALLMOVIES

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]

        initRecyclerView()
        setupSearchBar()
        setupRatingFilter()
        setupSortSpinner()
        setupCategoryChips()

        // Cargar películas por defecto
        binding.tvCategory.text = currentCategory
        viewModel.getAllMovies()

        // Observer
        viewModel.movieList.observe(this) { movies ->
            allMovies = movies
            applyFilters()
            binding.progressBar.visibility = View.GONE
            binding.rvMovies.visibility = View.VISIBLE
        }
    }

    private fun initRecyclerView() {
        val layoutManager = GridLayoutManager(this, 2) // Cambié a 2 columnas para mejor visualización
        binding.rvMovies.layoutManager = layoutManager
        adapterMovies = AdapterMovies(this, arrayListOf())
        binding.rvMovies.adapter = adapterMovies
    }

    private fun setupSearchBar() {
        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                applyFilters()
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun setupRatingFilter() {
        binding.ratingSlider.addOnChangeListener { slider, value, fromUser ->
            binding.tvRatingValue.text = "Rating mínimo: ${String.format("%.1f", value)}"
            applyFilters()
        }
    }

    private fun setupSortSpinner() {
        val sortOptions = arrayOf("Sin ordenar", "Rating (Mayor a Menor)", "Rating (Menor a Mayor)",
            "Popularidad (Mayor a Menor)", "Título (A-Z)")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, sortOptions)
        binding.sortSpinner.adapter = adapter

        binding.sortSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                applyFilters()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setupCategoryChips() {
        binding.chipPremiere.setOnClickListener { loadCategory(Constants.CATEGORY_ALLMOVIES) { viewModel.getAllMovies() } }
        binding.chipPopular.setOnClickListener { loadCategory(Constants.CATEGORY_POPULARITY) { viewModel.getPopular() } }
        binding.chipTopRated.setOnClickListener { loadCategory(Constants.CATEGORY_TOPRATED) { viewModel.getTopRated() } }
        binding.chipUpcoming.setOnClickListener { loadCategory(Constants.CATEGORY_UPCOMING) { viewModel.getUpComing() } }
    }

    private fun loadCategory(category: String, loadAction: () -> Unit) {
        if (currentCategory != category) {
            currentCategory = category
            binding.tvCategory.text = category
            binding.progressBar.visibility = View.VISIBLE
            binding.rvMovies.visibility = View.GONE

            // Actualizar estado de chips
            binding.chipGroupCategories.clearCheck()
            when(category) {
                Constants.CATEGORY_ALLMOVIES -> binding.chipPremiere.isChecked = true
                Constants.CATEGORY_POPULARITY -> binding.chipPopular.isChecked = true
                Constants.CATEGORY_TOPRATED -> binding.chipTopRated.isChecked = true
                Constants.CATEGORY_UPCOMING -> binding.chipUpcoming.isChecked = true
            }

            loadAction()
        }
    }

    private fun applyFilters() {
        var filteredList = allMovies

        // Filtro de búsqueda
        val searchQuery = binding.searchBar.text.toString().lowercase()
        if (searchQuery.isNotEmpty()) {
            filteredList = filteredList.filter {
                it.title.lowercase().contains(searchQuery)
            }
        }

        // Filtro de rating
        val minRating = binding.ratingSlider.value
        filteredList = filteredList.filter {
            it.rating.toFloatOrNull()?.let { rating -> rating >= minRating } ?: true
        }

        // Ordenamiento
        filteredList = when (binding.sortSpinner.selectedItemPosition) {
            1 -> filteredList.sortedByDescending { it.rating.toFloatOrNull() ?: 0f }
            2 -> filteredList.sortedBy { it.rating.toFloatOrNull() ?: 0f }
            3 -> filteredList.sortedByDescending { it.popularity.toFloatOrNull() ?: 0f }
            4 -> filteredList.sortedBy { it.title }
            else -> filteredList
        }

        // Actualizar adapter
        adapterMovies.movieList = filteredList
        adapterMovies.notifyDataSetChanged()

        // Mostrar mensaje si no hay resultados
        binding.tvNoResults.visibility = if (filteredList.isEmpty()) View.VISIBLE else View.GONE
    }
}