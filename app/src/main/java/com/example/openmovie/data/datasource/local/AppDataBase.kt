package com.example.openmovie.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.openmovie.data.datasource.local.dto.MovieDto
import com.example.openmovie.data.datasource.local.dto.MovieRecommendationsDto

@Database(entities = [MovieDto::class, MovieRecommendationsDto::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao

    companion object {
        fun createInstance(context: Context): AppDataBase {
            return Room.databaseBuilder(
                context,
                AppDataBase::class.java, "open-movie"
            ).build()
        }
    }

}