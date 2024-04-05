package com.example.openmovie.data.datasource.local

import com.example.openmovie.data.datasource.local.dto.MovieRecommendationsDto
import com.example.openmovie.presentation.movies.adapter.Movie

interface LocalDataSource {

    suspend fun saveMovies(movies: List<Movie>)
    suspend fun saveRecommendationsMovies(movies: List<MovieRecommendationsDto>)
    suspend fun getAllMovies(): List<Movie>
    suspend fun getAllRecommendedMovies(): List<Movie>

}