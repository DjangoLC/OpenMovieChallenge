package com.example.openmovie.presentation.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.openmovie.R
import com.example.openmovie.data.datasource.remote.dto.profile.Avatar
import com.example.openmovie.databinding.MovieItemBinding
import com.google.gson.annotations.SerializedName

data class Account(
    val id: Int? = null,
    val name: String? = null,
    val username: String? = null
)

data class Movie(
    val id: Int,
    val title: String,
    val url: String,
    val backdropPath: String,
    val popularity: Double,
    val voteCount: Int
) {
    fun createUrl(): String {
        val prefix = "https://image.tmdb.org/t/p/w780"
         return if (this.url.isNotEmpty()) {
             prefix.plus(this.url)
        } else {
             prefix.plus(this.backdropPath)
         }
    }
}

class MovieAdapter : ListAdapter<Movie, MovieViewHolder>(diffCallback) {

    companion object {
        val diffCallback = object: DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class MovieViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): MovieViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return MovieViewHolder(
                DataBindingUtil.inflate(inflater, R.layout.movie_item, null, false)
            )
        }
    }

    fun bind(movie: Movie) {
        val url = movie.createUrl()
        binding.imageUrl = url
        binding.movieName = movie.title
        binding.executePendingBindings()
    }


}