package com.example.openmovie.data.datasource.remote.dto.profile


import com.google.gson.annotations.SerializedName

data class Avatar(
    @SerializedName("gravatar")
    val gravatar: Gravatar? = null,
    @SerializedName("tmdb")
    val tmdb: Tmdb? = null
)