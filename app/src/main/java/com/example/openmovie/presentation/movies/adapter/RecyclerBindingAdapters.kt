package com.example.openmovie.presentation.movies.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.openmovie.utils.AspectRatioImageView

@BindingAdapter("bind:imageUrl")
fun loadImage(view: AspectRatioImageView, imageUrl: String?) {
    Glide.with(view.context).load(imageUrl).into(view)
}

@BindingAdapter("bind:setAdapter")
fun setup(rv: RecyclerView, adapter: MovieAdapter) {
    rv.adapter = adapter
    rv.layoutManager = LinearLayoutManager(rv.context).apply {
        orientation = LinearLayoutManager.HORIZONTAL
    }
}