package com.example.openmovie.data.datasource.remote.dto.favorites


import com.google.gson.annotations.SerializedName

data class FavoritesMovies(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val results: List<Result?>? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null
)