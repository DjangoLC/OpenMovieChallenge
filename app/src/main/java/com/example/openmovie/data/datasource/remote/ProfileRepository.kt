package com.example.openmovie.data.datasource.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ProfileRepository {

    suspend fun getRatedMovies() = withContext(Dispatchers.IO) {
        try {
            MovieClient.createProfileService().getRatedMovies().results?.map {
                it.toDomain()
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

    suspend fun getFavoriteMovies() = withContext(Dispatchers.IO) {
        try {
            MovieClient.createProfileService().getFavoriteMovies().results?.map {
                it.toDomain()
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

    suspend fun getAccountInfo() = withContext(Dispatchers.IO) {
        try {
            MovieClient.createProfileService().getAccountInfo().toDomain()
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

}