package com.example.openmovie.data

import com.example.openmovie.data.datasource.local.LocalDataSource
import com.example.openmovie.data.datasource.local.dto.MovieRecommendationsDto
import com.example.openmovie.data.datasource.remote.RemoteDataSource
import com.example.openmovie.presentation.movies.adapter.Movie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    // id to get movies recommendations
    private val movieId = 281957

    suspend fun getPopularMovies(): List<Movie> {
         remoteDataSource.getPopularMovies()?.also {
            localDataSource.saveMovies(it)
        }
        return localDataSource.getAllMovies().sortedByDescending { it.popularity }
    }

    suspend fun getRecommendedMovies(): List<Movie> {
        remoteDataSource.getRecommendedMovies(movieId)?.also {
            localDataSource.saveMovies(it)
            localDataSource.saveRecommendationsMovies(it.map { MovieRecommendationsDto(it.id) })
        }
        return localDataSource.getAllRecommendedMovies()
    }

    suspend fun getTopRate(): List<Movie> {
        remoteDataSource.getTopRate()?.also {
            localDataSource.saveMovies(it)
        }
        return localDataSource.getAllMovies().sortedByDescending { it.voteCount }
    }

}