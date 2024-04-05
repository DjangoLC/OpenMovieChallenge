package com.example.openmovie.data.datasource.remote.dto


import com.google.gson.annotations.SerializedName

data class RatedMovies(
    @SerializedName("page")
    val page: Int? = 0,
    @SerializedName("results")
    val results: List<ResultX>? = listOf(),
    @SerializedName("total_pages")
    val totalPages: Int? = 0,
    @SerializedName("total_results")
    val totalResults: Int? = 0
)