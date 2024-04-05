package com.example.openmovie.data.datasource.remote.dto.profile


import com.google.gson.annotations.SerializedName

data class Gravatar(
    @SerializedName("hash")
    val hash: String? = null
)