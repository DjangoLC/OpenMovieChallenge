package com.example.openmovie.data.datasource.remote

import com.example.mymovieapp.network.model.MovieList
import com.example.openmovie.data.datasource.remote.dto.MovieListsWithDate
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

    @GET("3/movie/popular")
    suspend fun getPopularMovies(): MovieList

    @GET("3/movie/{movie_id}/recommendations")
    suspend fun getRecommendedMovies(@Path("movie_id") movieId: Int): MovieListsWithDate

    @GET("3/movie/top_rated?language=en-US&page=3")
    suspend fun getTopRate(): MovieList

}