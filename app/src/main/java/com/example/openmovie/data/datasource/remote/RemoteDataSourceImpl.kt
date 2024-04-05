package com.example.openmovie.data.datasource.remote

import com.example.openmovie.presentation.movies.adapter.Movie
import javax.inject.Singleton

@Singleton
class RemoteDataSourceImpl : RemoteDataSource {

    override suspend fun getPopularMovies(): List<Movie>? {
        return try {
            val result = MovieClient.createMoviesService().getPopularMovies()
            result.results?.mapNotNull { it?.toMovie()
            } ?: emptyList()
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

    override suspend fun getTopRate(): List<Movie>? {
        return try {
            val result = MovieClient.createMoviesService().getTopRate()
            result.results?.mapNotNull { it?.toMovie()
            } ?: emptyList()
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

    override suspend fun getRecommendedMovies(movieId: Int): List<Movie>? {
        return try {
            val result = MovieClient.createMoviesService().getRecommendedMovies(movieId)
            result.results?.mapNotNull { it?.toMovie() }
                ?: emptyList()
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

}