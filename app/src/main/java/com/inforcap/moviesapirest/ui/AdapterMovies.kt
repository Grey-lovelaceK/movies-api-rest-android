package com.inforcap.moviesapirest.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.inforcap.moviesapirest.R
import com.inforcap.moviesapirest.core.Constants
import com.inforcap.moviesapirest.databinding.ItemRvmovieBinding
import com.inforcap.moviesapirest.models.MovieEntity

class AdapterMovies(
    val context: Context,
    var movieList: List<MovieEntity>
) : RecyclerView.Adapter<AdapterMovies.MoviesViewHolder>() {

    private lateinit var binding: ItemRvmovieBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        binding = ItemRvmovieBinding.inflate(LayoutInflater.from(context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.onBind(movieList[position])
    }

    inner class MoviesViewHolder(private val binding: ItemRvmovieBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(movie: MovieEntity) {
            binding.run {
                // Cargar imagen con Glide
                Glide.with(context)
                    .load("${Constants.API_URL_IMAGE}${movie.image}")
                    .centerCrop()
                    .apply(RequestOptions().override(Constants.IMAGE_WIDTH, Constants.IMAGE_HEIGHT))
                    .placeholder(R.drawable.ic_launcher_background) // Placeholder mientras carga
                    .error(R.drawable.baseline_error_24)
                    .into(ivImage)

                // Título
                tvTitle.text = movie.title

                // Rating con formato
                val rating = movie.rating.toFloatOrNull() ?: 0f
                tvRating.text = String.format("%.1f", rating)

                // Color del badge según rating
                ratingBadge.setCardBackgroundColor(
                    when {
                        rating >= 8.0 -> context.getColor(android.R.color.holo_green_light)
                        rating >= 6.0 -> context.getColor(android.R.color.holo_orange_light)
                        else -> context.getColor(android.R.color.holo_red_light)
                    }
                )

                // Popularidad formateada
                val popularity = movie.popularity.toFloatOrNull() ?: 0f
                tvPopularity.text = when {
                    popularity >= 1000 -> String.format("%.1fK", popularity / 1000)
                    else -> popularity.toInt().toString()
                }

                // Click para mostrar overview
                cvMovie.setOnClickListener {
                    showMovieDetails(movie)
                }
            }
        }

        private fun showMovieDetails(movie: MovieEntity) {
            val rating = movie.rating.toFloatOrNull() ?: 0f
            val popularity = movie.popularity.toFloatOrNull() ?: 0f

            val message = """
                ${movie.overview}
                
                ⭐ Rating: ${String.format("%.1f", rating)}/10
                🔥 Popularidad: ${String.format("%.0f", popularity)}
            """.trimIndent()

            AlertDialog.Builder(context)
                .setTitle(movie.title)
                .setMessage(message)
                .setPositiveButton("Cerrar") { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }
}