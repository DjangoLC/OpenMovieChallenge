package com.example.openmovie.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.openmovie.data.datasource.local.dto.MovieDto
import com.example.openmovie.data.datasource.local.dto.MovieRecommendationsDto

@Dao
interface MoviesDao {

    @Query("SELECT * FROM MovieDto")
    suspend fun getAllMovies(): List<MovieDto>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(users: List<MovieDto>)

    @Query("SELECT * FROM MovieDto as m INNER JOIN MovieRecommendationsDto as mr WHERE m.id = mr.id")
    suspend fun getAllRecommendationMovies(): List<MovieDto>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMoviesRecommendations(movies: List<MovieRecommendationsDto>)
}