package com.example.openmovie.data.datasource.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//TODO(BuildConfig)
object MovieClient {

    private val client = OkHttpClient.Builder()
        .authenticator(Authentication())
        .build()

    // Builder
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    fun createMoviesService() = retrofit.create(MovieService::class.java)

    fun createProfileService() = retrofit.create(ProfileService::class.java)

}