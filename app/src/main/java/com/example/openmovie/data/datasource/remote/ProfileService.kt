package com.example.openmovie.data.datasource.remote

import com.example.openmovie.data.datasource.remote.dto.RatedMovies
import com.example.openmovie.data.datasource.remote.dto.profile.AccountDto
import retrofit2.http.GET

interface ProfileService {

    @GET("3/account/8611472/rated/movies")
    suspend fun getRatedMovies(): RatedMovies

    @GET("3/account/8611472/favorite/movies")
    suspend fun getFavoriteMovies(): RatedMovies

    @GET("3/account/8611472")
    suspend fun getAccountInfo(): AccountDto

}