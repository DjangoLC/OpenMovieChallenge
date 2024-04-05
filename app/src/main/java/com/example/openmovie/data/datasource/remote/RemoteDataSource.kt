package com.example.openmovie.data.datasource.remote

import com.example.openmovie.presentation.movies.adapter.Movie

interface RemoteDataSource {
    suspend fun getPopularMovies(): List<Movie>?
    suspend fun getTopRate(): List<Movie>?
    suspend fun getRecommendedMovies(movieId: Int): List<Movie>?
}