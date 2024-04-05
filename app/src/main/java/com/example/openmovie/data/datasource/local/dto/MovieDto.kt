package com.example.openmovie.data.datasource.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.openmovie.data.datasource.remote.toMovie

@Entity
data class MovieDto(
    @PrimaryKey
    val id: Int,
    val title: String,
    val imageUrl: String,
    val backdropPath: String,
    val popularity: Double,
    val voteCount: Int
)

@Entity
data class MovieRecommendationsDto(
    @PrimaryKey val id: Int
)