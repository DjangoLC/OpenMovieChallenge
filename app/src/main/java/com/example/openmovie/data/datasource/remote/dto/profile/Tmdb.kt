package com.example.openmovie.data.datasource.remote.dto.profile


import com.google.gson.annotations.SerializedName

data class Tmdb(
    @SerializedName("avatar_path")
    val avatarPath: String? = null
)