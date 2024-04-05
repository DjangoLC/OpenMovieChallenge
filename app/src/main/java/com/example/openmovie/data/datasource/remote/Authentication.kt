package com.example.openmovie.data.datasource.remote

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class Authentication : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request {
        return response.request().newBuilder()
            .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlNDYyODkzNjQzYWRiNzZkYjA0YWZmZjhhYTBmMzBjMCIsInN1YiI6IjVkNTIyN2EwZmNiOGNjMDAxM2U4N2Y2NCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.2kWCs2IrkZsvdXdG_UyTdEKzU83ogVrzIMz6VEBBNXc")
            .build()
    }
}