package com.example.openmovie.data.datasource.remote

import com.example.mymovieapp.network.model.Result
import com.example.openmovie.data.datasource.local.dto.MovieDto
import com.example.openmovie.data.datasource.remote.dto.ResultX
import com.example.openmovie.data.datasource.remote.dto.profile.AccountDto
import com.example.openmovie.presentation.movies.adapter.Account
import com.example.openmovie.presentation.movies.adapter.Movie

fun Result.toMovie(): Movie {

    return Movie(
        id = this.id ?: Int.MIN_VALUE,
        title = this.title ?: "unknown",
        url = this.posterPath ?: "default_url",
        backdropPath = this.backdropPath ?: "default_url",
        popularity = this.popularity ?: 0.0,
        voteCount = this.voteCount ?: 0
    )
}

fun MovieDto.toDomain() =
    Movie(
        id = this.id,
        title = this.title,
        url = this.imageUrl,
        backdropPath = this.backdropPath,
        popularity = this.popularity,
        voteCount = this.voteCount
    )

fun Movie.toDb() =
    MovieDto(
        id = this.id,
        title = this.title,
        imageUrl = this.url,
        backdropPath = this.backdropPath,
        popularity = this.popularity,
        voteCount = this.voteCount
    )


fun ResultX.toDomain() =
    Movie(
        id = this.id ?: Int.MIN_VALUE,
        title = this.title ?: "unknown",
        url = this.posterPath ?: "default_url",
        backdropPath = this.backdropPath ?: "default_url",
        popularity = this.popularity ?: 0.0,
        voteCount = this.voteCount ?: 0
    )

fun AccountDto.toDomain() =
    Account(
        id = this.id,
        name = this.name,
        username = this.username
    )

