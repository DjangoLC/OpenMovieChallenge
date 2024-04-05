package com.example.openmovie.data.datasource.local

import com.example.openmovie.data.datasource.local.dto.MovieRecommendationsDto
import com.example.openmovie.data.datasource.remote.toDb
import com.example.openmovie.data.datasource.remote.toDomain
import com.example.openmovie.presentation.movies.adapter.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSourceImpl @Inject constructor(private val database: AppDataBase) : LocalDataSource {

    override suspend fun saveMovies(movies: List<Movie>) {
        return withContext(Dispatchers.IO) {
            database.moviesDao().insertAll(movies.map { it.toDb() })
        }
    }

    override suspend fun saveRecommendationsMovies(movies: List<MovieRecommendationsDto>) {
        withContext(Dispatchers.IO) {
            database.moviesDao().insertMoviesRecommendations(movies)
        }
    }

    override suspend fun getAllMovies(): List<Movie> {
        return withContext(Dispatchers.IO) {
            database.moviesDao().getAllMovies().map { it.toDomain() }
        }
    }

    override suspend fun getAllRecommendedMovies(): List<Movie> {
        return withContext(Dispatchers.IO) {
            database.moviesDao().getAllRecommendationMovies().map { it.toDomain() }
        }
    }

}