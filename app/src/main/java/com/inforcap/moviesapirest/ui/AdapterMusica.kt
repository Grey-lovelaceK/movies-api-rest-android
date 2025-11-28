package com.inforcap.moviesapirest.ui

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.inforcap.moviesapirest.R
import com.inforcap.moviesapirest.core.Constants
import com.inforcap.moviesapirest.databinding.ItemRvmovieBinding
import com.inforcap.moviesapirest.models.ArtistEntity

class AdapterMusica(
    val context: Context,
    var artistList: List<ArtistEntity>
) : RecyclerView.Adapter<AdapterMusica.MusicViewHolder>() {

    private lateinit var binding: ItemRvmovieBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        binding = ItemRvmovieBinding.inflate(LayoutInflater.from(context), parent, false)
        return MusicViewHolder(binding)
    }

    override fun getItemCount(): Int = artistList.size

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.onBind(artistList[position])
    }

    inner class MusicViewHolder(private val binding: ItemRvmovieBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(artist: ArtistEntity) {
            binding.run {

                val imageUrl = artist.image ?: artist.logo ?: ""

                Glide.with(context)
                    .load(imageUrl)
                    .centerCrop()
                    .apply(RequestOptions().override(Constants.IMAGE_WIDTH, Constants.IMAGE_HEIGHT))
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.baseline_error_24)
                    .into(ivImage)

                tvTitle.text = artist.name

                val year = artist.formedYear ?: "????"
                tvRating.text = year

                val genre = artist.genre ?: "Rock"
                val genreColor = Constants.GENRE_COLORS[genre] ?: "#9B59B6"
                try {
                    ratingBadge.setCardBackgroundColor(Color.parseColor(genreColor))
                } catch (e: Exception) {
                    ratingBadge.setCardBackgroundColor(Color.parseColor("#9B59B6"))
                }

                val country = artist.country ?: "Unknown"
                val flag = Constants.COUNTRY_FLAGS[country] ?: "🌍"
                tvPopularity.text = "$flag $country"

                cvMovie.setOnClickListener {
                    showArtistDetails(artist)
                }
            }
        }

        private fun showArtistDetails(artist: ArtistEntity) {
            val genre = artist.genre ?: "Unknown"
            val style = artist.style ?: "N/A"
            val members = artist.members ?: "N/A"
            val bio = artist.biography?.take(300) ?: "No biography available"
            val country = artist.country ?: "Unknown"
            val flag = Constants.COUNTRY_FLAGS[country] ?: "🌍"

            val message = """
                🎵 Género: $genre
                
                🎨 Estilo: $style
                
                $flag País: $country
                
                👥 Miembros: $members
                
                📅 Formado en: ${artist.formedYear ?: "Unknown"}
                
                📖 Bio: $bio${if (bio.length >= 300) "..." else ""}
            """.trimIndent()

            AlertDialog.Builder(context)
                .setTitle("🎤 ${artist.name}")
                .setMessage(message)
                .setPositiveButton("Cerrar") { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }
}