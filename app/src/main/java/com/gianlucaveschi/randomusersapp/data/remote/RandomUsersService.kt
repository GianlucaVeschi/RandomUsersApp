package com.gianlucaveschi.randomusersapp.data.remote

import com.gianlucaveschi.randomusersapp.data.users.UsersApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUsersService {

    @GET("?exc=login")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("seed") seed: String,
        @Query("results") results: Int
    ): UsersApiResponse
}